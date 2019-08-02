package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa;

import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JpaRsOperationRepoPlus extends JpaRepository<RsOperationData, String>, JpaSpecificationExecutor<RsOperationData>  {

    @Query(value = "select o.* from uamc_permission p left join uamc_rs_operation o on p.pms_source_id = o.oprt_id where p.pms_source_type = 'oprt' and p.pms_id = ?1", nativeQuery = true)
    RsOperationData findOperationByPmsId(String pmsId);

}
