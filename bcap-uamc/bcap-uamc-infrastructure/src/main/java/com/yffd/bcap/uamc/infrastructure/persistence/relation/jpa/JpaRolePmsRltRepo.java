package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.domain.model.relation.RolePmsRltRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRolePmsRltRepo implements RolePmsRltRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRlt(String pmsId, String roleId) {
        String sqlStr = "insert into uamc_role_pms_rlt(pms_id, role_id) value(:pmsId, :roleId)";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("pmsId", pmsId)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }

    @Override
    public void deleteRlt(String pmsId, String roleId) {
        String sqlStr = "delete from uamc_role_pms_rlt where pms_id = :pmsId and role_id = :roleId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("pmsId", pmsId)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByPmsId(String pmsId) {
        String sqlStr = "delete from uamc_role_pms_rlt where pms_id = :pmsId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("pmsId", pmsId)
                .executeUpdate();
    }

    @Override
    public void deleteRltByRoleId(String roleId) {
        String sqlStr = "delete from uamc_role_pms_rlt where role_id = :roleId";
        entityManager.createNativeQuery(sqlStr)
                .setParameter("roleId", roleId)
                .executeUpdate();
    }
}
