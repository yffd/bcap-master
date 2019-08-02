package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa.query;

import com.yffd.bcap.uamc.application.resource.query.RsMenuQry;
import com.yffd.bcap.uamc.domain.model.resource.RsMenuData;
import com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa.JpaRsMenueRepoPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaRsMenuQry implements RsMenuQry {
    @Autowired
    private JpaRsMenueRepoPlus jpaRsMenueRepoPlus;

    @Override
    public List<RsMenuData> findAll() {
        return jpaRsMenueRepoPlus.findAll();
    }
}
