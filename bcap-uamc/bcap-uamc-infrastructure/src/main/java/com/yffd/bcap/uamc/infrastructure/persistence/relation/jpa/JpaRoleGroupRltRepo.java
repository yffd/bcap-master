package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRoleGroupRltRepo implements RoleGroupRltRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRlt(String groupId, String roleId) {
        String sqlStr = "insert into uamc_role_group_rlt(group_id, role_id) value(:groupId, :roleId)";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("groupId", groupId)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }

    @Override
    public void deleteRlt(String groupId, String roleId) {
        String sqlStr = "delete from uamc_role_group_rlt where group_id = :groupId and role_id = :roleId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("groupId", groupId)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByGroupId(String groupId) {
        String sqlStr = "delete from uamc_role_group_rlt where group_id = :groupId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("groupId", groupId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByRoleId(String roleId) {
        String sqlStr = "delete from uamc_role_group_rlt where role_id = :roleId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }
}
