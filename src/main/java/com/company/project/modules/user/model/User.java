package com.company.project.modules.user.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class User {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 上次密码修改日期
     */
    @Column(name = "last_password_change")
    private Date lastPasswordChange;

    /**
     * 账户可用
     */
    private Boolean enable;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取上次密码修改日期
     *
     * @return last_password_change - 上次密码修改日期
     */
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * 设置上次密码修改日期
     *
     * @param lastPasswordChange 上次密码修改日期
     */
    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    /**
     * 获取账户可用
     *
     * @return enable - 账户可用
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * 设置账户可用
     *
     * @param enable 账户可用
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}