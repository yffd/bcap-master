package com.yffd.bcap.uamc.domain.model.relation;

public interface RoleUserRltRepo {

    /**
     * 移除关联关系（用户：角色）
     * @param userId    用户ID
     * @param roleId    角色ID
     */
    void addRlt(String userId, String roleId);

    /**
     * 移除关联关系（用户：角色）
     * @param userId    用户ID
     * @param roleId    角色ID
     */
    void deleteRlt(String userId, String roleId);

    /**
     * 根据用户ID删除关联关系（用户：角色），应用场景：用户删除或失效时
     * @param userId
     */
    void deleteRltByUserId(String userId);

    /**
     * 根据角色ID删除关联关系（用户：角色），应用场景：角色删除或失效时
     * @param roleId
     */
    void deleteRltByRoleId(String roleId);


}
