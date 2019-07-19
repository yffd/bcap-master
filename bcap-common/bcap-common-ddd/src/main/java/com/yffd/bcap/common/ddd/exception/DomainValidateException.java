package com.yffd.bcap.common.ddd.exception;

import com.yffd.bcap.common.model.exception.BaseException;

public class DomainValidateException extends BaseException {
    private static final long serialVersionUID = -5688961000740238179L;
    private static final String DEF_NAME = "domain-validate";

    public DomainValidateException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

    /**
     * 错误
     * @param msg 描述信息
     * @return
     */
    public static DomainValidateException ERROR(String msg) {
        return new DomainValidateException(null, "AA0100", msg);
    }

    /**
     * 方法调用参数错误
     * @param msg 描述信息
     * @return
     */
    public static DomainValidateException ERROR_PARAMS(String msg) {
        return new DomainValidateException(null, "AA0101", msg);
    }

    /**
     * 方法调用参数为空
     * @return
     */
    public static DomainValidateException ERROR_PARAMS_EMPTY() {
        return new DomainValidateException(null, "AA0102", "方法调用参数为空");
    }

    @Override
    public String getName() {
        return DEF_NAME;
    }
}
