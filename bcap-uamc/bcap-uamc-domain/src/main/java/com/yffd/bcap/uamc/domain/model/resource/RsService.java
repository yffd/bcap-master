package com.yffd.bcap.uamc.domain.model.resource;

import com.yffd.bcap.uamc.domain.model.permission.PermissionRepo;

public class RsService {
    private static final RsService instance = new RsService();

    private RsService() {
    }

    public static RsService getInstance() {
        return instance;
    }

    public Boolean exsistMenuById(RsMenuEntity entity, RsMenuRepo resourceMenuRepo) {
        return null != resourceMenuRepo.findById(entity.exsistById());
    }

    public Boolean exsistOperationById(RsOperationEntity entity, RsOperationRepo resourceOperationRepo) {
        return null != resourceOperationRepo.findById(entity.exsistById());
    }

    public void deleteRsWithRlt(RsOperationEntity entity, RsOperationRepo repo, PermissionRepo permissionRepo) {
        repo.deleteById(entity.deleteById());
        //1.资源删除时，要同时删除对应的权限；
        permissionRepo.deleteById(entity.delPmsByOprtId());
    }

    public void deleteRsWithRlt(RsMenuEntity entity, RsMenuRepo repo, PermissionRepo permissionRepo) {
        repo.deleteById(entity.deleteById());
        //1.资源删除时，要同时删除对应的权限；
        permissionRepo.deleteById(entity.delRltPmsByMenuId());
    }

}
