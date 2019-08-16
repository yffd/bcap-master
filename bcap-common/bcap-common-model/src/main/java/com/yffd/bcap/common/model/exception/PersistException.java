package com.yffd.bcap.common.model.exception;

public class PersistException extends SysException {
    private static final long serialVersionUID = -3457933413197980781L;
    private String sqlId = "";

    public PersistException(String code, String tip) {
        super(code, tip);
    }

    public PersistException(String code, String tip, Throwable cause) {
        super(code, tip, cause);
    }

    public PersistException(String code, String tip, String sqlId) {
        super(code, tip);
        this.sqlId = sqlId;
    }

    public PersistException(String code, String tip, String sqlId, Throwable cause) {
        super(code, tip, cause);
        this.sqlId = sqlId;
    }

    public static PersistException instance() {
        return new PersistException(SysExceptionEnum.DB_EXCEPTION.getCode(), SysExceptionEnum.DB_EXCEPTION.getTip());
    }

    public static PersistException instance(String tip) {
        return new PersistException(SysExceptionEnum.DB_EXCEPTION.getCode(), tip);
    }

    public static PersistException instance(String tip, Throwable cause) {
        return new PersistException(SysExceptionEnum.DB_EXCEPTION.getCode(), tip, cause);
    }


    /**
     * 数据库操作，插入数据为空
     */
    public static PersistException DB_INSERT_NULL(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_INSERT_NULL.getCode(),
                SysExceptionEnum.DB_INSERT_NULL.getTip(), sqlId);
    }

    /**
     * 数据库操作，插入返回结果为0
     */
    public static PersistException DB_INSERT_RESULT_0(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_INSERT_RESULT_0.getCode(),
                SysExceptionEnum.DB_INSERT_RESULT_0.getTip(), sqlId);
    }

    /**
     * 数据库操作，更新数据为空
     */
    public static PersistException DB_UPDATE_NULL(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_UPDATE_NULL.getCode(),
                SysExceptionEnum.DB_UPDATE_NULL.getTip(), sqlId);
    }

    /**
     * 数据库操作，更新返回结果为0
     */
    public static PersistException DB_UPDATE_RESULT_0(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_UPDATE_RESULT_0.getCode(),
                SysExceptionEnum.DB_UPDATE_RESULT_0.getTip(), sqlId);
    }

    /**
     * 数据库操作，删除数据为空
     */
    public static PersistException DB_DELETE_NULL(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_DELETE_NULL.getCode(),
                SysExceptionEnum.DB_DELETE_NULL.getTip(), sqlId);
    }

    /**
     * 数据库操作，删除返回结果为0
     */
    public static PersistException DB_DELETE_RESULT_0(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_DELETE_RESULT_0.getCode(),
                SysExceptionEnum.DB_DELETE_RESULT_0.getTip(), sqlId);
    }

    /**
     * 数据库操作，查询条件为空
     */
    public static PersistException DB_SELECT_BY_NULL(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_SELECT_BY_NULL.getCode(),
                SysExceptionEnum.DB_SELECT_BY_NULL.getTip(), sqlId);
    }

    /**
     * 数据库操作，单条查询返回结果为多条
     */
    public static PersistException DB_SELECT_ONE_RESULT_MULTI(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_SELECT_ONE_RESULT_MULTI.getCode(),
                SysExceptionEnum.DB_SELECT_ONE_RESULT_MULTI.getTip(), sqlId);
    }

    /**
     * 数据库操作，序列生成超时
     */
    public static PersistException DB_GET_SEQ_NEXT_VALUE_ERROR(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_GET_SEQ_VALUE_TIMEOUT.getCode(),
                SysExceptionEnum.DB_GET_SEQ_VALUE_TIMEOUT.getTip(), sqlId);
    }

    /**
     * 数据库操作，查询返回结果为空
     */
    public static PersistException DB_SELECT_NULL(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_SELECT_RESULT_NULL.getCode(),
                SysExceptionEnum.DB_SELECT_RESULT_NULL.getTip(), sqlId);
    }

    /**
     * sqlId 不能为空
     */
    public static PersistException DB_SQL_ID_EMPTY() {
        return new PersistException(SysExceptionEnum.DB_SQL_ID_EMPTY.getCode(),
                SysExceptionEnum.DB_SQL_ID_EMPTY.getTip(), "");
    }

    public static PersistException DB_UNSUPPORTTYPE(String sqlId) {
        return new PersistException(SysExceptionEnum.DB_UNSUPPORTTYPE.getCode(),
                SysExceptionEnum.DB_UNSUPPORTTYPE.getTip(), sqlId);
    }

}
