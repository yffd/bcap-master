package com.yffd.bcap.uamc.application.resource.dto;

import java.io.Serializable;

public class RsOperationCondition implements Serializable {
    private static final long serialVersionUID = 5597557782576480235L;
    private String oprtId;//操作ID
    private String oprtName;//操作名称
    private String oprtState;//状态

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
}
