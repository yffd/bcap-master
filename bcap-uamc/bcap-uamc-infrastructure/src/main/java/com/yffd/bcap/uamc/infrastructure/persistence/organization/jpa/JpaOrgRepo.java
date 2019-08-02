package com.yffd.bcap.uamc.infrastructure.persistence.organization.jpa;

import com.yffd.bcap.common.support.persistence.jpa.JpaRepositorySupport;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import com.yffd.bcap.uamc.domain.model.organization.OrgRepo;
import com.yffd.bcap.uamc.infrastructure.persistence.user.jpa.JpaUserRepoPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class JpaOrgRepo extends JpaRepositorySupport<OrgData> implements OrgRepo {
    @Autowired
    private JpaOrgRepoPlus orgJpa;
    @Autowired
    private JpaUserRepoPlus userJpa;

    @Override
    public String getDataId(OrgData data) {
        return data.getOrgId();
    }

    @Override
    public void setDataId(OrgData data, String id) {
        data.setOrgId(id);
    }

    @Override
    public JpaRepository<OrgData, String> repository() {
        return orgJpa;
    }

    @Override
    public void deleteByIds(Set<String> orgIds) {
        orgJpa.deleteByIds(orgIds);
    }

    @Override
    public Integer countUsers(Set<String> orgIds) {
        return userJpa.countUsers(orgIds);
    }

    @Override
    public List<OrgData> findChildren(String orgId) {
        return orgJpa.findChildren(orgId);
    }
}
