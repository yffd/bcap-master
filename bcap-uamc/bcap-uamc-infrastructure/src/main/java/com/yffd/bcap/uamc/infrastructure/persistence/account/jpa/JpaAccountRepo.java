package com.yffd.bcap.uamc.infrastructure.persistence.account.jpa;

import com.yffd.bcap.common.support.persistence.jpa.JpaRepositorySupport;
import com.yffd.bcap.uamc.domain.model.account.AccountData;
import com.yffd.bcap.uamc.domain.model.account.AccountRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaAccountRepo extends JpaRepositorySupport<AccountData> implements AccountRepo {

    @Override
    public String getDataId(AccountData data) {
        return null;
    }

    @Override
    public void setDataId(AccountData data, String id) {

    }

    @Override
    public JpaRepository<AccountData, String> repository() {
        return null;
    }

    @Override
    public void deleteByUserId(String userId) {

    }
}
