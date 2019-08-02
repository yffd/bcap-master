package com.yffd.bcap.uamc.application.permission.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.permission.dto.PermissionCriteria;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.resource.ResourceData;
import com.yffd.bcap.uamc.domain.model.resource.RsMenuData;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;

public interface PermissionQry {

    PageData<PermissionData> findPage(PermissionCriteria criteria, PageInfo pageInfo);


    ResourceData findResourceByPmsId(String pmsId, String sourceType);

    RsMenuData findMenuInfoByPmsId(String pmsId);

    RsOperationData findOperationInfoByPmsId(String pmsId);

}
