package com.yffd.bcap.uamc.domain.model.role.entity;

import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.common.ddd.domain.entity.IEntityObject;
import com.yffd.bcap.common.model.exception.CheckException;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoleEntity implements IEntityObject {
    private static final long serialVersionUID = -5209277505214546383L;
    private SysOperator sysOperator;
    private RoleData data;

    public RoleEntity(RoleData data, SysOperator sysOperator) {
        if (null == data || null == sysOperator)
            throw CheckException.PARAM_IS_EMPTY("构造器参数错误["+this.getClass()+"]");
        this.sysOperator = sysOperator;
        this.data = data;
    }

    public RoleData add() {
        DataObjectHelper.initPropsForAdd(this.data, this.sysOperator);
        return this.data;
    }

    public RoleData updateById() {
        if (BcapStringUtils.isEmpty(this.data.getRoleId()))
            throw CheckException.PARAM_IS_EMPTY("修改失败，数据实体ID不能为空[" + this.getClass() + "]");
        DataObjectHelper.initPropsForAdd(this.data, this.sysOperator);
        return this.data;
    }

    public String deleteById() {
        if (BcapStringUtils.isEmpty(this.data.getRoleId()))
            throw CheckException.DATA_EXSIST("删除失败，数据实体ID不能为空[" + this.getClass() + "]");
        return this.data.getRoleId();
    }

    public String exsistById() {
        if (BcapStringUtils.isEmpty(this.data.getRoleId()))
            throw CheckException.PARAM_IS_EMPTY("删除失败，数据实体ID不能为空[" + this.getClass() + "]");
        return this.data.getRoleId();
    }

    /**
     * 启用角色
     */
    public RoleData active() {
        if (BcapStringUtils.isEmpty(this.data.getRoleId()))
            throw CheckException.PARAM_IS_EMPTY("启用失败，数据实体ID不能为空[" + this.getClass() + "]");
        this.data.setRoleState(ActiveStateEnum.ACTIVE.getCode());
        DataObjectHelper.initPropsForUpdate(this.data, this.sysOperator);
        return this.data;
    }

    /**
     * 禁用角色
     */
    public RoleData deactive() {
        if (BcapStringUtils.isEmpty(this.data.getRoleId()))
            throw CheckException.PARAM_IS_EMPTY("停用失败，数据实体ID不能为空[" + this.getClass() + "]");
        this.data.setRoleState(ActiveStateEnum.DEACTIVE.getCode());
        DataObjectHelper.initPropsForUpdate(this.data, this.sysOperator);
        return this.data;
    }

    /**
     * 映射关联关系，组：角色
     * @param groupIds
     * @return          映射关系，key:groupId, value:roleId
     */
    public Map<String, String> mappingRltGroup(Set<String> groupIds) {
        if (BcapCollectionUtils.isEmpty(groupIds)) return null;
        Map<String, String> map = new HashMap<>(groupIds.size());
        for (String groupId : groupIds) {
            map.put(groupId, this.data.getRoleId());
        }
        return map;
    }

    /**
     * 映射关联关系，权限：角色
     * @param pmsIds
     * @return          映射关系，key:pmsId, value:roleId
     */
    public Map<String, String> mappingRltPermission(Set<String> pmsIds) {
        if (BcapCollectionUtils.isEmpty(pmsIds)) return null;
        Map<String, String> map = new HashMap<>(pmsIds.size());
        for (String pmsId : pmsIds) {
            map.put(pmsId, this.data.getRoleId());
        }
        return map;
    }

    /**
     * 映射关联关系，用户：角色
     * @param userIds
     * @return          映射关系，key:userId, value:roleId
     */
    public Map<String, String> mappingRltUser(Set<String> userIds) {
        if (BcapCollectionUtils.isEmpty(userIds)) return null;
        Map<String, String> map = new HashMap<>(userIds.size());
        for (String userId : userIds) {
            map.put(userId, this.data.getRoleId());
        }
        return map;
    }

}
