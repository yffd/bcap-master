package com.yffd.bcap.uamc.infrastructure.persistence.group;

import com.yffd.bcap.uamc.domain.model.group.GroupData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupJpa extends JpaRepository<GroupData, String> {

}
