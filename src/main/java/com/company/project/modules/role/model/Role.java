package com.company.project.modules.role.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Role {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    private String auth;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return auth
     */
    public String getAuth() {
        return auth;
    }

    /**
     * @param auth
     */
    public void setAuth(String auth) {
        this.auth = auth;
    }
}