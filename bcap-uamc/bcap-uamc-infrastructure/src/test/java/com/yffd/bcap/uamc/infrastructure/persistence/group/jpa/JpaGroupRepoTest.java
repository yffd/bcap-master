package com.yffd.bcap.uamc.infrastructure.persistence.group.jpa;

import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class JpaGroupRepoTest extends UamcApplicationTest {

    @Autowired
    private JpaGroupRepo jpaGroupRepo;

    @Test
    public void insertOneTest() {
        GroupData groupData = new GroupData();
        groupData.setGroupId(jpaGroupRepo.nextIdentity());
        groupData.setGroupName("组名_" + jpaGroupRepo.nextIdentity());
        groupData.setGroupState(ActiveStateEnum.ACTIVE.getCode());
        groupData.setRemark("测试");

        DataObjectHelper.initPropsForAdd(groupData, sysOperator());
        jpaGroupRepo.insertOne(groupData);
        System.out.println(groupData.getCreateTime());
    }

    @Test
    public void insertXxxTest() {
        for (int i = 100; i<125; i++) {
            GroupData groupData = new GroupData();
            groupData.setGroupId(jpaGroupRepo.nextIdentity());
            groupData.setGroupName("组名_" + i);
            groupData.setGroupState(ActiveStateEnum.ACTIVE.getCode());
            groupData.setRemark("测试");
            DataObjectHelper.initPropsForAdd(groupData, sysOperator());
            jpaGroupRepo.insertOne(groupData);
        }
    }

    @Test
    public void updateByIdTest() {
        GroupData groupData = new GroupData();
        groupData.setGroupId("4CD953931FFD4B61BB8541D4B518E631");
        groupData.setGroupName("测试_修改1-1");
        DataObjectHelper.initPropsForUpdate(groupData, sysOperator());
        jpaGroupRepo.updateById(groupData);
        System.out.println(groupData.getUpdateTime());
    }

    @Test
    public void deleteByIdTest() {
        String id = "F2679867E2B148EF8E0FEB074AE19905";
        this.jpaGroupRepo.deleteById(id);
    }

    @Test
    public void findByIdTest() {
        String id ="4CD953931FFD4B61BB8541D4B518E631";
        GroupData data = this.jpaGroupRepo.findById(id);
        System.out.println(data.getGroupName());
    }

    @Test
    public void updateGroupStateTest() {
        GroupData data = new GroupData();
        data.setGroupId("4CD953931FFD4B61BB8541D4B518E631");
        data.setGroupState(ActiveStateEnum.ACTIVE.getCode());
        DataObjectHelper.initPropsForUpdate(data, sysOperator());
        jpaGroupRepo.updateGroupState(data);
        System.out.println(data.getGroupState());
    }

    @Test
    public void deleteByIdInTest() {
        Set<String> ids = new HashSet<>();
        ids.add("FBF9EA363EAB49C092F352F7B52F2712");
        ids.add("B99B8FCD648245CC9D25FD5A0EBD70F3");
        ids.add("6B68935378D64A2E826473154C655E2D");
        ids.add("DBCE212EF1B84348A090D5959FD55FF3");
        ids.add("9C605F19B2B140A48DCF8B53CD2DD0AC");
        jpaGroupRepo.deleteByIdIn(ids);
    }

}



