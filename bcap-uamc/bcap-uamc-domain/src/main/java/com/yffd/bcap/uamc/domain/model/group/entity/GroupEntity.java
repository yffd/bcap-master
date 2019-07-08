package com.yffd.bcap.uamc.domain.model.group.entity;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import lombok.Data;

@Data
public class GroupEntity extends EntityObject {
    private static final long serialVersionUID = 8917064942315854878L;
    private String groupId;//组ID
    private String groupName;//组名称
    private String parentId;//父组ID
    private String groupState;//状态，启用=enabled、停用=disabled
    private String remark;//组描述

    public void enable() {
        this.groupState = ActiveStateEnum.ACTIVE.getCode();
    }

    public void disable() {
        this.groupState = ActiveStateEnum.INACTIVE.getCode();
    }
}
