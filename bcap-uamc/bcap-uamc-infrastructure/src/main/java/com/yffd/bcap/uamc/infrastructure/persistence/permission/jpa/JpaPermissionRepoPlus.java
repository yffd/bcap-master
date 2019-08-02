package com.yffd.bcap.uamc.infrastructure.persistence.permission.jpa;

import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JpaPermissionRepoPlus extends JpaRepository<PermissionData, String>, JpaSpecificationExecutor<PermissionData> {

    @Query(value = "select p.* from uamc_role_pms_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.role_id = ?1 order by p.create_time desc",
            countQuery = "select count(*) from uamc_role_pms_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.role_id = ?1",
            nativeQuery = true)
    Page<PermissionData> findPageByRoleId(String roleId, Pageable pageable);

    @Query(value = "select p.* from uamc_pms_user_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.user_id = ?1 order by p.create_time desc",
            countQuery = "select count(*) from uamc_pms_user_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.user_id = ?1",
            nativeQuery = true)
    Page<PermissionData> findPermissionsByUserId(String userId, Pageable pageable);

    @Query(value = "select p.* from uamc_group_user_rlt gu " +
            "left join uamc_group_pms_rlt gp on gu.group_id = gp.group_id " +
            "left join uamc_permission p on gp.pms_id = p.pms_id " +
            "where p.pms_id is not null and gu.user_id = ?1 " +
            "order by p.create_time desc",
            countQuery = "select count(*) from uamc_group_user_rlt gu " +
                    "left join uamc_group_pms_rlt gp on gu.group_id = gp.group_id " +
                    "left join uamc_permission p on gp.pms_id = p.pms_id " +
                    "where p.pms_id is not null and gu.user_id = ?1 ",
            nativeQuery = true)
    Page<PermissionData> findSecondPermissionsByUserId(String userId, Pageable pageable);

    @Query(value = "select * from " +
            "(select p.* from uamc_pms_user_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.user_id = ?1 " +
            "union " +
            "select p.* from uamc_group_user_rlt gu " +
            "left join uamc_group_pms_rlt gp on gu.group_id = gp.group_id " +
            "left join uamc_permission p on gp.pms_id = p.pms_id " +
            "where p.pms_id is not null and gu.user_id = ?1) tt " +
            "order by tt.create_time desc",
            countQuery = "select count(*) from " +
                    "(select p.* from uamc_pms_user_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.user_id = ?1 " +
                    "union " +
                    "select p.* from uamc_group_user_rlt gu " +
                    "left join uamc_group_pms_rlt gp on gu.group_id = gp.group_id " +
                    "left join uamc_permission p on gp.pms_id = p.pms_id " +
                    "where p.pms_id is not null and gu.user_id = ?1) tt",
            nativeQuery = true)
    Page<PermissionData> findAllPermissionsByUserId(String userId, Pageable pageable);



    @Query(value = "select p.* from uamc_group_pms_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.group_id = ?1 order by p.create_time desc",
            countQuery = "select count(*) from uamc_group_pms_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.group_id = ?1",
            nativeQuery = true)
    Page<PermissionData> findPermissionsByGroupId(String groupId, Pageable pageable);

    @Query(value = "select p.* from uamc_role_group_rlt rlt \n" +
            "left join uamc_role_pms_rlt rp on rlt.role_id = rp.role_id\n" +
            "left join uamc_permission p on rp.pms_id = p.pms_id\n" +
            "where p.pms_id is not null and rlt.group_id = ?1\n" +
            "order by p.create_time desc",
            countQuery = "select count(*) from uamc_role_group_rlt rlt \n" +
                    "left join uamc_role_pms_rlt rp on rlt.role_id = rp.role_id\n" +
                    "left join uamc_permission p on rp.pms_id = p.pms_id\n" +
                    "where p.pms_id is not null and rlt.group_id = ?1\n",
            nativeQuery = true)
    Page<PermissionData> findSecondPermissionsByGroupId(String groupId, Pageable pageable);

    @Query(value = "select tt.* from (\n" +
            "select p.* from uamc_group_pms_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.group_id = ?1\n" +
            "union\n" +
            "select p.* from uamc_role_group_rlt rlt \n" +
            "left join uamc_role_pms_rlt rp on rlt.role_id = rp.role_id\n" +
            "left join uamc_permission p on rp.pms_id = p.pms_id\n" +
            "where p.pms_id is not null and rlt.group_id = ?1\n" +
            ") tt order by tt.create_time desc",
            countQuery = "select count(*) from (\n" +
                    "select p.* from uamc_group_pms_rlt rlt left join uamc_permission p on rlt.pms_id = p.pms_id where rlt.group_id = ?1\n" +
                    "union\n" +
                    "select p.* from uamc_role_group_rlt rlt \n" +
                    "left join uamc_role_pms_rlt rp on rlt.role_id = rp.role_id\n" +
                    "left join uamc_permission p on rp.pms_id = p.pms_id\n" +
                    "where p.pms_id is not null and rlt.group_id = ?1\n" +
                    ") tt",
            nativeQuery = true)
    Page<PermissionData> findAllPermissionsByGroupId(String groupId, Pageable pageable);
}
