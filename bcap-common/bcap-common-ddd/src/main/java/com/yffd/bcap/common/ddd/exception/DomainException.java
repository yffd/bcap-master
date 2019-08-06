package com.yffd.bcap.common.ddd.exception;

import com.yffd.bcap.common.model.exception.BaseException;

public class DomainException extends BaseException {
    private static final long serialVersionUID = -5688961000740238179L;
    private static final String DEF_NAME = "domain";

    public DomainException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

    /**
     * 错误
     * @param msg 描述信息
     * @return
     */
    public static DomainException ERROR(String msg) {
        return new DomainException(null, "AA0100", msg);
    }

    /**
     * 方法调用参数错误
     * @param msg 描述信息
     * @return
     */
    public static DomainException ERROR_PARAMS(String msg) {
        return new DomainException(null, "AA0101", msg);
    }

    /**
     * 方法调用参数为空
     * @return
     */
    public static DomainException ERROR_PARAMS_EMPTY() {
        return new DomainException(null, "AA0102", "方法调用参数为空");
    }

    @Override
    public String getName() {
        return DEF_NAME;
    }
}
