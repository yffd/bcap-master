package com.yffd.bcap.uamc.infrastructure.persistence.group;

import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.group.GroupRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class GroupRepoAdapter implements GroupRepo {
    @Override
    public Set<String> findChildrenIds(String parentId) {
        return null;
    }

    @Override
    public void deleteByIdIn(Set<String> delIds) {

    }

    @Override
    public void insertOne(GroupData entity) {

    }

    @Override
    public void updateById(GroupData entity) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public GroupData findById(String id) {
        return null;
    }

    @Override
    public List<GroupData> listData(GroupData entity) {
        return null;
    }
}
