package com.yffd.bcap.uamc.domain.repository;

import com.yffd.bcap.uamc.domain.entities.UamcRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UamcRoleRepoTest {

    @Autowired
    private RoleRepo uamcRoleRepo;


    @Test
    public void saveTest() {
        UamcRole role = new UamcRole();
        role.setRoleName("t2");
        role.setRoleState("enabled");
        role.setRoleId("a1fba29a38a84736aaee388aab095cea");
//        role.setRoleId(IdentityGeneratorUtil.getId());
        UamcRole result = uamcRoleRepo.save(role);
        System.out.println(result.getRoleId());
    }

}
