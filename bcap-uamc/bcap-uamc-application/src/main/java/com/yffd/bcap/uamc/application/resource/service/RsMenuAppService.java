package com.yffd.bcap.uamc.application.resource.service;

import com.yffd.bcap.common.model.exception.CheckException;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.model.permission.PermissionRepo;
import com.yffd.bcap.uamc.domain.model.resource.RsMenuData;
import com.yffd.bcap.uamc.domain.model.resource.RsMenuEntity;
import com.yffd.bcap.uamc.domain.model.resource.RsMenuRepo;
import com.yffd.bcap.uamc.domain.model.resource.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RsMenuAppService {
    @Autowired
    private RsMenuRepo resourceMenuRepo;
    @Autowired
    private PermissionRepo permissionRepo;

    public void addMenu(RsMenuData data, SysOperator sysOperator) {
        RsMenuEntity entity = new RsMenuEntity(data, sysOperator);
        if (RsService.getInstance().exsistMenuById(entity, resourceMenuRepo)) {
            throw CheckException.DATA_EXSIST("添加失败，数据已存在[ID: "+ data.getMenuId() +", class："+ data.getClass() +"]");
        }
        resourceMenuRepo.insertOne(entity.add());
    }

    public void updateMenu(RsMenuData data, SysOperator sysOperator) {
        RsMenuEntity entity = new RsMenuEntity(data, sysOperator);
        resourceMenuRepo.updateById(entity.updateById());
    }

    @Transactional
    public void deleteMenu(RsMenuData data, SysOperator sysOperator) {
        RsMenuEntity entity = new RsMenuEntity(data, sysOperator);
        //1.资源删除时，要同时删除对应的权限；
        RsService.getInstance().deleteRsWithRlt(entity, resourceMenuRepo, permissionRepo);
    }
}
