package com.company.project.modules.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.company.project.configurer.security.model.User;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.modules.user.service.UserService;
import com.company.project.modules.wechat.utils.WxCommonUtil;
import com.company.project.utils.TokenUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WechatController
 *
 * @author yanfeixiang
 * @date 2018/3/28
 */
@RestController
@RequestMapping("api/wechat")
public class WeChatController {

    @Value("${app.id}")
    private String appId;

    @Value("${app.secret}")
    private String appSecret;

    @Value("${wxAuthUrl}")
    private String wxAuthUrl;

    @Value("${redirectUrl}")
    private String redirectUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private TokenUtils tokenUtils;

    private UserService userService;

    @Autowired
    public WeChatController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 微信公众号网页授权.
     */
    @GetMapping("/authWeb")
    public void authWeb(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 向微信服务器请求获取用户openId
            String url = wxAuthUrl;
            url = url.replace("APPID", WxCommonUtil.urlEncodeUTF8(appId));
            url = url.replace("REDIRECT_URI", WxCommonUtil.urlEncodeUTF8(redirectUrl));

            String state = request.getParameter("redirectUrl");
            url = url.replace("STATE", WxCommonUtil.urlEncodeUTF8(state));
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                LOGGER.debug("授权异常");
                e.printStackTrace();
            }
        } catch (Exception e) {
            LOGGER.error("微信公众号-共通接口 " + this.getClass().getName() + "#authWeb\n" + e.getMessage());
        }
    }

    @GetMapping("/getAuthResult")
    public Result getAuthResult(@RequestParam String code, @RequestParam String state) {
        String result = null;
        try {
            if (code != null && !code.isEmpty()) {
                // 授权得到code
                result = WxCommonUtil.getAuthResult(appId, appSecret, code);
                LOGGER.info("获取到结果:" + result);
            }
        } catch (Exception e) {
            LOGGER.error(
                    "微信公众号-共通接口 " + this.getClass().getName() + "#queryOpenId\n" + e.getMessage());
        }
        return ResultGenerator.genSuccessResult(result);
    }

    @GetMapping("/fetchAuthResult")
    public void fetchAuthResult(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String result;

        try {
            // 授权得到code
            result = WxCommonUtil.getAuthResult(appId, appSecret, code);
            JSONObject object = JSONObject.parseObject(result);
            // 返回结果不为空
            if (ObjectUtils.allNotNull(object)) {
                String openId = object.getString("openid");
                // 结果包含openid
                if (StringUtils.isNotEmpty(openId)) {
                    User user = userService.getUserByOpenId(openId);
                    String token = tokenUtils.generateToken(user);
                    state = state + "?token=" + token;
                }
            }

            response.sendRedirect(state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
