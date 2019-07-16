package com.yffd.bcap.uamc.domain.model.group;

import com.yffd.bcap.common.ddd.domain.repository.RepositorySupport;

import java.util.Set;

public interface GroupRepo extends RepositorySupport<GroupData> {

    Set<String> findChildrenIds(String parentId);

    void deleteByIdIn(Set<String> delIds);

}
