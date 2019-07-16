package com.yffd.bcap.uamc.domain.model.relation;

import java.util.Map;

public interface GroupPmsRltRepo {

    /**
     * 建立关联关系（权限：组）
     * @param relationMap   key:pmsId, value:groupId
     */
    void addRlt(Map<String, String> relationMap);

    /**
     *  移除关联关系（权限：组）
     * @param relationMap   key:pmsId, value:roleId
     */
    void deleteRlt(Map<String, String> relationMap);

    /**
     * 根据权限ID删除关联关系（权限：组），应用场景：权限删除或失效时
     * @param pmsId
     */
    void deleteRltByPmsId(String pmsId);

    /**
     * 根据组ID删除关联关系（权限：组），应用场景：组删除或失效时
     * @param groupId
     */
    void deleteRltByGroupId(String groupId);
}
