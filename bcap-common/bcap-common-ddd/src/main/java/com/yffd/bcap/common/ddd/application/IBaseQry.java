package com.yffd.bcap.common.ddd.application;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;

import java.util.List;

public interface IBaseQry<E, V> {

    /**
     * 分页查询
     * @param criteria  查询条件
     * @param pageInfo  分页信息
     * @return
     */
    PageData<E> findPage(V criteria, PageInfo pageInfo);

    /**
     * 列表查询
     * @param criteria  查询条件
     * @return
     */
    List<E> findList(V criteria);
}
