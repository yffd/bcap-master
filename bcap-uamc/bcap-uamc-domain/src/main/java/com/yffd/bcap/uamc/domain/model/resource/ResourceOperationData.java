package com.yffd.bcap.uamc.domain.model.resource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_resource_operation")
public class ResourceOperationData extends ResourceEntity {
    private static final long serialVersionUID = 7434329744873777586L;
    @Id
    private String oprtnId;//操作ID
    private String oprtnName;//操作名称
    private String oprtnState;//状态，启用=enabled、停用=disabled
    private String oprtnUrl;//操作地址
    private Integer num;//排序号
    private String remark;//描述

    public String getOprtnId() {
        return oprtnId;
    }

    public void setOprtnId(String oprtnId) {
        this.oprtnId = oprtnId;
    }

    public String getOprtnName() {
        return oprtnName;
    }

    public void setOprtnName(String oprtnName) {
        this.oprtnName = oprtnName;
    }

    public String getOprtnState() {
        return oprtnState;
    }

    public void setOprtnState(String oprtnState) {
        this.oprtnState = oprtnState;
    }

    public String getOprtnUrl() {
        return oprtnUrl;
    }

    public void setOprtnUrl(String oprtnUrl) {
        this.oprtnUrl = oprtnUrl;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
