package com.yffd.bcap.common.ddd.context;

import com.yffd.bcap.common.ddd.domain.data.DataObjectSupport;
import com.yffd.bcap.common.model.login.LoginInfo;

public abstract class BaseContext<E extends DataObjectSupport> implements IBaseCtx {
    private LoginInfo loginInfo;
    private E entity;

    public BaseContext(LoginInfo loginInfo, E entity) {
        this.loginInfo = loginInfo;
        this.entity = entity;
    }

    @Override
    public LoginInfo getLoginInfo() {
        return this.loginInfo;
    }

    public E getEntity() {
        return entity;
    }
}
