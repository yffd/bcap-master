package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.UamcApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaGroupUserRltRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaGroupUserRltRepo jpaGroupUserRltRepo;

    @Test
    public void addRltTest() {
        for (int i = 0; i < 5; i++) {
            String userId = "u" + i;
            String groupId = "g";
            jpaGroupUserRltRepo.addRlt(userId, groupId);
        }
    }

    @Test
    public void deleteRltTest() {
        String userId = "u1";
        String groupId = "g1";
        jpaGroupUserRltRepo.deleteRlt(userId, groupId);
    }

    @Test
    public void deleteRltByUserIdTest() {
        String userId = "u";
        jpaGroupUserRltRepo.deleteRltByUserId(userId);
    }

    @Test
    public void deleteRltByGroupIdTest() {
        String groupId = "g";
        jpaGroupUserRltRepo.deleteRltByGroupId(groupId);
    }

}
