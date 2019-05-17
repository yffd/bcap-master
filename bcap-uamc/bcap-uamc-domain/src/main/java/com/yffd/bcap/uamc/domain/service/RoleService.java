package com.yffd.bcap.uamc.domain.service;

import com.yffd.bcap.common.support.exception.BizException;
import com.yffd.bcap.common.support.util.StringUtils;
import com.yffd.bcap.uamc.domain.entities.UamcRole;
import com.yffd.bcap.uamc.domain.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public UamcRole add(UamcRole role) {
        return this.roleRepo.save(role);
    }

    public void update(UamcRole role) {
        if(StringUtils.isBlank(role.getRoleId())) throw BizException.ERROR_PARAMS_EMPTY();
        this.roleRepo.save(role);
    }

}
