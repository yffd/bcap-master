package com.yffd.bcap.uamc.domain.model.group;

import com.yffd.bcap.common.ddd.domain.data.DataObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_group")
public class GroupData extends DataObject {
    private static final long serialVersionUID = 8917064942315854878L;
    @Id
    private String groupId;//组ID
    private String groupName;//组名称
    private String groupState;//状态
    private String remark;//组描述

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
