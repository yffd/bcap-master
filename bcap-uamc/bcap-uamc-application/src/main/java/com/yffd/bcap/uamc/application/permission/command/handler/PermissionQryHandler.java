package com.yffd.bcap.uamc.application.permission.command.handler;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.permission.command.PermissionCriteriaVo;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.resource.ResourceMenuData;
import com.yffd.bcap.uamc.domain.model.resource.ResourceOperationData;

public interface PermissionQryHandler {

    PageData<PermissionData> listPage(PermissionCriteriaVo criteria, PageInfo pageInfo);

    PageData<ResourceMenuData> rltMenuInfo(String pmsId);

    PageData<ResourceOperationData> rltOperationInfo(String pmsId);

}
