package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.UamcApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaGroupPmsRltRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaGroupPmsRltRepo jpaGroupPmsRltRepo;

    @Test
    public void addRltTest() {
        for (int i = 0; i < 5; i++) {
            String pmsId = "p-" + i;
            String groupId = "g-" + i;
            jpaGroupPmsRltRepo.addRlt(pmsId, groupId);
        }
    }

    @Test
    public void deleteRltTest() {
        String pmsId = "p-1";
        String groupId = "g-1";
        jpaGroupPmsRltRepo.deleteRlt(pmsId, groupId);
    }

    @Test
    public void deleteRltByPmsIdTest() {
        String pmsId = "p";
        jpaGroupPmsRltRepo.deleteRltByPmsId(pmsId);
    }

    @Test
    public void deleteRltByGroupIdTest() {
        String groupId = "g";
        jpaGroupPmsRltRepo.deleteRltByGroupId(groupId);
    }
}
