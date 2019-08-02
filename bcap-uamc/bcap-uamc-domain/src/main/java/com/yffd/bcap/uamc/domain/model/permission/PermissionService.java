package com.yffd.bcap.uamc.domain.model.permission;

import com.yffd.bcap.uamc.domain.model.relation.GroupPmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.PmsUserRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RolePmsRltRepo;

public class PermissionService {
    private static final PermissionService instance = new PermissionService();

    private PermissionService() {
    }

    public static PermissionService getInstance() {
        return instance;
    }

    public Boolean exsistPmsById(PermissionEntity permissionEntity, PermissionRepo permissionRepo) {
        return null != permissionRepo.findById(permissionEntity.exsistById());
    }

    public void deletePmsWithRlt(PermissionEntity permissionEntity, PermissionRepo permissionRepo, RolePmsRltRepo rolePmsRltRepo,
                       GroupPmsRltRepo groupPmsRltRepo, PmsUserRltRepo pmsUserRltRepo) {
        String deletePmsId = permissionEntity.deleteById();
        permissionRepo.deleteById(deletePmsId);
        //1.删除权限与角色的关联关系；
        rolePmsRltRepo.deleteRltByPmsId(deletePmsId);
        //2.删除权限与组的关联关系；
        groupPmsRltRepo.deleteRltByPmsId(deletePmsId);
        //3.删除权限与用户的关联关系；
        pmsUserRltRepo.deleteRltByPmsId(deletePmsId);
    }

}
