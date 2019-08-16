package com.yffd.bcap.common.model.exception;

public class SysException extends RuntimeException {
    private static final long serialVersionUID = -3218233739638650518L;

    private String code;//异常编号
    private String tip;//异常提示

    public SysException(String code, String tip) {
        this.code = code;
        this.tip = tip;
    }

    public SysException(String code, String tip, Throwable cause) {
        super(String.format("code:%s, tip:%s", code, tip), cause);
        this.code = code;
        this.tip = tip;
    }

    @Override
    public String getMessage() {
        return String.format("code:%s, tip:%s", this.getCode(), this.getTip());
    }

    public String getCode() {
        return code;
    }

    public String getTip() {
        return tip;
    }

    public static SysException instance() {
        return new SysException(SysExceptionEnum.SYS_EXCEPTION.getCode(), SysExceptionEnum.SYS_EXCEPTION.getTip());
    }

    public static SysException instance(String tip) {
        return new SysException(SysExceptionEnum.SYS_EXCEPTION.getCode(), tip);
    }

    public static SysException instance(String tip, Throwable cause) {
        return new SysException(SysExceptionEnum.SYS_EXCEPTION.getCode(), tip, cause);
    }
}
