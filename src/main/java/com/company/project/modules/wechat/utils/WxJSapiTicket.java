package com.company.project.modules.wechat.utils;

/**
 * 微信JS-SDK使用的jsapi_ticket 有效期7200秒，全局缓存jsapi_ticket
 *
 * @author yaoxinyu
 */
public class WxJSapiTicket {

    private static volatile WxJSapiTicket instance;

    public static WxJSapiTicket getInstance() {
        if (instance == null) {
            synchronized (WxJSapiTicket.class) {
                // 未初始化，则初始instance变量
                if (instance == null) {
                    instance = new WxJSapiTicket();
                }
            }
        }
        return instance;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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
     * 获取到的ticket
     */
    private String ticket;

    /**
     * ticket有效时间，单位：秒
     */
    private int period = 7200;

    /**
     * 上一次获取到ticket的时间戳
     */
    private long lastTick = 0;

}
