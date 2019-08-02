package com.yffd.bcap.uamc.infrastructure.persistence.organization.jpa;

import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.domain.model.organization.OrgRepo;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface JpaOrgRepoPlus extends JpaRepository<OrgData, String>, JpaSpecificationExecutor<OrgData> {

    @Transactional
    @Modifying
    @Query("delete from OrgData t where t.orgId in (?1)")
    void deleteByIds(Set<String> ids);

    @Query("select t from OrgData t where t.orgPath like %?1%")
    List<OrgData> findChildren(String orgId);
}
