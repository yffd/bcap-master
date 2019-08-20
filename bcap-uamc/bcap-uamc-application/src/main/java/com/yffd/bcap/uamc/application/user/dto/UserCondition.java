package com.yffd.bcap.uamc.application.user.dto;

import java.io.Serializable;
import java.util.Date;

public class UserCondition implements Serializable {
    private static final long serialVersionUID = 2410985060072197079L;
    private String userId;//用户ID
    private String userName;//用户名称
    private String mobile;//移动电话号码
    private String email;//电子邮箱
    private String orgId;//所属机构ID
    private Date startTime;
    private Date endTime;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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
