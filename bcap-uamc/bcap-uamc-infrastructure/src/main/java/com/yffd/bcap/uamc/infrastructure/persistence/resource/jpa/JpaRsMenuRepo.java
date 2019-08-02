package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa;

import com.yffd.bcap.common.support.persistence.jpa.JpaRepositorySupport;
import com.yffd.bcap.uamc.domain.model.resource.RsMenuData;
import com.yffd.bcap.uamc.domain.model.resource.RsMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaRsMenuRepo extends JpaRepositorySupport<RsMenuData> implements RsMenuRepo {
    @Autowired
    private JpaRsMenueRepoPlus jpaRsMenueRepoPlus;

    @Override
    public String getDataId(RsMenuData data) {
        return data.getMenuId();
    }

    @Override
    public void setDataId(RsMenuData data, String id) {
        data.setMenuId(id);
    }

    @Override
    public JpaRepository<RsMenuData, String> repository() {
        return jpaRsMenueRepoPlus;
    }
}
