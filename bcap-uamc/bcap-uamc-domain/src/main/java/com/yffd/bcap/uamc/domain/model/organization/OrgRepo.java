package com.yffd.bcap.uamc.domain.model.organization;

import com.yffd.bcap.common.ddd.domain.repository.RepositorySupport;

import java.util.List;
import java.util.Set;

public interface OrgRepo extends RepositorySupport<OrgData> {

    void deleteByIds(Set<String> orgIds);

    Boolean exsistUser(Set<String> orgIds);

    List<OrgData> findChildren(String orgId);
}
