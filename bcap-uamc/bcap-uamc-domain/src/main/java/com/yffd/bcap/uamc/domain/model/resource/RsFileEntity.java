package com.yffd.bcap.uamc.domain.model.resource;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.system.SysOperator;

public class RsFileEntity extends EntityObject<RsMenuData> {

    public RsFileEntity(RsMenuData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getMenuId();
    }
}
