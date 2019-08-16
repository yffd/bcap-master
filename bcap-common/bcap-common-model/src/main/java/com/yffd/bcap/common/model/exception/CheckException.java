package com.yffd.bcap.common.model.exception;

public class CheckException extends SysException {
    private static final long serialVersionUID = 8262173866930694925L;

    private CheckException(String code, String tip) {
        super(code, tip);
    }

    private CheckException(String code, String tip, Throwable cause) {
        super(code, tip, cause);
    }

    public static CheckException instance(String tip) {
        return new CheckException(SysExceptionEnum.SERVICE_EXCEPTION.getCode(), tip);
    }

    public static CheckException PARAM_IS_EMPTY() {
        return new CheckException(SysExceptionEnum.PARAM_IS_EMPTY.getCode(), SysExceptionEnum.PARAM_IS_EMPTY.getTip());
    }
    public static CheckException PARAM_IS_EMPTY(String tip) {
        return new CheckException(SysExceptionEnum.PARAM_IS_EMPTY.getCode(), tip);
    }

    public static CheckException PARAM_IS_INVALID() {
        return new CheckException(SysExceptionEnum.PARAM_IS_INVALID.getCode(), SysExceptionEnum.PARAM_IS_INVALID.getTip());
    }
    public static CheckException PARAM_IS_INVALID(String tip) {
        return new CheckException(SysExceptionEnum.PARAM_IS_INVALID.getCode(), tip);
    }

    public static CheckException DATA_EXSIST() {
        return new CheckException(SysExceptionEnum.DATA_EXSIST.getCode(), SysExceptionEnum.DATA_EXSIST.getTip());
    }
    public static CheckException DATA_EXSIST(String tip) {
        return new CheckException(SysExceptionEnum.DATA_EXSIST.getCode(), tip);
    }

    public static CheckException DATA_NOT_EXSIST() {
        return new CheckException(SysExceptionEnum.DATA_NOT_EXSIST.getCode(), SysExceptionEnum.DATA_NOT_EXSIST.getTip());
    }
    public static CheckException DATA_NOT_EXSIST(String tip) {
        return new CheckException(SysExceptionEnum.DATA_NOT_EXSIST.getCode(), tip);
    }

}
