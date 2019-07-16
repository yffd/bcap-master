package com.yffd.bcap.uamc.domain.model.relation;

import java.util.Map;

public interface PmsUserRltRepo {

    /**
     * 建立关联关系（用户：权限）
     * @param relationMap   key:userId, value:pmsId
     */
    void addRlt(Map<String, String> relationMap);

    /**
     *  移除关联关系（用户：权限）
     * @param relationMap   key:userId, value:pmsId
     */
    void deleteRlt(Map<String, String> relationMap);

    /**
     * 根据户ID删除关联关系（用户：权限），应用场景：用户删除或失效时
     * @param userId
     */
    void deleteRltByUserId(String userId);

    /**
     * 根据权限ID删除关联关系（用户：权限），应用场景：权限删除或失效时
     * @param pmsId
     */
    void deleteRltByPmsId(String pmsId);
}
