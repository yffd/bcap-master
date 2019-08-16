package com.yffd.bcap.common.model.exception;

public class ServiceException extends SysException {
    private static final long serialVersionUID = -1626647183352145000L;

    public ServiceException(String code, String tip) {
        super(code, tip);
    }

    public ServiceException(String code, String tip, Throwable cause) {
        super(code, tip, cause);
    }

    public static ServiceException instance() {
        return new ServiceException(SysExceptionEnum.SERVICE_EXCEPTION.getCode(), SysExceptionEnum.SERVICE_EXCEPTION.getTip());
    }

    public static ServiceException instance(String tip) {
        return new ServiceException(SysExceptionEnum.SERVICE_EXCEPTION.getCode(), tip);
    }

    public static ServiceException instance(String tip, Throwable cause) {
        return new ServiceException(SysExceptionEnum.SERVICE_EXCEPTION.getCode(), tip, cause);
    }

}
