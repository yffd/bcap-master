package com.yffd.bcap.common.ddd.domain.repository;

import java.util.List;

/**
 * 领域厂库接口
 * @param <D>   数据实体
 */
public interface RepositorySupport<D> extends IRepository {

    void insertOne(D data);

    void updateById(D data);

    void deleteById(String id);

    D findById(String id);

    List<D>  listData(D data);

}
