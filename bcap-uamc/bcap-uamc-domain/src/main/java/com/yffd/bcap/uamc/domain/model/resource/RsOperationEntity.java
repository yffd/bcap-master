package com.yffd.bcap.uamc.domain.model.resource;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.system.SysOperator;

public class RsOperationEntity extends EntityObject<RsOperationData> {

    public RsOperationEntity(RsOperationData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getOprtId();
    }

    public String delPmsByOprtId() {
        return this.data().getOprtId();
    }
}
