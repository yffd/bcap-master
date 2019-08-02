package com.yffd.bcap.uamc.application.permission.service;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.exception.UamcDomainValidateException;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionEntity;
import com.yffd.bcap.uamc.domain.model.permission.PermissionRepo;
import com.yffd.bcap.uamc.domain.model.permission.PermissionService;
import com.yffd.bcap.uamc.domain.model.relation.GroupPmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.PmsUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RolePmsRltRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionAppService {
    private PermissionService permissionService = PermissionService.getInstance();
    @Autowired
    private PermissionRepo permissionRepo;
    @Autowired
    private RolePmsRltRepo rolePmsRltRepo;
    @Autowired
    private GroupPmsRltRepo groupPmsRltRepo;
    @Autowired
    private PmsUserRltRepo pmsUserRltRepo;

    public void addPms(PermissionData permissionData, SysOperator sysOperator) {
        PermissionEntity permissionEntity = new PermissionEntity(permissionData, sysOperator);
        if (permissionService.exsistPmsById(permissionEntity, permissionRepo))
            throw UamcDomainValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ permissionData.getPmsId() +", class："+ permissionData.getClass() +"]");
        permissionRepo.insertOne(permissionEntity.add());
    }

    public void updatePms(PermissionData permissionData, SysOperator sysOperator) {
        PermissionEntity permissionEntity = new PermissionEntity(permissionData, sysOperator);
        permissionRepo.updateById(permissionEntity.updateById());
    }

    @Transactional
    public void deletePms(PermissionData permissionData, SysOperator sysOperator) {
        PermissionEntity permissionEntity = new PermissionEntity(permissionData, sysOperator);
        permissionService.deletePmsWithRlt(permissionEntity, permissionRepo, rolePmsRltRepo, groupPmsRltRepo, pmsUserRltRepo);
    }

}
