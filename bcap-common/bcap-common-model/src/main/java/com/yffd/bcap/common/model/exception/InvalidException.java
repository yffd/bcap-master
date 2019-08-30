package com.yffd.bcap.common.model.exception;

public class InvalidException extends SysException {
    private static final long serialVersionUID = 8262173866930694925L;

    private InvalidException(String code, String tip) {
        super(code, tip);
    }

    private InvalidException(String code, String tip, Throwable cause) {
        super(code, tip, cause);
    }

    public static InvalidException instance(String tip) {
        return new InvalidException(SysExceptionEnum.SERVICE_EXCEPTION.getCode(), tip);
    }

    public static InvalidException PARAM_IS_EMPTY() {
        return new InvalidException(SysExceptionEnum.PARAM_IS_EMPTY.getCode(), SysExceptionEnum.PARAM_IS_EMPTY.getTip());
    }
    public static InvalidException PARAM_IS_EMPTY(String tip) {
        return new InvalidException(SysExceptionEnum.PARAM_IS_EMPTY.getCode(), tip);
    }

    public static InvalidException PARAM_IS_INVALID() {
        return new InvalidException(SysExceptionEnum.PARAM_IS_INVALID.getCode(), SysExceptionEnum.PARAM_IS_INVALID.getTip());
    }
    public static InvalidException PARAM_IS_INVALID(String tip) {
        return new InvalidException(SysExceptionEnum.PARAM_IS_INVALID.getCode(), tip);
    }

    public static InvalidException DATA_EXSIST() {
        return new InvalidException(SysExceptionEnum.DATA_EXSIST.getCode(), SysExceptionEnum.DATA_EXSIST.getTip());
    }
    public static InvalidException DATA_EXSIST(String tip) {
        return new InvalidException(SysExceptionEnum.DATA_EXSIST.getCode(), tip);
    }

    public static InvalidException DATA_NOT_EXSIST() {
        return new InvalidException(SysExceptionEnum.DATA_NOT_EXSIST.getCode(), SysExceptionEnum.DATA_NOT_EXSIST.getTip());
    }
    public static InvalidException DATA_NOT_EXSIST(String tip) {
        return new InvalidException(SysExceptionEnum.DATA_NOT_EXSIST.getCode(), tip);
    }

    public static InvalidException METHOD_UNSUPPORT() {
        return new InvalidException(SysExceptionEnum.METHOD_UNSUPPORT.getCode(), SysExceptionEnum.METHOD_UNSUPPORT.getTip());
    }

}
