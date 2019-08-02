package com.yffd.bcap.uamc.infrastructure.persistence.user.jpa;

import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.common.model.generator.IdentityGenerator;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaUserRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaUserRepo jpaUserRepo;
    @Autowired
    private JpaUserRepoPlus userJpa;

    @Test
    public void insertXxxTest() {
        for (int i = 100; i<125; i++) {
            UserData data = new UserData();
            data.setUserName("用户_" + i);
            data.setEmail("test@qq.com");
            data.setIcon("/a/b/c/xxx.img");
            data.setOrgId(IdentityGenerator.getId());
            DataObjectHelper.initPropsForAdd(data, sysOperator());
            this.jpaUserRepo.insertOne(data);
        }
    }
}
