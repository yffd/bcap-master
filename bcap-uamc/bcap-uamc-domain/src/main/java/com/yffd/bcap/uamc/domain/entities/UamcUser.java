package com.yffd.bcap.uamc.domain.entities;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import lombok.Data;

@Data
public class UamcUser extends EntityObject {
    private static final long serialVersionUID = 2771217497705346815L;
    private String userId;//用户ID
    private String userName;//用户名称
    private String mobile;//移动电话号码
    private String email;//电子邮箱
    private String icon;//头像
    private String orgId;//所属机构ID


}
