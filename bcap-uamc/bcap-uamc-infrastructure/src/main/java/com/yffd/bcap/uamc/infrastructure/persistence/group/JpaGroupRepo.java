package com.yffd.bcap.uamc.infrastructure.persistence.group;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.group.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JpaGroupRepo implements GroupRepo {

    @Autowired
    private GroupJpa groupJpa;

    @Override
    public Set<String> findChildrenIds(String parentId) {
        return null;
    }

    @Override
    public void deleteByIdIn(Set<String> delIds) {

    }

    @Override
    public void insertOne(GroupData data) {
        if (BcapStringUtils.isEmpty(data.getGroupId())) data.setGroupId(this.nextIdentity());
        this.groupJpa.save(data);
    }

    @Override
    public void updateById(GroupData data) {
        Optional<GroupData> optional = this.groupJpa.findById(data.getGroupId());
        GroupData loadData = optional.get();
        this.groupJpa.save(copy(data, loadData));
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public GroupData findById(String id) {
        return null;
    }

    @Override
    public List<GroupData> listData(GroupData data) {
        return null;
    }

    private GroupData copy(GroupData from, GroupData to) {
        if (BcapStringUtils.isNotEmpty(from.getGroupName())) to.setGroupName(from.getGroupName());
        if (BcapStringUtils.isNotEmpty(from.getParentId())) to.setParentId(from.getParentId());
        if (BcapStringUtils.isNotEmpty(from.getGroupState())) to.setGroupState(from.getGroupState());
        if (BcapStringUtils.isNotEmpty(from.getRemark())) to.setRemark(from.getRemark());
        return to;
    }
}
