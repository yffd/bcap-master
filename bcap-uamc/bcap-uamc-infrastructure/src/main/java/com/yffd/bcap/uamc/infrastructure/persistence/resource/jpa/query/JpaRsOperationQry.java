package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.support.persistence.jpa.JpaQuerySupport;
import com.yffd.bcap.uamc.application.resource.dto.RsOperationCondition;
import com.yffd.bcap.uamc.application.resource.query.RsOperationQry;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;
import com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa.JpaRsOperationRepoPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class JpaRsOperationQry extends JpaQuerySupport implements RsOperationQry {
    @Autowired
    private JpaRsOperationRepoPlus jpaRsOperationRepoPlus;

    @Override
    public PageData<RsOperationData> findPage(RsOperationCondition condition, PageInfo pageInfo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Page<RsOperationData> page = jpaRsOperationRepoPlus.findAll(JpaRsOperationSpec.build(condition), this.toPageable(pageInfo, sort));
        return this.toPageData(page, pageInfo);
    }
}
