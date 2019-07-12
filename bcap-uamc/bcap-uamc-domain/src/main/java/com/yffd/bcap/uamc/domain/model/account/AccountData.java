package com.yffd.bcap.uamc.domain.model.account;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;
import lombok.Data;

import java.util.Date;

@Data
public class AccountData extends DataObjectSupport {
    private static final long serialVersionUID = 2771217497705346815L;
    private String acntId;//账号ID
    private String acntPwd;//账号密码
    private String acntState;//账号状态，启用=enabled、停用=disabled
    private String userId;//用户id
    private Date loginTime;//登录时间
    private Date loginTimeBefore;//上次登录时间
    private String loginIP;//登录IP
    private Integer loginCount;//登录次数
}
