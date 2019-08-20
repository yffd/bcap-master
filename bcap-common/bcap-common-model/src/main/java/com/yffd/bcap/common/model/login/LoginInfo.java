package com.yffd.bcap.common.model.login;

import java.io.Serializable;
import java.util.Set;

@Deprecated
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 3779269730581980785L;
    private String userId;
    private String userName;
    private String orgId;
    private String orgName;
    private Set<String> roles;
    private Set<String> resources;

    public LoginInfo() {
    }

    public LoginInfo(String userId, String userName, String orgId, String orgName, Set<String> roles, Set<String> resources) {
        this.userId = userId;
        this.userName = userName;
        this.orgId = orgId;
        this.orgName = orgName;
        this.roles = roles;
        this.resources = resources;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getResources() {
        return resources;
    }

    public void setResources(Set<String> resources) {
        this.resources = resources;
    }
}
