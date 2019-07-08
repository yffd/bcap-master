package com.yffd.bcap.uamc.domain.model.role.service;

import com.yffd.bcap.common.ddd.domain.entity.support.EntitySupport;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.common.support.util.StringUtils;
import com.yffd.bcap.uamc.domain.model.role.aggregate.RoleAgrgt;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;

public class RoleService {

    /*public void addRole(RoleRepo roleRepo, RoleAgrgt roleAgrgt) {
        this.validateAndThrow(roleRepo, roleAgrgt);

        if (!roleRepo.exsist(roleAgrgt.getEntity())) {
            EntitySupport.initPropsForAdd(roleAgrgt.getEntity(), roleAgrgt.getSysOperator());
            roleRepo.add(roleAgrgt.getEntity());
        } else {
            throw BcapValidateException.ERROR_PARAMS("角色已存在，添加失败！");
        }
    }

    public void activeRole(RoleRepo roleRepo, RoleAgrgt roleAgrgt) {
        this.validateAndThrow(roleRepo, roleAgrgt);

        roleAgrgt.enable();
        EntitySupport.initPropsForUpdate(roleAgrgt.getEntity(), roleAgrgt.getSysOperator());
        roleRepo.update(roleAgrgt.getEntity());
    }

    public void inActiveRole(RoleRepo roleRepo, RoleAgrgt roleAgrgt) {
        this.validateAndThrow(roleRepo, roleAgrgt);

        EntitySupport.initPropsForUpdate(roleAgrgt.getEntity(), roleAgrgt.getSysOperator());
        roleAgrgt.disable();
        roleRepo.update(roleAgrgt.getEntity());
    }

    public void deleteRole(RoleRepo roleRepo, RoleAgrgt roleAgrgt) {
        this.validateAndThrow(roleRepo, roleAgrgt);
        //1.删除角色与组的关联关系；
        //TODO
        //2.删除角色与用户的关联关系；
        //3.删除角色与权限的关联关系
        roleRepo.remove(roleAgrgt.getEntity());
    }

    private void validateAndThrow(RoleRepo roleRepo, RoleAgrgt roleAgrgt) {
        if (null == roleRepo) throw BcapValidateException.ERROR_PARAMS("roleRepo参数为空");
        if (null == roleAgrgt || null == roleAgrgt.getEntity() || StringUtils.isBlank(roleAgrgt.getEntity().getRoleId()))
            throw BcapValidateException.ERROR_PARAMS("roleEntity参数无效，必要属性：roleId");
    }*/
}