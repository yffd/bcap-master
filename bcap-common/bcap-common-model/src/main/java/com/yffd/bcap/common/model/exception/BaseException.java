package com.yffd.bcap.common.model.exception;

public abstract class BaseException extends RuntimeException {
    private String name;    //系统名称
    private String code;    //异常编号
    private String msg;     //异常信息

    public BaseException(Throwable cause, String code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return String.format("%s--[%s(%s)]", this.getName(), this.getCode(), this.msg);
    }

    public abstract  String getName();

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
