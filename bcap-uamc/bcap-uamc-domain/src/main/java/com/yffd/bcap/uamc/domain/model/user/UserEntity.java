package com.yffd.bcap.uamc.domain.model.user;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.model.utils.BcapCollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserEntity extends EntityObject<UserData> {

    public UserEntity(UserData data, SysOperator sysOperator) {
        super(data, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getUserId();
    }

    /**
     * 映射关联关系，组：用户
     * @param groupIds
     * @return          映射关系，key:groupId, value:userId
     */
    public Map<String, String> mappingRltGroup(Set<String> groupIds) {
        if (BcapCollectionUtils.isEmpty(groupIds)) return null;
        Map<String, String> map = new HashMap<>(groupIds.size());
        for (String grouId : groupIds) {
            map.put(grouId, this.data().getUserId());
        }
        return map;
    }

    /**
     * 映射关联关系，角色：用户
     * @param roleIds
     * @return          映射关系，key:roleId, value:userId
     */
    public Map<String, String> mappingRltRole(Set<String> roleIds) {
        if (BcapCollectionUtils.isEmpty(roleIds)) return null;
        Map<String, String> map = new HashMap<>(roleIds.size());
        for (String roleId : roleIds) {
            map.put(roleId, this.data().getUserId());
        }
        return map;
    }

    /**
     * 映射关联关系，权限：用户
     * @param pmsIds
     * @return          映射关系，key:pmsIds, value:userId
     */
    public Map<String, String> mappingRltPermission(Set<String> pmsIds) {
        if (BcapCollectionUtils.isEmpty(pmsIds)) return null;
        Map<String, String> map = new HashMap<>(pmsIds.size());
        for (String pmsId : pmsIds) {
            map.put(pmsId, this.data().getUserId());
        }
        return map;
    }

}
