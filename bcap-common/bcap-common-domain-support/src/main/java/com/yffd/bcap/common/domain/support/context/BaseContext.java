package com.yffd.bcap.common.domain.support.context;

import com.yffd.bcap.common.domain.model.login.LoginInfo;

public abstract class BaseContext<E> {

    abstract E getEntity();

    abstract LoginInfo getLoginInfo();

}
