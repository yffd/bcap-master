package com.yffd.bcap.uamc.application.role.command.handler;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RolePmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;
import com.yffd.bcap.uamc.domain.model.role.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public class RoleCmdHandler {

    private RoleRepo roleRepo;
    private RoleGroupRltRepo roleGroupRltRepo;
    private RolePmsRltRepo rolePmsRltRepo;
    private RoleUserRltRepo roleUserRltRepo;

    public void addRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        if (exsistById(roleEntity))
            throw BcapValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ roleData.getRoleId() +", class："+ roleData.getClass() +"]");
        roleRepo.insertOne(roleEntity.add());
    }

    public Boolean exsistById(RoleEntity roleEntity) {
        return null != roleRepo.findById(roleEntity.exsistById());
    }

    public void updateRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        roleRepo.updateById(roleEntity.updateById());
    }

    @Transactional
    public void deleteRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        RoleService roleService = new RoleService();
        roleService.deleteRoleWithRlt(roleEntity, roleRepo, roleGroupRltRepo, rolePmsRltRepo, roleUserRltRepo);
    }

    /**
     * 启用角色
     * @param roleData
     * @param sysOperator
     */
    public void activeRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        roleRepo.updateById(roleEntity.active());
    }

    /**
     * 禁用角色
     * @param roleData
     * @param sysOperator
     */
    public void deactiveRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        roleRepo.updateById(roleEntity.deactive());
    }

    /**
     * 指派组
     * @param groupIds
     * @param roleData
     * @param sysOperator
     */
    public void assignToGroups(Set<String> groupIds, RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        if (!exsistById(roleEntity))
            throw BcapValidateException.ERROR("角色指派组失败，角色ID不存在["+ roleData.getRoleId() +"]");
        roleGroupRltRepo.addRlt(roleEntity.assignToGroup(groupIds));
    }

    /**
     * 解除已指派组
     * @param groupIds
     * @param roleData
     * @param sysOperator
     */
    public void deleteRltGroups(Set<String> groupIds, RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        roleGroupRltRepo.deleteRlt(roleEntity.removeRltGroup(groupIds));
    }

    /**
     * 指派权限
     * @param pmsIds
     * @param roleData
     * @param sysOperator
     */
    public void assignToPermissions(Set<String> pmsIds, RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        if (!exsistById(roleEntity))
            throw BcapValidateException.ERROR("角色指派权限失败，角色ID不存在["+ roleData.getRoleId() +"]");
        rolePmsRltRepo.addRlt(roleEntity.assignToPermissions(pmsIds));
    }

    /**
     * 解除已指派权限
     * @param pmsIds
     * @param roleData
     * @param sysOperator
     */
    public void deleteRltPermissions(Set<String> pmsIds, RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        rolePmsRltRepo.deleteRlt(roleEntity.removeRltPermissions(pmsIds));
    }

}
