package com.yffd.bcap.common.model.exception;

public class SysException extends BaseException {
    private static final long serialVersionUID = -1029919034705665705L;
    private static final String DEF_NAME = "system";

    public SysException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

    /**
     * 错误
     * @param msg 描述信息
     * @return
     */
    public static SysException ERROR(String msg) {
        return new SysException(null, "AA0100", msg);
    }

    /**
     * 方法调用参数错误
     * @param msg 描述信息
     * @return
     */
    public static SysException ERROR_PARAMS(String msg) {
        return new SysException(null, "AA0101", msg);
    }

    /**
     * 方法调用参数为空
     * @return
     */
    public static SysException ERROR_PARAMS_EMPTY() {
        return new SysException(null, "AA0102", "方法调用参数为空");
    }


    @Override
    public String getName() {
        return DEF_NAME;
    }
}
