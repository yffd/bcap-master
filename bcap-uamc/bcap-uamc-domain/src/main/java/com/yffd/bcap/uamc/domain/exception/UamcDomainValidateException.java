package com.yffd.bcap.uamc.domain.exception;

import com.yffd.bcap.common.ddd.exception.DomainException;

public class UamcDomainValidateException extends DomainException {
    private static final String DEF_NAME = "uamc-domain-validate";

    public UamcDomainValidateException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

    @Override
    public String getName() {
        return DEF_NAME;
    }
}
