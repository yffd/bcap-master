package com.yffd.bcap.uamc.domain.model.group;

import com.yffd.bcap.common.ddd.domain.entity.EntityObject;
import com.yffd.bcap.common.model.exception.CheckException;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GroupEntity extends EntityObject<GroupData> {
    private static final long serialVersionUID = -7522198672181255411L;

    public GroupEntity(GroupData rootEntity, SysOperator sysOperator) {
        super(rootEntity, sysOperator);
    }

    @Override
    public String identity() {
        return this.data().getGroupId();
    }

    public GroupData active() {
        if (BcapStringUtils.isEmpty(this.identity()))
            throw CheckException.PARAM_IS_EMPTY("启用失败，数据实体ID不能为空[" + this.data().getClass() + "]");
        this.data().setGroupState(ActiveStateEnum.ACTIVE.getCode());
        this.initPropsForUpdate();
        return this.data();
    }

    public GroupData deactive() {
        if (BcapStringUtils.isEmpty(this.identity()))
            throw CheckException.PARAM_IS_EMPTY("停用失败，实体ID不能为空[" + this.data().getClass() + "]");
        this.data().setGroupState(ActiveStateEnum.DEACTIVE.getCode());
        this.initPropsForUpdate();
        return this.data();
    }

    /**
     * 映射关联关系，组：角色
     * @param roleIds
     * @return          映射关系，key:roleId, value:groupId
     */
    public Map<String, String> mappingRltRole(Set<String> roleIds) {
        if (BcapCollectionUtils.isEmpty(roleIds)) return null;
        Map<String, String> map = new HashMap<>(roleIds.size());
        for (String roleId : roleIds) {
            map.put(roleId, this.data().getGroupId());
        }
        return map;
    }

    /**
     * 映射关联关系，权限：角色
     * @param pmsIds
     * @return          映射关系，key:pmsId, value:groupId
     */
    public Map<String, String> mappingRltPermission(Set<String> pmsIds) {
        if (BcapCollectionUtils.isEmpty(pmsIds)) return null;
        Map<String, String> map = new HashMap<>(pmsIds.size());
        for (String pmsId : pmsIds) {
            map.put(pmsId, this.data().getGroupId());
        }
        return map;
    }

    public Set<String> deleteByGroupIds(Set<String> delIds) {
        if (BcapCollectionUtils.isEmpty(delIds))
            throw CheckException.PARAM_IS_EMPTY("将要删除的集合不能为空");
        return delIds;
    }

}
