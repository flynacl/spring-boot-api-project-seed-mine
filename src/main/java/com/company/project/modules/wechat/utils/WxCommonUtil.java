package com.company.project.modules.wechat.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.company.project.utils.Try;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class WxCommonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxCommonUtil.class);
    /**
     * access_token的接口地址（GET方法）
     */
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * jsapi_ticket的接口地址（GET方法）
     */
    private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    /**
     * 上传到微信服务器的媒体文件（GET方法）
     */
    private static final String MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

    private static final OkHttpClient CLIENT = new OkHttpClient();

    private static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = CLIENT.newCall(request).execute();
        Optional<String> data = Optional.ofNullable(response.body().string());
        return data.orElse("");
    }

    /**
     * URL编码（utf-8）
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取微信公众号关注用户的授权结果
     *
     * @param appId     公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code      授权返回的code
     * @return 授权结果
     */
    public static String getAuthResult(String appId, String appSecret, String code) throws IOException {
        // 拼接请求地址
        String requestUrl = ACCESS_TOKEN_URL;
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取授权结果
        Request request = new Request.Builder()
                .url(requestUrl)
                .build();
        Response response = CLIENT.newCall(request).execute();
        Optional<String> optional = Optional.ofNullable(response).map(Response::body).map(Try.of(ResponseBody::string, ""));
        return optional.orElse("");
    }

    /**
     * 发起http请求获取返回结果
     *
     * @param requestUrl 请求地址
     * @return
     */
    public static JSONObject httpRequest(String requestUrl) {
        JSONObject jsonObject = null;
        StringBuilder buffer = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 获取access_token
     *
     * @param appId     凭证
     * @param appSecret 密钥
     * @return true:成功 false:失败
     */
    public static boolean getAccessToken(String appId, String appSecret) {
        boolean ok = false;
        // 判断token是否已过期，未过期可以直接取值，过期则重新获取
        long lastTicket = WxAccessToken.getInstance().getLastTick();
        if (lastTicket > 0 && (System.currentTimeMillis() - lastTicket) < (WxAccessToken.getInstance().getPeriod() * 1000
                - 200000)) {
            return true;
        } else {
            // token过期，重新获取
            String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appId).replace("APPSECRET", appSecret);
            JSONObject jsonObject = null;
            // 如果请求成功
            if (null != jsonObject) {
                try {
                    WxAccessToken.getInstance().setToken(jsonObject.getString("access_token"));
                    WxAccessToken.getInstance().setPeriod(jsonObject.getIntValue("expires_in"));
                    WxAccessToken.getInstance().setLastTick(System.currentTimeMillis());
                    ok = true;
                } catch (JSONException e) {
                    // 获取token失败
                    LOGGER.error("获取token失败 errcode:" + jsonObject.getIntValue("errcode") + "errmsg:"
                            + jsonObject.getString("errmsg"));
                }
            }
        }

        return ok;
    }

    /**
     * 获取jsapi_ticket
     *
     * @param accessToken access_token值
     * @return true:成功 false:失败
     */
    public static boolean getJsapiTicket(String accessToken) {
        boolean ok = false;
        String requestUrl = JSAPI_TICKET_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = null;
        // 如果请求成功
        if (null != jsonObject) {
            try {
                WxJSapiTicket.getInstance().setTicket(jsonObject.getString("ticket"));
                WxJSapiTicket.getInstance().setPeriod(jsonObject.getIntValue("expires_in"));
                WxJSapiTicket.getInstance().setLastTick(System.currentTimeMillis());
                ok = true;
            } catch (JSONException e) {
                // 获取jsapi_ticket失败
                LOGGER.error("获取jsapi_ticket失败 errcode:" + jsonObject.getIntValue("errcode") + "errmsg:"
                        + jsonObject.getString("errmsg"));
            }
        }
        return ok;
    }

    /**
     * 获取媒体文件
     *
     * @param mediaId  上传到微信服务器的媒体文件id
     * @param token    接口访问凭证
     * @param filePath 文件路径
     */
    public static boolean downloadMediaFile(String mediaId, String token, String filePath) {
        boolean downloadSuc = false;
        String requestUrl = MEDIA_URL.replace("ACCESS_TOKEN", token).replace("MEDIA_ID", mediaId);
        HttpURLConnection conn = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = bis.read(buff, 0, 1024)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] filebyte = swapStream.toByteArray();
            // 保存图片
            File file = new File(filePath);
            if (!file.exists()) {
                File tmpFile = new File(file.getParent());
                if (!tmpFile.isDirectory()) {
                    tmpFile.mkdirs();
                }
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file);
            out.write(filebyte);
            out.flush();
            // 关闭流
            out.close();
            swapStream.close();

            downloadSuc = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return downloadSuc;
    }
}