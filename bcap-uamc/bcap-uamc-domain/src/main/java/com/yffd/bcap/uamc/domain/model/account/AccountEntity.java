package com.yffd.bcap.uamc.domain.model.account;

import com.yffd.bcap.common.ddd.domain.entity.EntityObjectSupport;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.common.support.util.StringUtils;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;

public class AccountEntity extends EntityObjectSupport<AccountData> {

    public AccountEntity(AccountData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return null;
    }

    public AccountData active() {
        //1.账号启用、停用，是系统行为，用于控制账号能否登录系统；
        if (StringUtils.isBlank(this.data().getAcntId()))
            throw BcapValidateException.ERROR_PARAMS("启用失败，数据实体ID不能为空[" + this.data().getClass() + "]");
        this.data().setAcntState(ActiveStateEnum.ACTIVE.getCode());
        this.initPropsForUpdate();
        return this.data();
    }

    public AccountData deactive() {
        //1.账号启用、停用，是系统行为，用于控制账号能否登录系统；
        if (StringUtils.isBlank(this.data().getAcntId()))
            throw BcapValidateException.ERROR_PARAMS("启用失败，数据实体ID不能为空[" + this.data().getClass() + "]");
        this.data().setAcntState(ActiveStateEnum.DEACTIVE.getCode());
        this.initPropsForUpdate();
        return this.data();
    }

    public AccountData recordLoginInfo() {
        if (StringUtils.isBlank(this.data().getAcntId()))
            throw BcapValidateException.ERROR_PARAMS("启用失败，数据实体ID不能为空[" + this.data().getClass() + "]");
        this.initPropsForUpdate();
        return this.data();
    }

}
