package com.yffd.bcap.uamc.application.resource.query;

import com.yffd.bcap.uamc.domain.model.resource.RsMenuData;

import java.util.List;

public interface RsMenuQry {

    List<RsMenuData> findAll();
}
