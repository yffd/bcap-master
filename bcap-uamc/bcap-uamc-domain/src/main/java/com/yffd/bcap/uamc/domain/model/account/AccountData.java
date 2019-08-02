package com.yffd.bcap.uamc.domain.model.account;

import com.yffd.bcap.common.ddd.domain.data.DataObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="uamc_account")
public class AccountData extends DataObject {
    private static final long serialVersionUID = 2771217497705346815L;
    @Id
    private String acntId;//账号ID
    private String acntPwd;//账号密码
    private String acntState;//账号状态
    private String userId;//用户id
    private Date loginTime;//登录时间
    private Date loginTimeBefore;//上次登录时间
    private String loginIP;//登录IP
    private Integer loginCount;//登录次数

    public String getAcntId() {
        return acntId;
    }

    public void setAcntId(String acntId) {
        this.acntId = acntId;
    }

    public String getAcntPwd() {
        return acntPwd;
    }

    public void setAcntPwd(String acntPwd) {
        this.acntPwd = acntPwd;
    }

    public String getAcntState() {
        return acntState;
    }

    public void setAcntState(String acntState) {
        this.acntState = acntState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginTimeBefore() {
        return loginTimeBefore;
    }

    public void setLoginTimeBefore(Date loginTimeBefore) {
        this.loginTimeBefore = loginTimeBefore;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}
