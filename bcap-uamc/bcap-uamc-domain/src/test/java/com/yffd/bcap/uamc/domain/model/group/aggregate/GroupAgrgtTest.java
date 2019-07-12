package com.yffd.bcap.uamc.domain.model.group.aggregate;

import com.yffd.bcap.uamc.domain.model.DomainEntityTest;
import com.yffd.bcap.uamc.domain.model.group.GroupEntity;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.group.GroupRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GroupAgrgtTest extends DomainEntityTest {
    @Spy
    private GroupRepo groupRepo;

    @Test
    public void addTest() {
        GroupData data = spy(GroupData.class);
        GroupEntity groupEntity = new GroupEntity(data, sysOperator);

        GroupRepo mockRepo = mock(GroupRepo.class,new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                GroupData entity = (GroupData) args[0];
                System.out.println("持久化被调用，id:" + entity.getGroupId());
                return null;
            }
        });

        mockRepo.insertOne(groupEntity.add());

//        GroupRepo mockRepo = mock(GroupRepo.class);
//        when(mockRepo.insertOne(entity)).thenAnswer(new Answer<Object>() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                Object[] args = invocation.getArguments();
//                GroupEntity entity = (GroupEntity) args[0];
//                System.out.println("持久化，id:" + entity.getGroupId());
//                return null;
//            }
//        });


    }

    @Test
    public void updateByIdTest() {
        GroupData data = new GroupData();
        data.setGroupId(groupRepo.nextIdentity());
        GroupEntity groupEntity = new GroupEntity(data, sysOperator);

        groupRepo.updateById(groupEntity.updateById());
    }

    @Test
    public void deleteByIdTest() {
        GroupData data = new GroupData();
        data.setGroupId(groupRepo.nextIdentity());
        GroupEntity groupEntity = new GroupEntity(data, sysOperator);

        groupRepo.deleteById(groupEntity.deleteById());
    }

    @Test
    public void exsistByIdTest() {
        GroupData data = new GroupData();
        data.setGroupId(groupRepo.nextIdentity());
        GroupEntity groupEntity = new GroupEntity(data, sysOperator);

        groupRepo.findById(groupEntity.exsistById());
    }

}
