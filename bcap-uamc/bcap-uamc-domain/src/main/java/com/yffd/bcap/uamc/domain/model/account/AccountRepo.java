package com.yffd.bcap.uamc.domain.model.account;

import com.yffd.bcap.common.ddd.domain.repository.RepositorySupport;

public interface AccountRepo extends RepositorySupport<AccountData> {

    void deleteByUserId(String userId);
}
