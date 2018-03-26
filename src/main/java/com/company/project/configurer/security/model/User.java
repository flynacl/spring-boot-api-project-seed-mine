package com.company.project.configurer.security.model;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 1:45
 */
public class User implements LoginDetail, TokenDetail {

    private String userId;
    private String username;
    private String password;
    private String authorities;
    private Long lastPasswordChange;
    private char enable;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAuthorities() {
        return authorities;
    }

    public User setAuthorities(String authorities) {
        this.authorities = authorities;
        return this;
    }

    public Long getLastPasswordChange() {
        return lastPasswordChange;
    }

    public User setLastPasswordChange(Long lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
        return this;
    }

    public User setEnable(char enable) {
        this.enable = enable;
        return this;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean enable() {
        return this.enable == '1';
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities='" + authorities + '\'' +
                ", lastPasswordChange=" + lastPasswordChange +
                ", enable=" + enable +
                '}';
    }
}
