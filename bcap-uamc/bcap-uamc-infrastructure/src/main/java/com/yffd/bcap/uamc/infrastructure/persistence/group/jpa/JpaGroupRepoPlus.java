package com.yffd.bcap.uamc.infrastructure.persistence.group.jpa;

import com.yffd.bcap.uamc.domain.model.group.GroupData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface JpaGroupRepoPlus extends JpaRepository<GroupData, String>, JpaSpecificationExecutor<GroupData> {

    @Transactional
    @Modifying
    @Query("update GroupData t " +
            "set t.groupState = :#{#data.groupState} " +
            ", t.version = t.version + 1, t.updateBy = :#{#data.updateBy}, t.updateTime = :#{#data.updateTime}" +
            " where t.groupId = :#{#data.groupId}")
    void updateGroupState(@Param("data") GroupData data);

    @Transactional
    @Modifying
    @Query("delete from GroupData t where t.groupId in (?1)")
    void deleteByIdIn(Set<String> ids);


    @Query(value = "select g.* from uamc_role_group_rlt rlt left join uamc_group g on rlt.group_id = g.group_id where rlt.role_id = ?1 order by g.create_time desc",
            countQuery = "select count(*) from uamc_role_group_rlt rlt left join uamc_group g on rlt.group_id = g.group_id where rlt.role_id = ?1",
            nativeQuery = true)
    Page<GroupData> findPageByRoleId(String roleId, Pageable pageable);
}
