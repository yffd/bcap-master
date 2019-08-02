package com.yffd.bcap.uamc.infrastructure.persistence.role.jpa;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.role.repository.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class JpaRoleRepo implements RoleRepo {
    private static final Logger LOG = LoggerFactory.getLogger(JpaRoleRepo.class);
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private JpaRoleRepoPlus roleJpa;

    @Override
    public void updateRoleState(RoleData roleData) {
        //方案一
        RoleData po = new RoleData();
        po.setRoleId(roleData.getRoleId());
        po.setRoleState(roleData.getRoleState());
        this.copyPropsForUpdate(roleData, po);
        this.roleJpa.updateRoleState(po);

        /*//方案二
        Optional<RoleData> optional = this.roleJpa.findById(roleData.getRoleId());
        RoleData po = optional.orElse(null);
        if (null == po) {
            LOG.warn("更新数据不存在[id="+ roleData.getRoleId() +"]");
            return;
        }
        RoleData tmp = new RoleData();
        tmp.setRoleState(roleData.getRoleState());
        this.copyProps(tmp, po);
        this.copyPropsForUpdate(roleData, po);
        this.roleJpa.save(po);*/
    }

    @Override
    public void insertOne(RoleData data) {
        if (BcapStringUtils.isEmpty(data.getRoleId())) data.setRoleId(this.nextIdentity());
        this.roleJpa.save(data);
    }

    @Override
    @Transactional
    public void updateById(RoleData data) {
        Optional<RoleData> optional = this.roleJpa.findById(data.getRoleId());
        RoleData po = optional.orElse(null);
        if (null == po) {
            LOG.warn("更新数据不存在[id="+ data.getRoleId() +"]");
            return;
        }
        this.copyPropsIgnoreNull(data, po);
        this.copyPropsForUpdate(data, po);
        this.roleJpa.save(po);
    }

    @Override
    public void deleteById(String id) {
        try {
            this.roleJpa.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOG.warn("更新的数据不存在[Id="+ id +"]");
        }

    }

    @Override
    public RoleData findById(String id) {
        Optional<RoleData> optional = this.roleJpa.findById(id);
        return optional.orElse(null);
    }

    private void copyPropsIgnoreNull(RoleData from, RoleData to) {
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

    private void copyPropsForUpdate(RoleData from, RoleData to) {
        to.setUpdateBy(from.getUpdateBy());
        to.setUpdateTime(from.getUpdateTime());
        to.setVersion(from.getVersion() + 1);
    }
}
