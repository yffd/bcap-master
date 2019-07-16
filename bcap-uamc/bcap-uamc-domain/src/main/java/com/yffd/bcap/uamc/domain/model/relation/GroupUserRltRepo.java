package com.yffd.bcap.uamc.domain.model.relation;

import java.util.Map;

public interface GroupUserRltRepo {

    /**
     * 建立关联关系（用户：组）
     * @param relationMap   key:userId, value:groupId
     */
    void addRlt(Map<String, String> relationMap);

    /**
     *  移除关联关系（用户：组）
     * @param relationMap   key:userId, value:roleId
     */
    void deleteRlt(Map<String, String> relationMap);

    /**
     * 根据户ID删除关联关系（用户：组），应用场景：用户删除或失效时
     * @param userId
     */
    void deleteRltByUserId(String userId);

    /**
     * 根据组ID删除关联关系（用户：组），应用场景：组删除或失效时
     * @param groupId
     */
    void deleteRltByGroupId(String groupId);
}
