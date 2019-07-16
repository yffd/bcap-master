package com.yffd.bcap.uamc.domain.model.user;

import com.yffd.bcap.common.ddd.domain.entity.EntityObjectSupport;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.common.support.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserEntity extends EntityObjectSupport<UserData> {

    public UserEntity(UserData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getUserId();
    }

    /**
     * 指派组
     * @param groupIds
     * @return
     */
    public Map<String, String> assignToGroups(Set<String> groupIds) {
        if (CollectionUtils.isEmpty(groupIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(groupIds.size());
        for (String grouId : groupIds) {
            map.put(grouId, this.data().getUserId());
        }
        return map;
    }

    /**
     * 解除已指派组
     * @param groupIds
     * @return
     */
    public Map<String, String> removeRltGroups(Set<String> groupIds) {
        return this.assignToGroups(groupIds);
    }

    /**
     * 指派角色
     * @param roleIds
     * @return
     */
    public Map<String, String> assignToRoles(Set<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(roleIds.size());
        for (String roleId : roleIds) {
            map.put(roleId, this.data().getUserId());
        }
        return map;
    }

    /**
     * 解除已指派角色
     * @param roleIds
     * @return
     */
    public Map<String, String> removeRltRoles(Set<String> roleIds) {
        return this.assignToRoles(roleIds);
    }

    /**
     * 指派权限
     * @param pmsIds
     * @return
     */
    public Map<String, String> assignToPermissions(Set<String> pmsIds) {
        if (CollectionUtils.isEmpty(pmsIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(pmsIds.size());
        for (String pmsId : pmsIds) {
            map.put(pmsId, this.data().getUserId());
        }
        return map;
    }

    /**
     * 解除已指派权限
     * @param pmsIds
     * @return
     */
    public Map<String, String> removeRltPermissions(Set<String> pmsIds) {
        return this.assignToPermissions(pmsIds);
    }
}
