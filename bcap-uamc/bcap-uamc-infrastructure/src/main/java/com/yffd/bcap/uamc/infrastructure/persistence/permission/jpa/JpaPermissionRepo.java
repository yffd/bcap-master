package com.yffd.bcap.uamc.infrastructure.persistence.permission.jpa;

import com.yffd.bcap.common.support.persistence.jpa.JpaRepositorySupport;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaPermissionRepo extends JpaRepositorySupport<PermissionData> implements PermissionRepo {

    @Autowired
    private JpaPermissionRepoPlus permissionJpa;

    @Override
    public String getDataId(PermissionData data) {
        return data.getPmsId();
    }

    @Override
    public void setDataId(PermissionData data, String id) {
        data.setPmsId(id);
    }

    @Override
    public JpaRepository<PermissionData, String> repository() {
        return permissionJpa;
    }
}
