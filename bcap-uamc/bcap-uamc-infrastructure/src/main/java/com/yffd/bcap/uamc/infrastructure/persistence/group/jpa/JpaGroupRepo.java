package com.yffd.bcap.uamc.infrastructure.persistence.group.jpa;

import com.yffd.bcap.common.support.persistence.jpa.JpaRepositorySupport;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.group.GroupRepo;
import com.yffd.bcap.uamc.infrastructure.persistence.group.jpa.JpaGroupRepoPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class JpaGroupRepo extends JpaRepositorySupport<GroupData> implements GroupRepo {

    @Autowired
    private JpaGroupRepoPlus jpaGroupRepoPlus;

    @Override
    public void updateGroupState(GroupData groupData) {
        GroupData po = new GroupData();
        po.setGroupId(groupData.getGroupId());
        po.setGroupState(groupData.getGroupState());
        this.copyPropsForUpdate(groupData, po);
        jpaGroupRepoPlus.updateGroupState(po);
    }

    @Override
    public void deleteByIdIn(Set<String> delIds) {
        jpaGroupRepoPlus.deleteByIdIn(delIds);
    }

    @Override
    public String getDataId(GroupData data) {
        return data.getGroupId();
    }

    @Override
    public void setDataId(GroupData data, String id) {
        data.setGroupId(id);
    }

    @Override
    public JpaRepository<GroupData, String> repository() {
        return this.jpaGroupRepoPlus;
    }


    /*private GroupData copyProps(GroupData from, GroupData to) {
        if (BcapStringUtils.isNotEmpty(from.getGroupName())) to.setGroupName(from.getGroupName());
        if (BcapStringUtils.isNotEmpty(from.getParentId())) to.setParentId(from.getParentId());
        if (BcapStringUtils.isNotEmpty(from.getGroupState())) to.setGroupState(from.getGroupState());
        if (BcapStringUtils.isNotEmpty(from.getRemark())) to.setRemark(from.getRemark());
        return to;
    }*/
}
