package com.yffd.bcap.uamc.domain.model.permission;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.system.SysOperator;

public class PermissionEntity extends EntityObject<PermissionData> {

    public PermissionEntity(PermissionData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getPmsId();
    }

}
