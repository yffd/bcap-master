package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.UamcApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaRoleGroupRltRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaRoleGroupRltRepo jpaRoleGroupRltRepo;

    @Test
    public void addRltTest() {
        for (int i = 0; i < 5; i++) {
            String groupId = "g" + i;
            String roleId = "r";
            jpaRoleGroupRltRepo.addRlt(groupId, roleId);
        }
    }

    @Test
    public void deleteRltTest() {
        String groupId = "g1";
        String roleId = "r1";
        jpaRoleGroupRltRepo.deleteRlt(groupId, roleId);
    }

    @Test
    public void deleteRltByGroupIdTest() {
        String groupId = "g";
        jpaRoleGroupRltRepo.deleteRltByGroupId(groupId);
    }

    @Test
    public void deleteRltByRoleIdTest() {
        String roleId = "r";
        jpaRoleGroupRltRepo.deleteRltByRoleId(roleId);
    }
}
