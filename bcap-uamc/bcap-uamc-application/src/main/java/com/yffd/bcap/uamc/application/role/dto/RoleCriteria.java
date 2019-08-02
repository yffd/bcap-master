package com.yffd.bcap.uamc.application.role.dto;

import java.io.Serializable;
import java.util.Date;

public class RoleCriteria implements Serializable {
    private static final long serialVersionUID = 2096611520136024618L;
    private String roleName;//角色名称
    private String roleState;//状态
    private Date startTime;
    private Date endTime;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        this.roleState = roleState;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
