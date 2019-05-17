package com.yffd.bcap.uamc.domain.repository;

import com.yffd.bcap.uamc.domain.entities.UamcRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<UamcRole, String> {
}
