package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.UamcApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaRoleUserRltRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaRoleUserRltRepo jpaRoleUserRltRepo;

    @Test
    public void addRltTest() {
        for (int i = 0; i < 5; i++) {
            String userId = "u" + i;
            String roleId = "r";
            jpaRoleUserRltRepo.addRlt(userId, roleId);
        }
    }

    @Test
    public void deleteRltTest() {
        String userId = "u1";
        String roleId = "r1";
        jpaRoleUserRltRepo.deleteRlt(userId, roleId);
    }

    @Test
    public void deleteRltByUserIdTest() {
        String userId = "u";
        jpaRoleUserRltRepo.deleteRltByUserId(userId);
    }

    @Test
    public void deleteRltByRoleIdTest() {
        String roleId = "r";
        jpaRoleUserRltRepo.deleteRltByRoleId(roleId);
    }
}
