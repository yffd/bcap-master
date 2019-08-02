package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.domain.model.relation.PmsUserRltRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaPmsUserRltRepo implements PmsUserRltRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRlt(String userId, String pmsId) {
        String sqlStr = "insert into uamc_pms_user_rlt(user_id, pms_id) value(:userId, :pmsId)";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .setParameter("pmsId", pmsId)
                .executeUpdate();
    }

    @Override
    public void deleteRlt(String userId, String pmsId) {
        String sqlStr = "delete from uamc_pms_user_rlt where user_id = :userId and pms_id = :pmsId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .setParameter("pmsId", pmsId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByUserId(String userId) {
        String sqlStr = "delete from uamc_pms_user_rlt where user_id = :userId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByPmsId(String pmsId) {
        String sqlStr = "delete from uamc_pms_user_rlt where pms_id = :pmsId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("pmsId", pmsId)
                .executeUpdate();
    }
}
