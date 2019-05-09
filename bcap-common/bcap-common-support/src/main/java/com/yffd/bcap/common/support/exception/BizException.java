package com.yffd.bcap.common.support.exception;

import com.yffd.bcap.common.domain.model.exception.BaseException;

public class BizException extends BaseException {
    private static final String DEF_NAME = "bcap-biz";

    public BizException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

    /**
     * 错误
     * @param msg 描述信息
     * @return
     */
    public static BizException ERROR(String msg) {
        return new BizException(null, "AA0100", msg);
    }

    /**
     * 方法调用参数错误
     * @param msg 描述信息
     * @return
     */
    public static BizException ERROR_PARAMS(String msg) {
        return new BizException(null, "AA0101", msg);
    }

    /**
     * 方法调用参数为空
     * @return
     */
    public static BizException ERROR_PARAMS_EMPTY() {
        return new BizException(null, "AA0102", "方法调用参数为空");
    }

    @Override
    public String getName() {
        return DEF_NAME;
    }
}
