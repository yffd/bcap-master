package com.yffd.bcap.uamc.domain.model.role.service;

import com.yffd.bcap.common.ddd.domain.service.IDomainService;
import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RolePmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;

import java.util.Map;
import java.util.Set;

public class RoleService implements IDomainService {
    private static final RoleService instance = new RoleService();

    private RoleService() {
    }

    public static RoleService getInstance() {
        return instance;
    }

    public Boolean exsistRoleById(RoleEntity roleEntity, RoleRepo roleRepo) {
        return null != roleRepo.findById(roleEntity.exsistById());
    }

    public void deleteRoleWithRlt(RoleEntity roleEntity, RoleRepo roleRepo, RoleGroupRltRepo roleGroupRltRepo,
                                  RolePmsRltRepo rolePmsRltRepo, RoleUserRltRepo roleUserRltRepo) {
        String roleId = roleEntity.deleteById();
        roleRepo.deleteById(roleId);
        //1.删除角色与组的关联关系；
        roleGroupRltRepo.deleteRltByRoleId(roleId);
        //2.删除角色与用户的关联关系；
        roleUserRltRepo.deleteRltByRoleId(roleId);
        //3.删除角色与权限的关联关系
        rolePmsRltRepo.deleteRltByRoleId(roleId);
    }

    /**
     * 添加关联关系（多对多），组：角色
     * @param rltMap    key:groupId, value:roleId
     * @param roleGroupRltRepo
     */
    public void addRltToGroups(Map<String, String> rltMap, RoleGroupRltRepo roleGroupRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String groupId = entry.getKey();
            String roleId = entry.getValue();
            roleGroupRltRepo.addRlt(groupId, roleId);
        }
    }

    /**
     * 移除关联关系（多对多），组：角色
     * @param rltMap    key:groupId, value:roleId
     * @param roleGroupRltRepo
     */
    public void removeRltToGroups(Map<String, String> rltMap, RoleGroupRltRepo roleGroupRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String groupId = entry.getKey();
            String roleId = entry.getValue();
            roleGroupRltRepo.deleteRlt(groupId, roleId);
        }
    }

    /**
     * 添加关联关系（多对多），权限：角色
     * @param rltMap    key:pmsId, value:roleId
     * @param rolePmsRltRepo
     */
    public void addRltToPermissions(Map<String, String> rltMap, RolePmsRltRepo rolePmsRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String pmsId = entry.getKey();
            String roleId = entry.getValue();
            rolePmsRltRepo.addRlt(pmsId, roleId);
        }
    }

    /**
     * 移除关联关系（多对多），权限：角色
     * @param rltMap    key:pmsId, value:roleId
     * @param rolePmsRltRepo
     */
    public void removeRltToPermissions(Map<String, String> rltMap, RolePmsRltRepo rolePmsRltRepo) {
        if (BcapCollectionUtils.isEmpty(rltMap)) return;
        Set<Map.Entry<String, String>> entrySet = rltMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String pmsId = entry.getKey();
            String roleId = entry.getValue();
            rolePmsRltRepo.deleteRlt(pmsId, roleId);
        }
    }

}