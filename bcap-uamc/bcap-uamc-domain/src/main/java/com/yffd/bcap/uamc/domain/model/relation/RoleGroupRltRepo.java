package com.yffd.bcap.uamc.domain.model.relation;

import java.util.Map;

public interface RoleGroupRltRepo {

    /**
     * 建立关联关系（组：角色）
     * @param relationMap   key:groupId, value:roleId
     */
    void addRlt(Map<String, String> relationMap);

    /**
     *  移除关联关系（组：角色）
     * @param relationMap   key:groupId, value:roleId
     */
    void deleteRlt(Map<String, String> relationMap);

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
