package com.yffd.bcap.uamc.infrastructure.persistence.relation.jpa;

import com.yffd.bcap.uamc.UamcApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaPmsUserRltRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaPmsUserRltRepo jpaPmsUserRltRepo;

    @Test
    public void addRltTest() {
        for (int i = 0; i < 5; i++) {
            String userId = "u" + i;
            String pmsId = "p";
            jpaPmsUserRltRepo.addRlt(userId, pmsId);
        }
    }

    @Test
    public void deleteRltTest() {
        String userId = "u1";
        String pmsId = "p1";
        jpaPmsUserRltRepo.deleteRlt(userId, pmsId);
    }

    @Test
    public void deleteRltByUserIdTest() {
        String userId = "u";
        jpaPmsUserRltRepo.deleteRltByUserId(userId);
    }

    @Test
    public void deleteRltByPmsIdTest() {
        String pmsId = "p";
        jpaPmsUserRltRepo.deleteRltByPmsId(pmsId);
    }
}
