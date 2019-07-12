package com.yffd.bcap.uamc.domain.model.role.entity;

import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.common.ddd.domain.entity.IEntityObject;
import com.yffd.bcap.common.model.generator.IdentityGenerator;
import com.yffd.bcap.common.model.system.SysOperator;
import com.yffd.bcap.common.support.exception.BcapValidateException;
import com.yffd.bcap.common.support.util.CollectionUtils;
import com.yffd.bcap.common.support.util.StringUtils;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class RoleEntity implements IEntityObject {
    private static final long serialVersionUID = -5209277505214546383L;
    private SysOperator sysOperator;
    private RoleData data;

    public RoleEntity(RoleData data, SysOperator sysOperator) {
        if (null == data || null == sysOperator)
            throw BcapValidateException.ERROR_PARAMS("构造器参数错误[data:"+data+", sysOperator:"+sysOperator+"]");
        this.sysOperator = sysOperator;
        this.data = data;
    }

    public RoleData add() {
        if (StringUtils.isBlank(this.data.getRoleId())) this.data.setRoleId(IdentityGenerator.getId());
        DataObjectHelper.initPropsForAdd(this.data, this.sysOperator);
        return this.data;
    }

    public RoleData updateById() {
        if (StringUtils.isBlank(this.data.getRoleId()))
            throw BcapValidateException.ERROR_PARAMS("修改失败，数据实体ID不能为空[" + this.data.getClass() + "]");
        DataObjectHelper.initPropsForAdd(this.data, this.sysOperator);
        return this.data;
    }

    public String deleteById() {
        if (StringUtils.isBlank(this.data.getRoleId()))
            throw BcapValidateException.ERROR_PARAMS("删除失败，数据实体ID不能为空[" + this.data.getClass() + "]");
        return this.data.getRoleId();
    }

    public String exsistById() {
        if (StringUtils.isBlank(this.data.getRoleId()))
            throw BcapValidateException.ERROR_PARAMS("删除失败，数据实体ID不能为空[" + this.data.getClass() + "]");
        return this.data.getRoleId();
    }

    /**
     * 启用角色
     */
    public RoleData active() {
        if (StringUtils.isBlank(this.data.getRoleId()))
            throw BcapValidateException.ERROR_PARAMS("启用失败，数据实体ID不能为空[" + this.data.getClass() + "]");
        this.data.setRoleState(ActiveStateEnum.ACTIVE.getCode());
        DataObjectHelper.initPropsForUpdate(this.data, this.sysOperator);
        return this.data;
    }

    /**
     * 禁用角色
     */
    public RoleData deactive() {
        if (StringUtils.isBlank(this.data.getRoleId()))
            throw BcapValidateException.ERROR_PARAMS("停用失败，数据实体ID不能为空[" + this.data.getClass() + "]");
        this.data.setRoleState(ActiveStateEnum.DEACTIVE.getCode());
        DataObjectHelper.initPropsForUpdate(this.data, this.sysOperator);
        return this.data;
    }

    /**
     * 指派组
     * @param groupIds
     * @return  key:groupId, value:roleId
     */
    public Map<String, String> assignToGroup(Set<String> groupIds) {
        if (CollectionUtils.isEmpty(groupIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(groupIds.size());
        for (String groupId : groupIds) {
            map.put(groupId, this.data.getRoleId());
        }
        return map;
    }

    /**
     * 解除已指派组
     * @param groupIds
     * @return  key:groupId, value:roleId
     */
    public Map<String, String> removeRltGroup(Set<String> groupIds) {
        return assignToGroup(groupIds);
    }

    /**
     * 指派权限
     * @param pmsIds
     * @return  key:pmsId, value:roleId
     */
    public Map<String, String> assignToPermissions(Set<String> pmsIds) {
        if (CollectionUtils.isEmpty(pmsIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(pmsIds.size());
        for (String pmsId : pmsIds) {
            map.put(pmsId, this.data.getRoleId());
        }
        return map;
    }

    /**
     * 解除已指派权限
     * @param pmsIds
     * @return  key:pmsId, value:roleId
     */
    public Map<String, String> removeRltPermissions(Set<String> pmsIds) {
        return assignToPermissions(pmsIds);
    }

    /**
     * 解除已指派用户
     * @param userIds
     * @return  key:userId, value:roleId
     */
    public Map<String, String> removeRltUsers(Set<String> userIds) {
        if (CollectionUtils.isEmpty(userIds))
            throw BcapValidateException.ERROR_PARAMS("集合不能为空");
        Map<String, String> map = new HashMap<>(userIds.size());
        for (String userId : userIds) {
            map.put(userId, this.data.getRoleId());
        }
        return map;
    }
}
