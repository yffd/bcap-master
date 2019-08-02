package com.yffd.bcap.uamc.domain.model.relation.data;

import com.yffd.bcap.common.ddd.domain.data.IDataObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_pms_resource_rlt")
public class PmsResourceRltData implements IDataObject {
    private static final long serialVersionUID = -2074267542388583687L;

    @Id
    private String id;
    private String pmsId;
    private String rsId;
    private String rsType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPmsId() {
        return pmsId;
    }

    public void setPmsId(String pmsId) {
        this.pmsId = pmsId;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }
}
