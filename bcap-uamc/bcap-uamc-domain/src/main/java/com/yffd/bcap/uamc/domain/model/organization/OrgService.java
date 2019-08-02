package com.yffd.bcap.uamc.domain.model.organization;

import com.yffd.bcap.common.model.utils.BcapCollectionUtils;
import com.yffd.bcap.common.model.utils.BcapStringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrgService {
    private static final OrgService instance = new OrgService();

    private OrgService() {
    }

    public static OrgService instance() {
        return instance;
    }

    public Boolean exsistOrgById(OrgEntity orgEntity, OrgRepo orgRepo) {
        return null != orgRepo.findById(orgEntity.exsistById());
    }

    public void deleteOrgWithChildren(OrgEntity orgEntity, OrgRepo orgRepo) {
        String delOrgId = orgEntity.deleteById();
        //1.若该机构、以及该机构子机构下拥有用户，则不能删除机构；
        Set<String> tmpOrgIds = new HashSet<>();
        tmpOrgIds.add(delOrgId);
        Set<String> childrenIds = this.orgChildrenIds(delOrgId, orgRepo);
        if (BcapCollectionUtils.isNotEmpty(tmpOrgIds)) tmpOrgIds.addAll(childrenIds);
        if (orgRepo.countUsers(tmpOrgIds) > 0) return;
        //2.若有子机构，则子机构一起删除；
        if (BcapCollectionUtils.isNotEmpty(childrenIds))
            orgRepo.deleteByIds(childrenIds);
        //3.删除机构
        orgRepo.deleteById(delOrgId);
    }

    public Set<String> orgParentIds(String orgId, OrgRepo orgRepo) {
        Set<String> parentIds = new HashSet<>();
        OrgData orgData = orgRepo.findById(orgId);
        String orgPath = orgData.getOrgPath();
        if (BcapStringUtils.isNotEmpty(orgPath) && orgPath.contains(",")) {
            String[] arr = orgPath.split(",");
            List<String> tmp = Arrays.asList(arr);
            parentIds.addAll(tmp);
        }
        return parentIds;
    }

    public Set<String> orgChildrenIds(String orgId, OrgRepo orgRepo) {
        List<OrgData> orgDataList = orgRepo.findChildren(orgId);
        if (BcapCollectionUtils.isEmpty(orgDataList)) return null;
        Set<String> childrenIds = new HashSet<>();
        for (OrgData orgData : orgDataList) {
            childrenIds.add(orgData.getOrgId());
        }
        childrenIds.remove(orgId);
        return childrenIds;
    }
}
