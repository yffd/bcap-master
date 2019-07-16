package com.yffd.bcap.uamc.application.permission.command.handler;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionEntity;
import com.yffd.bcap.uamc.domain.model.permission.PermissionRepo;
import com.yffd.bcap.uamc.domain.model.permission.PermissionService;
import com.yffd.bcap.uamc.domain.model.relation.GroupPmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.PmsUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RolePmsRltRepo;
import org.springframework.transaction.annotation.Transactional;

public class PermissionCmdHandler {

    private PermissionRepo permissionRepo;
    private RolePmsRltRepo rolePmsRltRepo;
    private GroupPmsRltRepo groupPmsRltRepo;
    private PmsUserRltRepo pmsUserRltRepo;

    public void addPms(PermissionData permissionData, SysOperator sysOperator) {
        PermissionEntity permissionEntity = new PermissionEntity(permissionData, sysOperator);
        if (exsistById(permissionEntity))
            throw BcapValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ permissionData.getPmsId() +", class："+ permissionData.getClass() +"]");
        permissionRepo.insertOne(permissionEntity.add());
    }

    public void updatePms(PermissionData permissionData, SysOperator sysOperator) {
        PermissionEntity permissionEntity = new PermissionEntity(permissionData, sysOperator);
        permissionRepo.updateById(permissionEntity.updateById());
    }

    @Transactional
    public void deletePms(PermissionData permissionData, SysOperator sysOperator) {
        PermissionEntity permissionEntity = new PermissionEntity(permissionData, sysOperator);
        PermissionService permissionService = new PermissionService();
        permissionService.deletePmsWithRlt(permissionEntity, permissionRepo, rolePmsRltRepo, groupPmsRltRepo, pmsUserRltRepo);
    }


    private Boolean exsistById(PermissionEntity permissionEntity) {
        return null != permissionRepo.findById(permissionEntity.exsistById());
    }

}
