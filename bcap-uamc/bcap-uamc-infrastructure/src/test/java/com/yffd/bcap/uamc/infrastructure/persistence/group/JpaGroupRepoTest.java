package com.yffd.bcap.uamc.infrastructure.persistence.group;

import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JpaGroupRepoTest extends UamcApplicationTest {

    @Autowired
    private GroupJpa jpaGroupRepo;

    @Test
    public void saveTest() {
        GroupData groupData = new GroupData();
        groupData.setGroupId("111");
        groupData.setGroupName("test_1");
        GroupData ret = jpaGroupRepo.save(groupData);
        System.out.println(ret.getGroupId());
    }

    @Test
    public void findAllTest() {
        List<GroupData> list = jpaGroupRepo.findAll();
        System.out.println(list);
    }

    @Test
    public void findByGroupNameTest() {
        GroupData data = jpaGroupRepo.findByGroupName("test_1");
        System.out.println(data);
    }
}
