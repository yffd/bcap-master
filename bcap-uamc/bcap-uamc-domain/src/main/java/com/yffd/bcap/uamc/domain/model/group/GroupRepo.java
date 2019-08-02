package com.yffd.bcap.uamc.domain.model.group;

import com.yffd.bcap.common.ddd.domain.repository.RepositorySupport;

import java.util.Set;

public interface GroupRepo extends RepositorySupport<GroupData> {

    void deleteByIdIn(Set<String> delIds);

    void updateGroupState(GroupData groupData);
}
