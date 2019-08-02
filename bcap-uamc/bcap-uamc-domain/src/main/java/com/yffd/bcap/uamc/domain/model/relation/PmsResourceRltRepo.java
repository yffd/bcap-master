package com.yffd.bcap.uamc.domain.model.relation;

import com.yffd.bcap.uamc.domain.model.relation.data.PmsResourceRltData;

public interface PmsResourceRltRepo {

    /**
     * 建立关联关系（资源：权限）
     * @param rltData
     */
    void addRlt(PmsResourceRltData rltData);

    /**
     * 移除关联关系（资源：权限）
     * @param rltData
     */
    void deleteRlt(PmsResourceRltData rltData);

    /**
     * 根据资源ID删除关联关系（资源：权限），应用场景：资源删除或失效时
     * @param rsId
     */
    void deleteRltByRsId(String rsId);

    /**
     * 根据权限ID删除关联关系（资源：权限），应用场景：权限删除或失效时
     * @param pmsId
     */
    void deleteRltByPmsId(String pmsId);

}
