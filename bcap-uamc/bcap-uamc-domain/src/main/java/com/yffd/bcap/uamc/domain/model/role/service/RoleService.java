package com.yffd.bcap.uamc.domain.model.role.service;

import com.yffd.bcap.common.ddd.domain.service.IDomainService;
import com.yffd.bcap.uamc.domain.model.relation.RoleGroupRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RolePmsRltRepo;
import com.yffd.bcap.uamc.domain.model.relation.RoleUserRltRepo;
import com.yffd.bcap.uamc.domain.model.role.entity.RoleEntity;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;

public class RoleService implements IDomainService {

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

}