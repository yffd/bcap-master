package com.yffd.bcap.uamc.domain.model.organization;

import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.exception.InvalidException;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.model.utils.BcapStringUtils;

public class OrgEntity extends EntityObject<OrgData> {
    public transient static final String ROOT_PARENT_ID = "ROOT";
    public transient static final String PATH_SEPARATION = "#";

    public OrgEntity(OrgData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getOrgId();
    }

    public OrgData addRoot() {
        this.data().setParentId(ROOT_PARENT_ID);
        this.data().setOrgPath(this.data().getOrgId() + PATH_SEPARATION);
        DataObjectHelper.initPropsForAdd(this.data(), this.sysOperator());
        return this.data();
    }

    public OrgData addChild(String parentId, String orgPath) {
        DataObjectHelper.initPropsForAdd(this.data(), this.sysOperator());
        this.data().setParentId(parentId);
        this.data().setOrgPath(orgPath + PATH_SEPARATION + this.data().getOrgId() + PATH_SEPARATION);
        return this.data();
    }

    public String exsistByParentId() {
        if (BcapStringUtils.isEmpty(this.data().getParentId()))
            throw InvalidException.PARAM_IS_EMPTY("数据实体父ID不能为空[" + this.getClass() + "]");
        return this.data().getParentId();
    }
}
