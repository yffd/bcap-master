package com.yffd.bcap.uamc.domain.model.group.aggregate;

import com.yffd.bcap.common.ddd.domain.entity.aggregate.AggregateRoot;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.entities.UamcPermission;
import com.yffd.bcap.uamc.domain.model.group.entity.GroupEntity;

import java.util.List;

public class GroupPermissionsAgrgt extends AggregateRoot<GroupEntity> {
    private static final long serialVersionUID = 1920135838516910705L;

    private List<UamcPermission> permissions;

    public GroupPermissionsAgrgt(SysOperator sysOperator, GroupEntity entity) {
        super(sysOperator, entity);
    }

    public GroupPermissionsAgrgt(SysOperator sysOperator, GroupEntity entity, List<UamcPermission> permissions) {
        super(sysOperator, entity);
        this.permissions = permissions;
    }
}
