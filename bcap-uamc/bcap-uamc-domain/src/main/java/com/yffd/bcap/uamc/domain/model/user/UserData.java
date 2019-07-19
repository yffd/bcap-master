package com.yffd.bcap.uamc.domain.model.user;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_user")
public class UserData extends DataObjectSupport {
    private static final long serialVersionUID = 926423415872727207L;
    @Id
    private String userId;//用户ID
    private String userName;//用户名称
    private String mobile;//移动电话号码
    private String email;//电子邮箱
    private String icon;//头像
    private String orgId;//所属机构ID

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
