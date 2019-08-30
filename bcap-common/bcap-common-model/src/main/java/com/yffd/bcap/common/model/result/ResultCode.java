package com.yffd.bcap.common.model.result;

public enum ResultCode {
    SUCCESS(1, "成功"),
    FAIL(2, "失败"),

    /** 参数错误：1000-1999 */
    PARAM_IS_EMPTY(1001, "参数为空"),
    PARAM_IS_INVALID(1002, "参数无效"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /** 数据错误：2000-2999 */
    DATA_EXSIST(2001, "数据已存在"),
    DATA_NOT_EXSIST(2002, "数据不存在"),
    DATA_IS_NULL(2003, "数据为空"),


    /** 持久化错误：4000-4999 */
    DB_INSERT_NULL(4001, "持久化操作，插入数据为空"),
    DB_INSERT_RESULT_0(4002, "持久化操作，插入返回结果为0"),
    DB_UPDATE_NULL(4003, "持久化操作，更新数据为空"),
    DB_UPDATE_RESULT_0(4004, "持久化操作，更新返回结果为0"),
    DB_DELETE_NULL(4005, "持久化操作，删除数据为空"),
    DB_DELETE_RESULT_0(4006, "持久化操作，删除返回结果为0"),
    DB_SELECT_BY_NULL(4007, "持久化操作，查询条件为空"),
    DB_SELECT_ONE_RESULT_MULTI(4008, "持久化操作，单条查询返回结果为多条"),
    DB_SELECT_RESULT_NULL(4009, "持久化操作，查询返回结果为空"),
    DB_SQL_ID_EMPTY(4010, "sqlId不能为空"),
    DB_GET_SEQ_VALUE_TIMEOUT(4011, "持久化操作，序列生成超时"),
    DB_UNSUPPORTTYPE(4012, "持久化操作，不支持类型"),

    /** 应用接口错误：5000-5999 */
    API_NOT_FOUND(5001, "请求资源未找到"),
    API_NOT_SUPPORTED_METHOD(5002, "请求方法暂时未支持"),
    API_NOT_SUPPORTED_MEDIA_TYPE(5003, "请求媒体类型未支持"),
    API_NOT_ACCEPTABLE_MEDIA_TYPE(5004, "服务器响应的媒体类型不符客户端期望的媒体类型"),
    API_MISSING_REQUEST_PARAMETER(5005, "请求必要参数丢失"),
    API_NOT_SUPPORTED_CONVERSION(5006, "请求数据转换失败"),
    API_MESSAGE_NOT_READABLE(5007, "消息内容不可读"),
    API_MESSAGE_NOT_WRITABLE(5008, "消息内容不可写"),
    API_NOT_VALID_METHOD_ARGUMENT(5009, "请求方法参数校验失败"),
    API_UPLOAD_FILE_ERROR(5010, "文件上传失败"),
    API_ASYNC_REQUEST_TIMEOUT(5011, "异步请求超时"),

    /** 其它错误：9000-9999 */
    OPERATION_EXPIRE(9991, "操作过期"),
    OPERATION_UNSUPPORT(9992, "操作不支持"),
    ;
    private Integer code;
    private String tip;

    ResultCode(Integer code, String tip) {
        this.code = code;
        this.tip = tip;
    }

    public Integer code() {
        return code;
    }

    public String tip() {
        return tip;
    }

}
