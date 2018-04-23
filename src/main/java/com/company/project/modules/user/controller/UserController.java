package com.company.project.modules.user.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.modules.user.model.User;
import com.company.project.modules.user.service.UserService;
import com.company.project.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author flynacl
 * @date 2018-03-26
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Value("${token.header}")
    private String tokenHeader;

    @PostMapping("/login")
    public Result login(User user) {
        List<User> userList = userService.login(user);
        if (userList.size() > 1) {
            return ResultGenerator.genFailResult("记录过多！");
        } else if (userList.size() == 1) {
            Map<String, Object> result = new HashMap<>(2);
            User resultUser = userList.get(0);
            result.put("user", resultUser);
            com.company.project.configurer.security.model.User roleUser =
                    new com.company.project.configurer.security.model.User(resultUser.getUsername());
            result.put(tokenHeader, tokenUtils.generateToken(roleUser));
            return ResultGenerator.genSuccessResult().setData(result);
        } else {
            return ResultGenerator.genFailResult("用户名或密码错误！");
        }
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        String authToken = request.getHeader(this.tokenHeader);
        // 尝试拿 token 中的 username
        // 若是没有 token 或者拿 username 时出现异常，那么 username 为 null
        String username = this.tokenUtils.getUsernameFromToken(authToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return ResultGenerator.genSuccessResult(userDetails);
    }

    @PostMapping
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
