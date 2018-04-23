package com.company.project.modules.wechat.utils;

/**
 * 微信接口访问token 有效期7200秒，全局缓存access_token
 * 
 * @author yaoxinyu
 *
 */
public class WxAccessToken {

    private static volatile WxAccessToken instance;

    public static WxAccessToken getInstance() {
        if (instance == null) {
            synchronized (WxAccessToken.class) {
                // 未初始化，则初始instance变量
                if (instance == null) {
                    instance = new WxAccessToken();
                }
            }
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public long getLastTick() {
        return lastTick;
    }

    public void setLastTick(long lastTick) {
        this.lastTick = lastTick;
    }

    /**
     * 获取到的凭证
     */
    private String token;

    /**
     * 凭证有效时间，单位：秒
     */
    private int period = 7200;

    /**
     * 上一次获取凭证的时间戳
     */
    private long lastTick = 0;

}
