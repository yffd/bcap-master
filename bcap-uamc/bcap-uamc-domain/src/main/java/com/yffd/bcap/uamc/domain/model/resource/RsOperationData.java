package com.yffd.bcap.uamc.domain.model.resource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_rs_operation")
public class RsOperationData extends ResourceData {
    private static final long serialVersionUID = 7434329744873777586L;
    @Id
    private String oprtId;//操作ID
    private String oprtName;//操作名称
    private String oprtState;//状态
    private String oprtUrl;//操作地址
    private Integer num;//排序号
    private String remark;//描述

    public String getOprtId() {
        return oprtId;
    }

    public void setOprtId(String oprtId) {
        this.oprtId = oprtId;
    }

    public String getOprtName() {
        return oprtName;
    }

    public void setOprtName(String oprtName) {
        this.oprtName = oprtName;
    }

    public String getOprtState() {
        return oprtState;
    }

    public void setOprtState(String oprtState) {
        this.oprtState = oprtState;
    }

    public String getOprtUrl() {
        return oprtUrl;
    }

    public void setOprtUrl(String oprtUrl) {
        this.oprtUrl = oprtUrl;
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
