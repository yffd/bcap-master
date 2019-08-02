package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRoleUserRltRepo implements RoleUserRltRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRlt(String userId, String roleId) {
        String sqlStr = "insert into uamc_role_user_rlt(user_id, role_id) value(:userId, :roleId)";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }

    @Override
    public void deleteRlt(String userId, String roleId) {
        String sqlStr = "delete from uamc_role_user_rlt where user_id = :userId and role_id = :roleId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByUserId(String userId) {
        String sqlStr = "delete from uamc_role_user_rlt where user_id = :userId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByRoleId(String roleId) {
        String sqlStr = "delete from uamc_role_user_rlt where role_id = :roleId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }
}
