package com.yffd.bcap.uamc.domain.model.relation;

public interface GroupUserRltRepo {

    /**
     * 建立关联关系（用户：组）
     * @param userId    用户ID
     * @param groupId   组ID
     */
    void addRlt(String userId, String groupId);

    /**
     * 移除关联关系（用户：组）
     * @param userId    用户ID
     * @param groupId   组ID
     */
    void deleteRlt(String userId, String groupId);

    /**
     * 根据用户ID删除关联关系（用户：组），应用场景：用户删除或失效时
     * @param userId
     */
    void deleteRltByUserId(String userId);

    /**
     * 根据组ID删除关联关系（用户：组），应用场景：组删除或失效时
     * @param groupId
     */
    void deleteRltByGroupId(String groupId);
}
