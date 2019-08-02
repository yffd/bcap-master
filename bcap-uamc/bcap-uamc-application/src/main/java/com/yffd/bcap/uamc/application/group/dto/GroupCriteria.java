package com.yffd.bcap.uamc.application.group.dto;

import java.io.Serializable;

public class GroupCriteria implements Serializable {
    private static final long serialVersionUID = -1272118714607219115L;
    private String groupName;//组名称
    private String groupState;//状态

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupState() {
        return groupState;
    }

    public void setGroupState(String groupState) {
        this.groupState = groupState;
    }
}
