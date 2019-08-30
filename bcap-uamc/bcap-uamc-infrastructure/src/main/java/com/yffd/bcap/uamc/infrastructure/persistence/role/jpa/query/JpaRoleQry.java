package com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.query;

import com.yffd.bcap.common.model.exception.InvalidException;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.role.query.RoleQry;
import com.yffd.bcap.uamc.application.role.dto.RoleConditon;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaRoleQry implements RoleQry {

    @Autowired
    private JpaRoleRepoPlus jpaRoleRepoPlus;
    @Autowired
    private JpaGroupRepoPlus jpaGroupRepoPlus;
    @Autowired
    private JpaPermissionRepoPlus jpaPermissionRepoPlus;
    @Autowired
    private JpaUserRepoPlus jpaUserRepoPlus;

    @Override
    public List<RoleData> findList(RoleConditon condition) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        return this.jpaRoleRepoPlus.findAll(JpaRoleSpec.build(condition), sort);
    }

    @Override
    public PageData<RoleData> findPage(RoleConditon conditon, PageInfo pageInfo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = this.toPageable(pageInfo, sort);
        Page<RoleData> page = this.jpaRoleRepoPlus.findAll(JpaRoleSpec.build(conditon), pageable);
        return this.toPageData(page, pageInfo);

        /*pageInfo.setTotalRecord(Integer.parseInt(page.getTotalElements() + ""));
        List<RoleData> list = new ArrayList<>();
        page.forEach(roleData -> list.add(roleData));
        PageData<RoleData> pageData = new PageData<>(pageInfo, list);
        return this.convert(page, pageInfo);*/
    }

    @Override
    public PageData<GroupData> findGroupsByRoleId(String roleId, PageInfo pageInfo) {
        if (BcapStringUtils.isEmpty(roleId)) throw InvalidException.PARAM_IS_EMPTY();
        Page<GroupData> page = jpaGroupRepoPlus.findPageByRoleId(roleId, toPageable(pageInfo));
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<PermissionData> findPermissionsByRoleId(String roleId, PageInfo pageInfo) {
        if (BcapStringUtils.isEmpty(roleId)) throw InvalidException.PARAM_IS_EMPTY();
        Page<PermissionData> page = jpaPermissionRepoPlus.findPageByRoleId(roleId, toPageable(pageInfo));
        return this.toPageData(page, pageInfo);
    }

    @Override
    public PageData<UserData> findUsersByRoleId(String roleId, PageInfo pageInfo) {
        if (BcapStringUtils.isEmpty(roleId)) throw InvalidException.PARAM_IS_EMPTY();
        Page<UserData> page = jpaUserRepoPlus.findPageByRoleId(roleId, toPageable(pageInfo));
        return this.toPageData(page, pageInfo);
    }


    private PageRequest toPageable(PageInfo pageInfo) {
        return PageRequest.of(pageInfo.getPageNum() -1 , pageInfo.getPageSize());
    }
    private PageRequest toPageable(PageInfo pageInfo, Sort sort) {
        return PageRequest.of(pageInfo.getPageNum() -1 , pageInfo.getPageSize(), sort);
    }
    private <T> PageData<T> toPageData(Page<T> page, PageInfo pageInfo) {
        pageInfo.setTotalRecord(Integer.parseInt(page.getTotalElements() + ""));
        List<T> list = new ArrayList<>();
        page.forEach(roleData -> list.add(roleData));
        PageData<T> pageData = new PageData<>(pageInfo, list);
        return pageData;
    }
}
