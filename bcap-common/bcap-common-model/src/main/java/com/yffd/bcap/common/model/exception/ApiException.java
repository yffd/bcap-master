package com.yffd.bcap.common.model.exception;

public class ApiException extends SysException {
    private static final long serialVersionUID = 1115614811704338422L;

    public ApiException(String code, String tip) {
        super(code, tip);
    }

    public ApiException(String code, String tip, Throwable cause) {
        super(code, tip, cause);
    }

    public static ApiException instance() {
        return new ApiException(SysExceptionEnum.API_EXCEPTION.getCode(), SysExceptionEnum.API_EXCEPTION.getTip());
    }

    public static ApiException instance(String tip) {
        return new ApiException(SysExceptionEnum.API_EXCEPTION.getCode(), tip);
    }

    public static ApiException instance(String tip, Throwable cause) {
        return new ApiException(SysExceptionEnum.API_EXCEPTION.getCode(), tip, cause);
    }


    public static ApiException API_EXCEPTION() {
        return new ApiException(SysExceptionEnum.API_EXCEPTION.getCode(), SysExceptionEnum.API_EXCEPTION.getTip());
    }

    public static ApiException API_NOT_FOUND() {
        return new ApiException(SysExceptionEnum.API_NOT_FOUND.getCode(), SysExceptionEnum.API_NOT_FOUND.getTip());
    }

    public static ApiException API_NOT_SUPPORTED_METHOD() {
        return new ApiException(SysExceptionEnum.API_NOT_SUPPORTED_METHOD.getCode(), SysExceptionEnum.API_NOT_SUPPORTED_METHOD.getTip());
    }

    public static ApiException API_NOT_SUPPORTED_MEDIA_TYPE() {
        return new ApiException(SysExceptionEnum.API_NOT_SUPPORTED_MEDIA_TYPE.getCode(),
                SysExceptionEnum.API_NOT_SUPPORTED_MEDIA_TYPE.getTip());
    }

    public static ApiException API_NOT_ACCEPTABLE_MEDIA_TYPE() {
        return new ApiException(SysExceptionEnum.API_NOT_ACCEPTABLE_MEDIA_TYPE.getCode(),
                SysExceptionEnum.API_NOT_ACCEPTABLE_MEDIA_TYPE.getTip());
    }

    public static ApiException API_MISSING_REQUEST_PARAMETER() {
        return new ApiException(SysExceptionEnum.API_MISSING_REQUEST_PARAMETER.getCode(),
                SysExceptionEnum.API_MISSING_REQUEST_PARAMETER.getTip());
    }

    public static ApiException API_NOT_SUPPORTED_CONVERSION() {
        return new ApiException(SysExceptionEnum.API_NOT_SUPPORTED_CONVERSION.getCode(),
                SysExceptionEnum.API_NOT_SUPPORTED_CONVERSION.getTip());
    }

    public static ApiException API_MESSAGE_NOT_READABLE() {
        return new ApiException(SysExceptionEnum.API_MESSAGE_NOT_READABLE.getCode(),
                SysExceptionEnum.API_MESSAGE_NOT_READABLE.getTip());
    }

    public static ApiException API_MESSAGE_NOT_WRITABLE() {
        return new ApiException(SysExceptionEnum.API_MESSAGE_NOT_WRITABLE.getCode(),
                SysExceptionEnum.API_MESSAGE_NOT_WRITABLE.getTip());
    }

    public static ApiException API_NOT_VALID_METHOD_ARGUMENT() {
        return new ApiException(SysExceptionEnum.API_NOT_VALID_METHOD_ARGUMENT.getCode(),
                SysExceptionEnum.API_NOT_VALID_METHOD_ARGUMENT.getTip());
    }

    public static ApiException API_MISSING_REQUEST_PART() {
        return new ApiException(SysExceptionEnum.API_MISSING_REQUEST_PART.getCode(),
                SysExceptionEnum.API_MISSING_REQUEST_PART.getTip());
    }

    public static ApiException API_ASYNC_REQUEST_TIMEOUT() {
        return new ApiException(SysExceptionEnum.API_ASYNC_REQUEST_TIMEOUT.getCode(),
                SysExceptionEnum.API_ASYNC_REQUEST_TIMEOUT.getTip());
    }

}
