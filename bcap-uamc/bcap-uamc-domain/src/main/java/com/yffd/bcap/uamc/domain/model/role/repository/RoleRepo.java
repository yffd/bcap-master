package com.yffd.bcap.uamc.domain.model.role.repository;

import com.yffd.bcap.common.ddd.domain.repository.IBaseRepo;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;

public interface RoleRepo extends IBaseRepo<RoleEntity> {

    void add(RoleEntity role);
    void update(RoleEntity role);
    void remove(RoleEntity role);
    Boolean exsist(RoleEntity role);
    Boolean exsistAndUnique(RoleEntity role);
    Integer count(String identity);
    RoleEntity findByIdentity(String identity);

}
