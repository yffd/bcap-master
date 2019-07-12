package com.yffd.bcap.common.ddd.domain.repository;

import java.util.List;

/**
 * 领域厂库接口
 * @param <E>   数据实体
 */
public interface RepositorySupport<E> extends IRepository {

    void insertOne(E entity);

    void updateById(E entity);

    void deleteById(String id);

    E findById(String id);

    List<E>  listData(E entity);

}
