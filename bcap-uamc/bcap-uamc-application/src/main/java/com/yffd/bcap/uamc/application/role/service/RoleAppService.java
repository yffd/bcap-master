package com.yffd.bcap.uamc.application.role.service;

import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.uamc.domain.exception.UamcDomainValidateException;
import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RolePmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;
import com.yffd.bcap.uamc.domain.model.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

@Service
public class RoleAppService {
    private RoleService roleService = RoleService.getInstance();
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private RoleGroupRltRepo roleGroupRltRepo;
    @Autowired
    private RolePmsRltRepo rolePmsRltRepo;
    @Autowired
    private RoleUserRltRepo roleUserRltRepo;

    public void addRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        if (roleService.exsistRoleById(roleEntity, roleRepo))
            throw UamcDomainValidateException.ERROR_PARAMS("添加失败，数据已存在[ID: "+ roleData.getRoleId() +", class："+ roleData.getClass() +"]");
        roleRepo.insertOne(roleEntity.add());
    }

    public void updateRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        roleRepo.updateById(roleEntity.updateById());
    }

    @Transactional
    public void deleteRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        roleService.deleteRoleWithRlt(roleEntity, roleRepo, roleGroupRltRepo, rolePmsRltRepo, roleUserRltRepo);
    }

    /**
     * 启用角色
     * @param roleData
     * @param sysOperator
     */
    public void activeRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        roleRepo.updateRoleState(roleEntity.active());
    }

    /**
     * 禁用角色
     * @param roleData
     * @param sysOperator
     */
    public void deactiveRole(RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        roleRepo.updateRoleState(roleEntity.deactive());
    }

    /**
     * 指派组
     * @param groupIds
     * @param roleData
     * @param sysOperator
     */
    public void assignToGroups(Set<String> groupIds, RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        if (!roleService.exsistRoleById(roleEntity, roleRepo))
            throw UamcDomainValidateException.ERROR("角色指派组失败，角色ID不存在["+ roleData.getRoleId() +"]");
        Map<String, String> rltMap = roleEntity.mappingRltGroup(groupIds);    //构建映射关系
        roleService.addRltToGroups(rltMap, roleGroupRltRepo);
    }

    /**
     * 解除已指派组
     * @param groupIds
     * @param roleData
     * @param sysOperator
     */
    public void deleteRltGroups(Set<String> groupIds, RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        Map<String, String> rltMap = roleEntity.mappingRltGroup(groupIds);    //构建映射关系
        roleService.removeRltToGroups(rltMap, roleGroupRltRepo);
    }

    /**
     * 指派权限
     * @param pmsIds
     * @param roleData
     * @param sysOperator
     */
    public void assignToPermissions(Set<String> pmsIds, RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        if (!roleService.exsistRoleById(roleEntity, roleRepo))
            throw UamcDomainValidateException.ERROR("角色指派权限失败，角色ID不存在["+ roleData.getRoleId() +"]");
        Map<String, String> rltMap = roleEntity.mappingRltPermission(pmsIds);    //构建映射关系
        roleService.addRltToPermissions(rltMap, rolePmsRltRepo);
    }

    /**
     * 解除已指派权限
     * @param pmsIds
     * @param roleData
     * @param sysOperator
     */
    public void deleteRltPermissions(Set<String> pmsIds, RoleData roleData, SysOperator sysOperator) {
        RoleEntity roleEntity = new RoleEntity(roleData, sysOperator);
        Map<String, String> rltMap = roleEntity.mappingRltPermission(pmsIds);    //构建映射关系
        roleService.removeRltToPermissions(rltMap, rolePmsRltRepo);
    }

}
