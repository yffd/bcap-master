package com.yffd.bcap.uamc.application.organization.assembler;

import com.yffd.bcap.common.model.tree.SimpleTreeBuilder;
import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.uamc.application.organization.dto.OrganizationNode;
import com.yffd.bcap.uamc.domain.model.organization.OrgEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrganizationAssembler {
    private static final Logger LOG = LoggerFactory.getLogger(OrganizationAssembler.class);
    private static SimpleTreeBuilder<OrganizationNode> builder = new SimpleTreeBuilder<OrganizationNode>() {
        @Override
        public OrganizationNode convert(OrganizationNode obj) {
            return obj;
        }
    };

    public static OrganizationNode buildTree(List<OrganizationNode> dataList) {
        return buildTree(dataList, OrgEntity.ROOT_PARENT_ID);
    }

    public static OrganizationNode buildTree(List<OrganizationNode> dataList, String rootParentId) {
        if (BcapCollectionUtils.isEmpty(dataList)) return null;
        OrganizationNode root = new OrganizationNode();
        root.setParentId(rootParentId);
        return builder.buildTree(root, dataList);
    }

}
