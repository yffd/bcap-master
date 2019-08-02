package com.yffd.bcap.uamc.application.organization.assembler;

import com.yffd.bcap.common.model.tree.AbstractTreeBuilder;
import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.common.support.util.SpringBeanUtils;
import com.yffd.bcap.uamc.application.organization.dto.OrganizationTree;
import com.yffd.bcap.uamc.application.organization.query.OrganizationQry;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationAssembler {
    private static final Logger LOG = LoggerFactory.getLogger(OrganizationAssembler.class);
    private static AbstractTreeBuilder<OrgData, OrganizationTree> builder = new AbstractTreeBuilder<OrgData, OrganizationTree>() {
        @Override
        public OrganizationTree convert(OrgData obj) {
            OrganizationTree tree = new OrganizationTree();
            SpringBeanUtils.copyPropsIgnoreNull(obj, tree);
            return tree;
        }
    };

    @Autowired
    private OrganizationQry organizationQry;

    public static OrganizationTree buildTree(List<OrgData> dataList) {
        if (BcapCollectionUtils.isEmpty(dataList)) return null;
        OrgData root = findRoot(dataList);
        if (null == root) {
            LOG.warn("未找到树的根节点【机构】");
            return null;
        }
        return builder.buildTree(root, dataList);
    }

    private static OrgData findRoot(List<OrgData> dataList) {
        for (OrgData data : dataList) {
            if (BcapStringUtils.isEmpty(data.getParentId())) return data;
        }
        return null;
    }
}
