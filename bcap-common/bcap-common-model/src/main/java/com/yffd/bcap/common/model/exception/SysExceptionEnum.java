package com.yffd.bcap.common.model.exception;

public enum SysExceptionEnum {
    SYS_EXCEPTION("SYS000", "系统异常"),
    //持久化层异常相关
    DB_EXCEPTION("PPP000", "持久化异常"),
    DB_INSERT_NULL("PPP001", "持久化操作，插入数据为空"),
    DB_INSERT_RESULT_0("PPP002", "持久化操作，插入返回结果为0"),
    DB_UPDATE_NULL("PPP003", "持久化操作，更新数据为空"),
    DB_UPDATE_RESULT_0("PPP004", "持久化操作，更新返回结果为0"),
    DB_DELETE_NULL("PPP005", "持久化操作，删除数据为空"),
    DB_DELETE_RESULT_0("PPP006", "持久化操作，删除返回结果为0"),
    DB_SELECT_BY_NULL("PPP007", "持久化操作，查询条件为空"),
    DB_SELECT_ONE_RESULT_MULTI("PPP008", "持久化操作，单条查询返回结果为多条"),
    DB_SELECT_RESULT_NULL("PPP009", "持久化操作，查询返回结果为空"),
    DB_SQL_ID_EMPTY("PPP010", "sqlId不能为空"),
    DB_GET_SEQ_VALUE_TIMEOUT("PPP011", "持久化操作，序列生成超时"),
    DB_UNSUPPORTTYPE("PPP012", "持久化操作，不支持类型"),

    //service层异常相关
    SERVICE_EXCEPTION("SSS000", "服务接口异常"),

    //应用接口异常相关
    API_EXCEPTION("AAA000", "应用接口异常"),
    API_NOT_FOUND("AAA001", "请求资源未找到"),
    API_NOT_SUPPORTED_METHOD("AAA002", "请求方法暂时未支持"),
    API_NOT_SUPPORTED_MEDIA_TYPE("AAA003", "请求媒体类型未支持"),
    API_NOT_ACCEPTABLE_MEDIA_TYPE("AAA004", "服务器响应的媒体类型不符客户端期望的媒体类型"),
    API_MISSING_REQUEST_PARAMETER("AAA005", "请求必要参数丢失"),
    API_NOT_SUPPORTED_CONVERSION("AAA006", "请求数据转换失败"),
    API_MESSAGE_NOT_READABLE("AAA007", "消息内容不可读"),
    API_MESSAGE_NOT_WRITABLE("AAA008", "消息内容不可写"),
    API_NOT_VALID_METHOD_ARGUMENT("AAA009", "请求方法参数校验失败"),
    API_MISSING_REQUEST_PART("AAA010", "文件上传失败"),
    API_ASYNC_REQUEST_TIMEOUT("AAA011", "异步请求超时"),

    //通用异常
    PARAM_IS_EMPTY("CCC999", "参数为空"),
    PARAM_IS_INVALID("CCC998", "参数无效"),
    DATA_EXSIST("CCC989", "数据已存在"),
    DATA_NOT_EXSIST("CCC988", "数据不存在"),
    ;
    private String code; //编号
    private String tip; //提示

    SysExceptionEnum(String code, String tip) {
        this.code = code;
        this.tip = tip;
    }

    public String getCode() {
        return code;
    }

    public String getTip() {
        return tip;
    }

    public String getTipFmt(String msg) {
        return String.format(this.getTip() + "[%s]", msg);
    }
}
