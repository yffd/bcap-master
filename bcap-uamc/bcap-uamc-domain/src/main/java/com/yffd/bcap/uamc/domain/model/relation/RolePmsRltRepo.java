package com.yffd.bcap.uamc.domain.model.relation;

import java.util.Map;

public interface RolePmsRltRepo {

    /**
     * 建立关联关系（权限：角色）
     * @param relationMap   key:pmsId, value:roleId
     */
    void addRlt(Map<String, String> relationMap);

    /**
     *  移除关联关系（权限：角色）
     * @param relationMap   key:pmsId, value:roleId
     */
    void deleteRlt(Map<String, String> relationMap);

    /**
     * 根据权限ID删除关联关系（权限：角色），应用场景：权限删除或失效时
     * @param pmsId
     */
    void deleteRltByPmsId(String pmsId);

    /**
     * 根据角色ID删除关联关系（权限：角色），应用场景：角色删除或失效时
     * @param roleId
     */
    void deleteRltByRoleId(String roleId);
}
