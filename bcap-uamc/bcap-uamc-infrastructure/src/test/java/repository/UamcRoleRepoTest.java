package repository;

import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;
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
    }

}
