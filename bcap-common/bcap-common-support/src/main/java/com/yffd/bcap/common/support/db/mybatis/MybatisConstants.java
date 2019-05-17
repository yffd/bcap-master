package com.yffd.bcap.common.support.db.mybatis;

public class MybatisConstants {
    /** mapper xml 中的SQL ID，即statement=insertOneBy */
    public static final String SQL_ID_INSERT_ONE_BY = "insertOneBy";
    /** mapper xml 中的SQL ID，即statement=insertBatchBy */
    public static final String SQL_ID_INSERT_BATCH_BY = "insertBatchBy";
    /** mapper xml 中的SQL ID，即statement=updateBy */
    public static final String SQL_ID_UPDATE_BY = "updateBy";
    /** mapper xml 中的SQL ID，即statement=deleteBy */
    public static final String SQL_ID_DELETE_BY = "deleteBy";
    /** mapper xml 中的SQL ID，即statement=selectOneBy */
    public static final String SQL_ID_SELECT_ONE_BY = "selectOneBy";
    /** mapper xml 中的SQL ID，即statement=selectCountBy */
    public static final String SQL_ID_SELECT_COUNT_BY = "selectCountBy";
    /** mapper xml 中的SQL ID，即statement=selectListBy */
    public static final String SQL_ID_SELECT_LIST_BY = "selectListBy";

    // 多参数时，参数名称
    public static final String PARAM_NAME_ENTITY = "entity";
    public static final String PARAM_NAME_ENTITY_OLD = "entityOld";
    public static final String PARAM_NAME_PROPS_MAP = "propsMap";
    public static final String PARAM_NAME_PAGE = "page";
    public static final String PARAM_NAME_ORDER_BY = "orderBy";
    public static final String PARAM_NAME_VO = "vo";

    //	public static final String PARAM_NAME_ID = "id";
    public static final String PARAM_NAME_ID_ITER = "idIter";
}
