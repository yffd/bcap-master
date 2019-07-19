package com.yffd.bcap.common.support.exception;

import com.yffd.bcap.common.model.exception.BaseException;

public class BcapMybatisException extends BaseException {
    private static final long serialVersionUID = -6653933750953804185L;
    private static final String DEF_NAME = "bcap-mybatis";
    private String sqlId = "";

    public BcapMybatisException(Throwable cause, String code, String msg) {
        super(cause, code, msg);
    }

    public BcapMybatisException(Throwable cause, String code, String msg, String sqlId) {
        super(cause, code, msg);
        this.sqlId = sqlId;
    }

    /**
     * 错误
     */
    public static BcapMybatisException DB_ERROR(String msg) {
        return new BcapMybatisException(null, "DD0000", msg);
    }

    /**
     * 错误
     */
    public static BcapMybatisException DB_ERROR(Throwable t, String msg) {
        return new BcapMybatisException(t, "DD0000", msg);
    }

    /**
     * 数据库操作，插入数据为空
     */
    public static BcapMybatisException DB_INSERT_NULL(String statement) {
        return new BcapMybatisException(null, "DD0100", "数据库操作，插入数据为空", statement);
    }

    /**
     * 数据库操作，插入返回结果为0
     */
    public static BcapMybatisException DB_INSERT_RESULT_0(String statement) {
        return new BcapMybatisException(null, "DDAA01","数据库操作，插入返回结果为0", statement);
    }

    /**
     * 数据库操作，更新数据为空
     */
    public static BcapMybatisException DB_UPDATE_NULL(String statement) {
        return new BcapMybatisException(null, "DDUU01", "数据库操作，更新数据为空", statement);
    }

    /**
     * 数据库操作，更新返回结果为0
     */
    public static BcapMybatisException DB_UPDATE_RESULT_0(String statement) {
        return new BcapMybatisException(null, "DDUU02", "数据库操作，更新返回结果为0", statement);
    }

    /**
     * 数据库操作，删除数据为空
     */
    public static BcapMybatisException DB_DELETE_NULL(String statement) {
        return new BcapMybatisException(null, "DDRR01", "数据库操作，删除数据为空");
    }

    /**
     * 数据库操作，删除返回结果为0
     */
    public static BcapMybatisException DB_DELETE_RESULT_0(String statement) {
        return new BcapMybatisException(null, "DDRR02", "数据库操作，删除返回结果为0", statement);
    }

    /**
     * 数据库操作，查询条件为空
     */
    public static BcapMybatisException DB_SELECT_BY_NULL(String statement) {
        return new BcapMybatisException(null, "DDSS01", "数据库操作，查询条件为空", statement);
    }

    /**
     * 数据库操作，单条查询返回结果为多条
     */
    public static BcapMybatisException DB_SELECT_ONE_RESULT_MULTI(String statement) {
        return new BcapMybatisException(null, "DDSS02", "数据库操作，单条查询返回结果为多条", statement);
    }

    /**
     * 数据库操作，序列生成超时
     */
    public static BcapMybatisException DB_GET_SEQ_NEXT_VALUE_ERROR(String statement) {
        return new BcapMybatisException(null, "DDSS03", "数据库操作，序列生成超时", statement);
    }

    /**
     * 数据库操作，查询条件为空
     */
    public static BcapMybatisException DB_SELECT_NULL(String statement) {
        return new BcapMybatisException(null, "DDSS04", "数据库操作，查询条件为空", statement);
    }

    /**
     * sqlId 不能为空
     */
    public static BcapMybatisException DB_SQL_ID_EMPTY(String... msg) {
        String tmp = "sqlId 不能为空";
        if (null!=msg[0] && !"".equals(msg[0].trim())) tmp = msg[0];
        return new BcapMybatisException(null, "DD1000", tmp, "");
    }

    public static BcapMybatisException DB_UNSUPPORTTYPE(String statement) {
        return new BcapMybatisException(null, "DD1002", "数据库操作，不支持类型", statement);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "--sql=" + this.sqlId;
    }

    @Override
    public String getName() {
        return DEF_NAME;
    }
}
