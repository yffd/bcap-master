package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.UamcApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaRolePmsRltRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaRolePmsRltRepo jpaRolePmsRltRepo;

    @Test
    public void addRltTest() {
        for (int i = 0; i < 5; i++) {
            String pmsId ="p" + i;
            String roleId = "r";
            jpaRolePmsRltRepo.addRlt(pmsId, roleId);
        }
    }

    @Test
    public void deleteRltTest() {
        String pmsId ="p1";
        String roleId = "r1";
        jpaRolePmsRltRepo.deleteRlt(pmsId, roleId);
    }

    @Test
    public void deleteRltByPmsIdTest() {
        String pmsId ="p";
        jpaRolePmsRltRepo.deleteRltByPmsId(pmsId);
    }

    @Test
    public void deleteRltByRoleIdTest() {
        String roleId = "r";
        jpaRolePmsRltRepo.deleteRltByRoleId(roleId);
    }

}
