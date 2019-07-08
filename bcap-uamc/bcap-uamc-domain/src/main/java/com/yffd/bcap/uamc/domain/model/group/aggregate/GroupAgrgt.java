package com.yffd.bcap.uamc.domain.model.group.aggregate;

import com.yffd.bcap.common.ddd.domain.entity.aggregate.AggregateRoot;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.group.entity.GroupEntity;

public class GroupAgrgt extends AggregateRoot<GroupEntity> {
    private static final long serialVersionUID = -7522198672181255411L;

    public GroupAgrgt(SysOperator sysOperator, GroupEntity entity) {
        super(sysOperator, entity);
    }

    public GroupEntity enable() {
        this.getEntity().setGroupState(ActiveStateEnum.ACTIVE.getCode());
        return this.getEntity();
    }

    public GroupEntity disable() {
        this.getEntity().setGroupState(ActiveStateEnum.INACTIVE.getCode());
        return this.getEntity();
    }

}
