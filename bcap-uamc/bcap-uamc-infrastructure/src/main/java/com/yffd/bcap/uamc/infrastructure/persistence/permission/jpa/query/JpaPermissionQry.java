package com.yffd.bcap.uamc.infrastructure.persistence.permission.jpa.query;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.page.PageInfo;
import com.yffd.bcap.common.support.persistence.jpa.JpaQuerySupport;
import com.yffd.bcap.uamc.application.permission.dto.PermissionCondition;
import com.yffd.bcap.uamc.application.permission.query.PermissionQry;
import com.yffd.bcap.uamc.domain.constants.enums.SourceTypeEnum;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.resource.ResourceData;
import com.yffd.bcap.uamc.domain.model.resource.RsMenuData;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;
import com.yffd.bcap.uamc.infrastructure.persistence.permission.jpa.JpaPermissionRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa.JpaRsMenueRepoPlus;
import com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa.JpaRsOperationRepoPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class JpaPermissionQry extends JpaQuerySupport implements PermissionQry {

    @Autowired
    private JpaPermissionRepoPlus jpaPermissionRepoPlus;
    @Autowired
    private JpaRsMenueRepoPlus jpaRsMenueRepoPlus;
    @Autowired
    private JpaRsOperationRepoPlus jpaRsOperationRepoPlus;

    @Override
    public PageData<PermissionData> findPage(PermissionCondition condition, PageInfo pageInfo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Page<PermissionData> page = jpaPermissionRepoPlus.findAll(JpaPermissionSpec.build(condition), this.toPageable(pageInfo, sort));
        return this.toPageData(page, pageInfo);
    }

    @Override
    public ResourceData findResourceByPmsId(String pmsId, String sourceType) {
        if (SourceTypeEnum.MENU.getCode().equals(sourceType)) return this.findMenuInfoByPmsId(pmsId);
        if (SourceTypeEnum.OPREATION.getCode().equals(sourceType)) return this.findOperationInfoByPmsId(pmsId);
        return null;
    }

    @Override
    public RsMenuData findMenuInfoByPmsId(String pmsId) {
        return jpaRsMenueRepoPlus.findMenuByPmsId(pmsId);
    }

    @Override
    public RsOperationData findOperationInfoByPmsId(String pmsId) {
        return jpaRsOperationRepoPlus.findOperationByPmsId(pmsId);
    }
}
