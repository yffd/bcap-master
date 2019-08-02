package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.domain.model.relation.GroupUserRltRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaGroupUserRltRepo implements GroupUserRltRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRlt(String userId, String groupId) {
        String sqlStr = "insert into uamc_group_user_rlt(user_id, group_id) value(:userId, :groupId)";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .setParameter("groupId", groupId)
                .executeUpdate();
    }

    @Override
    public void deleteRlt(String userId, String groupId) {
        String sqlStr = "delete from uamc_group_user_rlt where user_id = :userId and group_id = :groupId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .setParameter("groupId", groupId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByUserId(String userId) {
        String sqlStr = "delete from uamc_group_user_rlt where user_id = :userId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByGroupId(String groupId) {
        String sqlStr = "delete from uamc_group_user_rlt where group_id = :groupId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("groupId", groupId)
                .executeUpdate();
    }
}
