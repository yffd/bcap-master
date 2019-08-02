package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa;

import com.yffd.bcap.uamc.domain.model.resource.RsMenuData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JpaRsMenueRepoPlus extends JpaRepository<RsMenuData, String>, JpaSpecificationExecutor<RsMenuData> {

    @Query(value = "select m.* from uamc_permission p left join uamc_rs_menu m on p.pms_source_id = m.menu_id where p.pms_source_type = 'menu' p.pms_id = ?1", nativeQuery = true)
    RsMenuData findMenuByPmsId(String pmsId);
}
