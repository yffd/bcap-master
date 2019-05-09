package com.yffd.bcap.common.support.repository.mybatis.mapper;

import com.yffd.bcap.common.domain.model.page.PageInfo;
import com.yffd.bcap.common.support.repository.mybatis.MybatisConstants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IBaseMapper<E> {

    public int insertOneBy(E entity);

    public int insertListBy(List<E> entityList);

    public int updateBy(@Param(MybatisConstants.PARAM_NAME_ENTITY) E entity,
                        @Param(MybatisConstants.PARAM_NAME_ENTITY_OLD) E entityOld,
                        @Param(MybatisConstants.PARAM_NAME_PROPS_MAP) Map<String, Object> propsMap);

    public int deleteBy(@Param(MybatisConstants.PARAM_NAME_ENTITY) E entity,
                        @Param(MybatisConstants.PARAM_NAME_PROPS_MAP) Map<String, Object> propsMap);

    public E selectOneBy(@Param(MybatisConstants.PARAM_NAME_ENTITY) E entity,
                         @Param(MybatisConstants.PARAM_NAME_PROPS_MAP) Map<String, Object> propsMap);

    public Integer selectCountBy(@Param(MybatisConstants.PARAM_NAME_ENTITY) E entity,
                                 @Param(MybatisConstants.PARAM_NAME_PROPS_MAP) Map<String, Object> propsMap);

    public List<E> selectListBy(@Param(MybatisConstants.PARAM_NAME_ENTITY) E entity,
                                @Param(MybatisConstants.PARAM_NAME_PROPS_MAP) Map<String, Object> propsMap,
                                @Param(MybatisConstants.PARAM_NAME_ORDER_BY) String orderBy,
                                @Param(MybatisConstants.PARAM_NAME_PAGE) PageInfo page);

}
