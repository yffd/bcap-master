package com.yffd.bcap.uamc.infrastructure.persistence.user.jpa;

import com.yffd.bcap.uamc.domain.model.user.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface JpaUserRepoPlus extends JpaRepository<UserData, String>, JpaSpecificationExecutor<UserData> {

    @Query("select count(t) from UserData t where t.orgId in (?1)")
    Integer countUsers(Set<String> orgIds);


    @Query(value = "select u.* from uamc_role_user_rlt rlt left join uamc_user u on rlt.user_id = u.user_id where rlt.role_id = ?1 order by u.create_time desc",
            countQuery = "select count(*) from uamc_role_user_rlt rlt left join uamc_user u on rlt.user_id = u.user_id where rlt.role_id = ?1",
            nativeQuery = true)
    Page<UserData> findPageByRoleId(String roleId, Pageable pageable);

    @Query(value = "select u.* from uamc_group_user_rlt rlt left join uamc_user u on rlt.user_id = u.user_id where rlt.group_id = ?1 order by u.create_time desc",
            countQuery = "select count(*) from uamc_group_user_rlt rlt left join uamc_user u on rlt.user_id = u.user_id where rlt.group_id = ?1",
            nativeQuery = true)
    Page<UserData> findUsersByGroupId(String groupId, Pageable pageable);

}
