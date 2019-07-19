package com.yffd.bcap.uamc.infrastructure.persistence.role;

import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpa extends JpaRepository<RoleData, String> {
}
