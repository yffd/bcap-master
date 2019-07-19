package com.yffd.bcap.uamc.domain.model.relation;

public interface PmsUserRltRepo {

    /**
     * 建立关联关系（用户：权限）
     * @param userId    用户ID
     * @param pmsId     权限ID
     */
    void addRlt(String userId, String pmsId);

    /**
     * 移除关联关系（用户：权限）
     * @param userId    用户ID
     * @param pmsId     权限ID
     */
    void deleteRlt(String userId, String pmsId);

    /**
     * 根据用户ID删除关联关系（用户：权限），应用场景：用户删除或失效时
     * @param userId
     */
    void deleteRltByUserId(String userId);

    /**
     * 根据权限ID删除关联关系（用户：权限），应用场景：权限删除或失效时
     * @param pmsId
     */
    void deleteRltByPmsId(String pmsId);
}
