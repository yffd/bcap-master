package com.yffd.bcap.common.domain.support.repository.query;

import com.yffd.bcap.common.domain.model.page.PageData;
import com.yffd.bcap.common.domain.model.page.PageInfo;

import java.util.List;

public interface IBaseQry<E, V> {

    PageData<E> findPage(V criteria, PageInfo pageInfo);

    List<E> findList(V criteria);

    Integer findCount(V criteria);

}
