package com.yffd.bcap.uamc.infrastructure.persistence.user.jpa;

import com.yffd.bcap.common.support.persistence.jpa.JpaRepositorySupport;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import com.yffd.bcap.uamc.domain.model.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaUserRepo extends JpaRepositorySupport<UserData> implements UserRepo {
    @Autowired
    private JpaUserRepoPlus userJpa;

    @Override
    public String getDataId(UserData data) {
        return data.getUserId();
    }

    @Override
    public void setDataId(UserData data, String id) {
        data.setUserId(id);
    }

    @Override
    public JpaRepository<UserData, String> repository() {
        return userJpa;
    }
}
