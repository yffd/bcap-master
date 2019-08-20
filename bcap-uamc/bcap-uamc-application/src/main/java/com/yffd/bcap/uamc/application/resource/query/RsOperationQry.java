package com.yffd.bcap.uamc.application.resource.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.uamc.application.resource.dto.RsOperationCondition;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;

public interface RsOperationQry {

    PageData<RsOperationData> findPage(RsOperationCondition condition, PageInfo pageInfo);

}
