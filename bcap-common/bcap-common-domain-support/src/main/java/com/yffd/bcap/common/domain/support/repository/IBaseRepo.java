package com.yffd.bcap.common.domain.support.repository;

public interface IBaseRepo<E> {

    void insert(E entity);

    void update(E entity);

    void delete(E entity);

    E findById(String id);

}
