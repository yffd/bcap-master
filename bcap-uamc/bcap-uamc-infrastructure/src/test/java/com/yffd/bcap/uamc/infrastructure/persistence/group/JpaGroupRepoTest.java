package com.yffd.bcap.uamc.infrastructure.persistence.group;

import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaGroupRepoTest extends UamcApplicationTest {

    @Autowired
    private JpaGroupRepo jpaGroupRepo;

    @Test
    public void insertOneTest() {
        GroupData groupData = new GroupData();
        groupData.setGroupId(jpaGroupRepo.nextIdentity());
        groupData.setGroupName("测试_" + jpaGroupRepo.nextIdentity());
        groupData.setParentId(null);
        groupData.setGroupState(ActiveStateEnum.ACTIVE.getCode());
        groupData.setRemark("测试");
        jpaGroupRepo.insertOne(groupData);
        System.out.println(groupData.getCreateTime());
    }

    @Test
    public void updateByIdTest() {
        GroupData groupData = new GroupData();
        groupData.setGroupId("1111");
        groupData.setGroupName("测试_修改1");
        groupData.setRemark("测试_修改");
        jpaGroupRepo.updateById(groupData);
        System.out.println(groupData.getCreateTime());
    }

//    @Test
//    public void findAllTest() {
//        List<GroupData> list = jpaGroupRepo.findAll();
//        System.out.println(list);
//    }
//
//    @Test
//    public void findByGroupNameTest() {
//        GroupData data = jpaGroupRepo.findByGroupName("test_1");
//        System.out.println(data);
//    }
}
