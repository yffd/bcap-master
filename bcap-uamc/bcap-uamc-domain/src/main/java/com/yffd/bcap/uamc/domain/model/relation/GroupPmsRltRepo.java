package com.yffd.bcap.uamc.domain.model.relation;

public interface GroupPmsRltRepo {

    /**
     * 建立关联关系（权限：组）
     * @param pmsId     权限ID
     * @param groupId   组ID
     */
    void addRlt(String pmsId, String groupId);

    /**
     * 移除关联关系（权限：组）
     * @param pmsId     权限ID
     * @param groupId   组ID
     */
    void deleteRlt(String pmsId, String groupId);

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
