package com.yffd.bcap.uamc.domain.model.group;

import com.yffd.bcap.common.ddd.domain.entity.EntityObjectSupport;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.common.support.util.CollectionUtils;
import com.yffd.bcap.common.support.util.StringUtils;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GroupEntity extends EntityObjectSupport<GroupData> {
    private static final long serialVersionUID = -7522198672181255411L;

    public GroupEntity(GroupData rootEntity, SysOperator sysOperator) {
        super(rootEntity, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getGroupId();
    }

    public GroupData active() {
        if (StringUtils.isBlank(this.identity()))
            throw BcapValidateException.ERROR_PARAMS("启用失败，数据实体ID不能为空[" + this.data().getClass() + "]");
        this.data().setGroupState(ActiveStateEnum.ACTIVE.getCode());
        this.initPropsForUpdate();
        return this.data();
    }

    public GroupData deactive() {
        if (StringUtils.isBlank(this.identity()))
            throw BcapValidateException.ERROR_PARAMS("停用失败，实体ID不能为空[" + this.data().getClass() + "]");
        this.data().setGroupState(ActiveStateEnum.DEACTIVE.getCode());
        this.initPropsForUpdate();
        return this.data();
    }

    /**
     * 指派角色
     * @param roleIds
     * @return  key:roleId, value:groupId
     */
    public Map<String, String> assignToRoles(Set<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(roleIds.size());
        for (String roleId : roleIds) {
            map.put(roleId, this.data().getGroupId());
        }
        return map;
    }

    /**
     * 解除指派角色
     * @param roleIds
     * @return  key:roleId, value:groupId
     */
    public Map<String, String> removeRltRoles(Set<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(roleIds.size());
        for (String roleId : roleIds) {
            map.put(roleId, this.data().getGroupId());
        }
        return map;
    }

    /**
     * 指派权限
     * @param pmsIds
     * @return  key:pmsId, value:groupId
     */
    public Map<String, String> assignToPermissions(Set<String> pmsIds) {
        if (CollectionUtils.isEmpty(pmsIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(pmsIds.size());
        for (String pmsId : pmsIds) {
            map.put(pmsId, this.data().getGroupId());
        }
        return map;
    }

    /**
     * 解除指派权限
     * @param pmsIds
     * @return  key:pmsId, value:groupId
     */
    public Map<String, String> removeRltPermissions(Set<String> pmsIds) {
        if (CollectionUtils.isEmpty(pmsIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(pmsIds.size());
        for (String pmsId : pmsIds) {
            map.put(pmsId, this.data().getGroupId());
        }
        return map;
    }

    public Set<String> deleteByGroupIds(Set<String> delIds) {
        if (CollectionUtils.isEmpty(delIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        return delIds;
    }

}
