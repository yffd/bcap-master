package com.yffd.bcap.uamc.domain.model.user;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;
import lombok.Data;

@Data
public class UserData extends DataObjectSupport {
    private static final long serialVersionUID = 926423415872727207L;
    private String userId;//用户ID
    private String userName;//用户名称
    private String mobile;//移动电话号码
    private String email;//电子邮箱
    private String icon;//头像
    private String orgId;//所属机构ID
}
