package com.yffd.bcap.uamc.domain.dataobject;

import com.yffd.bcap.common.support.model.DataObject;
import lombok.Data;

import java.util.Date;

@Data
public class UamcUserDO extends DataObject {
    private static final long serialVersionUID = 2771217497705346815L;
    private String userId;//用户ID
    private String userName;//用户名称
    private String mobile;//移动电话号码
    private String email;//电子邮箱
    private String icon;//头像
    private String orgId;//所属机构ID
    private String accountId;//账号ID
    private String accountPwd;//账号密码
    private String accountState;//账号状态，启用=enabled、停用=disabled
    private Integer accountLevel;//账号等级
    private Date loginTime;//登录时间
    private Date preLoginTime;//上次登录时间
    private String loginIP;//登录IP
    private Integer loginCount;//登录次数

}
