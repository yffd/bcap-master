package com.yffd.bcap.uamc.domain.model.relation;

import java.util.Map;

public interface RoleUserRltRepo {

    /**
     *  移除关联关系（用户：角色）
     * @param relationMap   key:userId, value:roleId
     */
    void addRlt(Map<String, String> relationMap);

    /**
     *  移除关联关系（用户：角色）
     * @param relationMap   key:userId, value:roleId
     */
    void deleteRlt(Map<String, String> relationMap);

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
