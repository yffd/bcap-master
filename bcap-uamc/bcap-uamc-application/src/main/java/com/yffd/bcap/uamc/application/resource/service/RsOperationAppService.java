package com.yffd.bcap.uamc.application.resource.service;

import com.yffd.bcap.common.ddd.application.service.ApplicationServiceSupport;
import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.ddd.domain.repository.RepositorySupport;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.model.permission.PermissionRepo;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationEntity;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationRepo;
import com.yffd.bcap.uamc.domain.model.resource.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RsOperationAppService extends ApplicationServiceSupport<RsOperationData> {
    @Autowired
    private RsOperationRepo resourceOperationRepo;
    @Autowired
    private PermissionRepo permissionRepo;

    @Override
    public EntityObject buildEntity(RsOperationData data, SysOperator sysOperator) {
        return new RsOperationEntity(data, sysOperator);
    }

    @Override
    public RepositorySupport repository() {
        return this.resourceOperationRepo;
    }

    @Override
    @Transactional
    public void delete(RsOperationData data, SysOperator sysOperator) {
        RsOperationEntity entity = new RsOperationEntity(data, sysOperator);
        //1.资源删除时，要同时删除对应的权限；
        RsService.getInstance().deleteRsWithRlt(entity, resourceOperationRepo, permissionRepo);
    }
}
