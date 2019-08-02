package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa;

import com.yffd.bcap.common.support.persistence.jpa.JpaRepositorySupport;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaRsOperationRepo extends JpaRepositorySupport<RsOperationData> implements RsOperationRepo {
    @Autowired
    private JpaRsOperationRepoPlus jpaRsOperationRepoPlus;

    @Override
    public String getDataId(RsOperationData data) {
        return data.getOprtId();
    }

    @Override
    public void setDataId(RsOperationData data, String id) {
        data.setOprtId(id);
    }

    @Override
    public JpaRepository<RsOperationData, String> repository() {
        return jpaRsOperationRepoPlus;
    }
}
