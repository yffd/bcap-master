package com.yffd.bcap.uamc.infrastructure.persistence.role;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaRoleRepo implements RoleRepo {
    private static final Logger LOG = LoggerFactory.getLogger(JpaRoleRepo.class);
    @Autowired
    private RoleJpa roleJpa;

    @Override
    public void insertOne(RoleData data) {
        if (BcapStringUtils.isEmpty(data.getRoleId())) data.setRoleId(this.nextIdentity());
        this.roleJpa.save(data);
    }

    @Override
    public void updateById(RoleData data) {
        Optional<RoleData> optional = this.roleJpa.findById(data.getRoleId());
        RoleData loadData = null;
        try {
            loadData = optional.get();
        } catch (Exception e) {
            LOG.warn("更新数据不存在", e);
        }
        if (null == loadData) return;
        this.copyProps(data, loadData);
        this.roleJpa.save(loadData);
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public RoleData findById(String id) {
        return null;
    }

    @Override
    public List<RoleData> listData(RoleData data) {
        return null;
    }

    private void copyProps(RoleData from, RoleData to) {
        if (BcapStringUtils.isNotEmpty(from.getRoleName())) to.setRoleName(from.getRoleName());
        if (BcapStringUtils.isNotEmpty(from.getRoleState())) to.setRoleState(from.getRoleState());
        if (BcapStringUtils.isNotEmpty(from.getRemark())) to.setRemark(from.getRemark());
        if (BcapStringUtils.isNotEmpty(from.getDelFlag())) to.setDelFlag(from.getDelFlag());
        if (null != from.getVersion()) to.setVersion(from.getVersion());
        if (BcapStringUtils.isNotEmpty(from.getCreateBy())) to.setCreateBy(from.getCreateBy());
        if (null != from.getCreateTime()) to.setCreateTime(from.getCreateTime());
        if (BcapStringUtils.isNotEmpty(from.getUpdateBy())) to.setUpdateBy(from.getUpdateBy());
        if (null != from.getUpdateTime()) to.setUpdateTime(from.getUpdateTime());
    }
}
