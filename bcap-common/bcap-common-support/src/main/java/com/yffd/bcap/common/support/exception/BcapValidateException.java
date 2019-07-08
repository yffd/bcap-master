package com.yffd.bcap.common.support.exception;

import com.yffd.bcap.common.model.exception.BaseException;

public class BcapValidateException extends BaseException {
    private static final long serialVersionUID = -2455105409986555065L;
    private static final String DEF_NAME = "bcap-validate";

    public BcapValidateException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

    /**
     * 错误
     * @param msg 描述信息
     * @return
     */
    public static BcapValidateException ERROR(String msg) {
        return new BcapValidateException(null, "AA0100", msg);
    }

    /**
     * 方法调用参数错误
     * @param msg 描述信息
     * @return
     */
    public static BcapValidateException ERROR_PARAMS(String msg) {
        return new BcapValidateException(null, "AA0101", msg);
    }

    /**
     * 方法调用参数为空
     * @return
     */
    public static BcapValidateException ERROR_PARAMS_EMPTY() {
        return new BcapValidateException(null, "AA0102", "方法调用参数为空");
    }

    @Override
    public String getName() {
        return DEF_NAME;
    }
}
