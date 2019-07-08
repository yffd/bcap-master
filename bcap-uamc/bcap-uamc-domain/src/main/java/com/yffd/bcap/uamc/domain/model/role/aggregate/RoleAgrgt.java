package com.yffd.bcap.uamc.domain.model.role.aggregate;

import com.yffd.bcap.common.ddd.domain.entity.aggregate.AggregateRoot;
import com.yffd.bcap.common.ddd.domain.entity.support.EntitySupport;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.common.support.util.StringUtils;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;

import java.util.Set;

public class RoleAgrgt extends AggregateRoot<RoleEntity> {
    private static final long serialVersionUID = -5209277505214546383L;

    public RoleAgrgt(SysOperator sysOperator, RoleEntity entity) {
        super(sysOperator, entity);
    }

    public void add(RoleRepo roleRepo) {
        this.validateAndThrow(roleRepo, this.getEntity());
        if (!roleRepo.exsist(this.getEntity())) {
            EntitySupport.initPropsForAdd(this.getEntity(), this.getSysOperator());
            roleRepo.add(this.getEntity());
        } else {
            throw BcapValidateException.ERROR_PARAMS("角色已存在，添加失败！");
        }
    }

    public void update(RoleRepo roleRepo) {
        this.validateAndThrow(roleRepo, this.getEntity());
        EntitySupport.initPropsForAdd(this.getEntity(), this.getSysOperator());
        roleRepo.update(this.getEntity());
    }

    public void delete(RoleRepo roleRepo) {
        this.validateAndThrow(roleRepo, this.getEntity());
        //1.删除角色与组的关联关系；
        //TODO
        //2.删除角色与用户的关联关系；
        //3.删除角色与权限的关联关系
        roleRepo.remove(this.getEntity());
    }

    /**
     * 启用角色
     * @param roleRepo
     */
    public void active(RoleRepo roleRepo) {
        this.validateAndThrow(roleRepo, this.getEntity());
//        roleAgrgt.enable();
        this.getEntity().setRoleState(ActiveStateEnum.ACTIVE.getCode());
        EntitySupport.initPropsForUpdate(this.getEntity(), this.getSysOperator());
        roleRepo.update(this.getEntity());
    }

    /**
     * 禁用角色
     * @param roleRepo
     */
    public void inActive(RoleRepo roleRepo) {
        this.validateAndThrow(roleRepo, this.getEntity());
        //roleAgrgt.disable();
        this.getEntity().setRoleState(ActiveStateEnum.INACTIVE.getCode());
        EntitySupport.initPropsForUpdate(this.getEntity(), this.getSysOperator());
        roleRepo.update(this.getEntity());
    }

    /**
     * 指派组
     * @param roleRepo
     * @param groupIds
     */
    public void assignToGroup(RoleRepo roleRepo, Set<String> groupIds) {

    }

    /**
     * 指派权限
     * @param roleRepo
     * @param pmsIds
     */
    public void assignToPms(RoleRepo roleRepo, Set<String> pmsIds) {

    }

    private void validateAndThrow(RoleRepo roleRepo, RoleEntity roleEntity) {
        if (null == roleRepo) throw BcapValidateException.ERROR_PARAMS("roleRepo参数为空");
        if (null == roleEntity || StringUtils.isBlank(roleEntity.getRoleId()))
            throw BcapValidateException.ERROR_PARAMS("roleEntity参数无效，必要属性：roleId");
    }

    /*public void enable() {
        this.getEntity().setRoleState(ActiveStateEnum.ACTIVE.getCode());
    }

    public void disable() {
        this.getEntity().setRoleState(ActiveStateEnum.INACTIVE.getCode());
    }*/
}
