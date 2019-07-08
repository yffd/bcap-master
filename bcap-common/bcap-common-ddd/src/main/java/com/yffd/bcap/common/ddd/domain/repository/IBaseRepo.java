package com.yffd.bcap.common.ddd.domain.repository;

import java.util.UUID;

public interface IBaseRepo<E> {

    void insert(E entity);

    void update(E entity);

    void delete(E entity);

    E findByIdentity(String identity);

    default String nextIdentity() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }
}
