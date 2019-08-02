package com.yffd.bcap.uamc.infrastructure.persistence.role.jpa;

import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface JpaRoleRepoPlus extends JpaRepository<RoleData, String>, JpaSpecificationExecutor<RoleData> {

    @Transactional
    @Modifying
    @Query("update RoleData t " +
            "set t.roleState = :#{#roleData.roleState} " +
            ", t.version = t.version + 1, t.updateBy = :#{#roleData.updateBy}, t.updateTime = :#{#roleData.updateTime}" +
            " where t.roleId = :#{#roleData.roleId}")
    void updateRoleState(@Param("roleData") RoleData roleData);


    @Query(value = "select r.* from uamc_role_user_rlt rlt left join uamc_role r on rlt.role_id = r.role_id where rlt.user_id = ?1 order by r.create_time desc",
    countQuery = "select count(*) from uamc_role_user_rlt rlt left join uamc_role r on rlt.role_id = r.role_id where rlt.user_id = ?1",
    nativeQuery = true)
    Page<RoleData> findRolesByUserId(String userId, Pageable pageable);

    @Query(value = "select r.* from uamc_group_user_rlt gu " +
            "left join uamc_role_group_rlt rg on gu.group_id = rg.group_id " +
            "left join uamc_role r on rg.role_id = r.role_id " +
            "where r.role_id is not null and gu.user_id = ?1 " +
            "order by r.create_time desc",
            countQuery = "select count(*) from uamc_group_user_rlt gu " +
                    "left join uamc_role_group_rlt rg on gu.group_id = rg.group_id " +
                    "left join uamc_role r on rg.role_id = r.role_id " +
                    "where r.role_id is not null and gu.user_id = ?",
            nativeQuery = true)
    Page<RoleData> findSecondRolesByUserId(String userId, Pageable pageable);

    @Query(value = "select * from " +
            "(select r.* from uamc_role_user_rlt rlt left join uamc_role r on rlt.role_id = r.role_id where rlt.user_id = ?1 " +
            "union " +
            "select r.* from uamc_group_user_rlt gu " +
            "left join uamc_role_group_rlt rg on gu.group_id = rg.group_id " +
            "left join uamc_role r on rg.role_id = r.role_id " +
            "where r.role_id is not null and gu.user_id = ?1) tt " +
            "order by tt.create_time desc",
            countQuery = "select count(*) from " +
                    "(select r.* from uamc_role_user_rlt rlt left join uamc_role r on rlt.role_id = r.role_id where rlt.user_id = ?1 " +
                    "union " +
                    "select r.* from uamc_group_user_rlt gu " +
                    "left join uamc_role_group_rlt rg on gu.group_id = rg.group_id " +
                    "left join uamc_role r on rg.role_id = r.role_id " +
                    "where r.role_id is not null and gu.user_id = ?1) tt",
            nativeQuery = true)
    Page<RoleData> findAllRolesByUserId(String userId, Pageable pageable);


    @Query(value = "select r.* from uamc_role_group_rlt rlt left join uamc_role r on rlt.role_id = r.role_id where rlt.group_id = ?1 order by r.create_time desc",
    countQuery = "select count(*) from uamc_role_group_rlt rlt left join uamc_role r on rlt.role_id = r.role_id where rlt.group_id = ?1",
    nativeQuery = true)
    Page<RoleData> findRolesByGroupId(String groupId, Pageable pageable);
}
