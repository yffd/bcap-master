package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.domain.model.relation.GroupPmsRltRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaGroupPmsRltRepo implements GroupPmsRltRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRlt(String pmsId, String groupId) {
        String sqlStr = "insert into uamc_group_pms_rlt(group_id, pms_id) value(:groupId, :pmsId)";
        entityManager.createNativeQuery(sqlStr)
        .setParameter("groupId", groupId)
        .setParameter("pmsId", pmsId)
        .executeUpdate();
    }

    @Override
    public void deleteRlt(String pmsId, String groupId) {
        String sqlStr = "delete from uamc_group_pms_rlt where group_id = :groupId and pms_id = :pmsId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("groupId", groupId)
                .setParameter("pmsId", pmsId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByPmsId(String pmsId) {
        String sqlStr = "delete from uamc_group_pms_rlt where pms_id = :pmsId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("pmsId", pmsId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByGroupId(String groupId) {
        String sqlStr = "delete from uamc_group_pms_rlt where group_id = :groupId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("groupId", groupId)
                .executeUpdate();
    }
}
