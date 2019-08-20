package com.yffd.bcap.uamc.infrastructure.persistence.group.jpa.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.support.persistence.jpa.JpaQuerySupport;
import com.yffd.bcap.uamc.application.group.dto.GroupCondition;
import com.yffd.bcap.uamc.application.group.query.GroupQry;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import com.yffd.bcap.uamc.infrastructure.persistence.group.jpa.JpaGroupRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.permission.jpa.JpaPermissionRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.JpaRoleRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.user.jpa.JpaUserRepoPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class JpaGroupQry extends JpaQuerySupport implements GroupQry {
    @Autowired
    private JpaGroupRepoPlus jpaGroupRepoPlus;
    @Autowired
    private JpaUserRepoPlus jpaUserRepoPlus;
    @Autowired
    private JpaRoleRepoPlus jpaRoleRepoPlus;
    @Autowired
    private JpaPermissionRepoPlus jpaPermissionRepoPlus;

    @Override
    public PageData<GroupData> findPage(GroupCondition condition, PageInfo pageInfo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Page<GroupData> page = jpaGroupRepoPlus.findAll(JpaGroupSpec.build(condition), this.toPageable(pageInfo, sort));
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<UserData> findUsersByGroupId(String groupId, PageInfo pageInfo) {
        Pageable pageable = this.toPageable(pageInfo);
        Page<UserData> page = jpaUserRepoPlus.findUsersByGroupId(groupId, pageable);
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<RoleData> findRolesByGroupId(String groupId, PageInfo pageInfo) {
        Pageable pageable = this.toPageable(pageInfo);
        Page<RoleData> page = jpaRoleRepoPlus.findRolesByGroupId(groupId, pageable);
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<PermissionData> findPermissionsByGroupId(String groupId, PageInfo pageInfo) {
        Pageable pageable = this.toPageable(pageInfo);
        Page<PermissionData> page = jpaPermissionRepoPlus.findPermissionsByGroupId(groupId, pageable);
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<PermissionData> findSecondPermissionsByGroupId(String groupId, PageInfo pageInfo) {
        Pageable pageable = this.toPageable(pageInfo);
        Page<PermissionData> page = jpaPermissionRepoPlus.findSecondPermissionsByGroupId(groupId, pageable);
        return this.toPageData(page, pageInfo);
    }
}
