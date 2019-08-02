package com.yffd.bcap.uamc.infrastructure.persistence.user.jpa.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.support.persistence.jpa.JpaQuerySupport;
import com.yffd.bcap.uamc.application.user.dto.UserCriteria;
import com.yffd.bcap.uamc.application.user.query.UserQry;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import com.yffd.bcap.uamc.infrastructure.persistence.permission.jpa.JpaPermissionRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.JpaRoleRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.user.jpa.JpaUserRepoPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class JpaUserQry extends JpaQuerySupport implements UserQry {
    @Autowired
    private JpaUserRepoPlus jpaUserRepoPlus;
    @Autowired
    private JpaRoleRepoPlus jpaRoleRepoPlus;
    @Autowired
    private JpaPermissionRepoPlus jpaPermissionRepoPlus;

    @Override
    public PageData<UserData> findPage(UserCriteria criteria, PageInfo pageInfo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = this.toPageable(pageInfo, sort);
        Page<UserData> page = this.jpaUserRepoPlus.findAll(JpaUserSpec.build(criteria), pageable);
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<RoleData> findRolesByUserId(String userId, PageInfo pageInfo) {
        Pageable pageable = this.toPageable(pageInfo);
        Page<RoleData> page = jpaRoleRepoPlus.findRolesByUserId(userId, pageable);
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<RoleData> findSecondRolesByUserId(String userId, PageInfo pageInfo) {
        Pageable pageable = this.toPageable(pageInfo);
        Page<RoleData> page = jpaRoleRepoPlus.findSecondRolesByUserId(userId, pageable);
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<PermissionData> findPermissionsByUserId(String userId, PageInfo pageInfo) {
        Pageable pageable = this.toPageable(pageInfo);
        Page<PermissionData> page = jpaPermissionRepoPlus.findPermissionsByUserId(userId, pageable);
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<PermissionData> findSecondPermissionsByUserId(String userId, PageInfo pageInfo) {
        Pageable pageable = this.toPageable(pageInfo);
        Page<PermissionData> page = jpaPermissionRepoPlus.findSecondPermissionsByUserId(userId, pageable);
        return this.toPageData(page, pageInfo);
    }
}
