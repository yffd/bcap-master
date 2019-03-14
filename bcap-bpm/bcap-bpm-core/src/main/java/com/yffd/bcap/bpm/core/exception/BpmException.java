package com.yffd.bcap.bpm.core.exception;

public class BpmException extends RuntimeException {

    private BpmException(String msg, Throwable t) {
        super(msg, t);
    }

    public static BpmException newInstance(String msg, Throwable t) {
        return new BpmException(msg, t);
    }

    /**
     * 方法调用业务逻辑错误
     * @param msg 描述信息
     * @return
     */
    public static BpmException BIZ_ERROR(String msg) {
        return new BpmException(msg, null);
    }

    /**
     * 方法调用参数错误
     * @param msg 描述信息
     * @return
     */
    public static BpmException PARAMS_ERROR(String msg) {
        return new BpmException(msg, null);
    }

    /**
     * 方法调用参数为空
     * @return
     */
    public static BpmException PARAMS_IS_EMPTY() {
        return new BpmException("方法调用参数为空", null);
    }



}
