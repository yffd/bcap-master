package com.yffd.bcap.uamc.domain.model.relation;

public interface RoleGroupRltRepo {

    /**
     * 建立关联关系（组：角色）
     * @param groupId   组ID
     * @param roleId    角色ID
     */
    void addRlt(String groupId, String roleId);

    /**
     * 移除关联关系（组：角色）
     * @param groupId   组ID
     * @param roleId    角色ID
     */
    void deleteRlt(String groupId, String roleId);

    /**
     * 根据组ID删除关联关系（组：角色），应用场景：组删除或失效时
     * @param groupId
     */
    void deleteRltByGroupId(String groupId);

    /**
     * 根据角色ID删除关联关系（组：角色），应用场景：角色删除或失效时
     * @param roleId
     */
    void deleteRltByRoleId(String roleId);

}
