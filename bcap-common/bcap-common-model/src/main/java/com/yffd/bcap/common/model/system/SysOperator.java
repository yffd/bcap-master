package com.yffd.bcap.common.model.system;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class SysOperator implements Serializable {
    private static final long serialVersionUID = 6496376687587246819L;
    private String userId;
    private String userName;
    private String orgId;
    private String orgName;
    private Set<String> roles;
    private Set<String> resources;
    private Date operateTime;

    public SysOperator() {
    }

    public SysOperator(String userId, String userName, String orgId, String orgName, Set<String> roles, Set<String> resources, Date operateTime) {
        this.userId = userId;
        this.userName = userName;
        this.orgId = orgId;
        this.orgName = orgName;
        this.roles = roles;
        this.resources = resources;
        this.operateTime = operateTime;
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

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
