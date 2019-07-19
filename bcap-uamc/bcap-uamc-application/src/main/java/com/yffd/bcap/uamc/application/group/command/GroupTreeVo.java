package com.yffd.bcap.uamc.application.group.command;

import com.yffd.bcap.common.ddd.domain.valobj.IValueObject;
import com.yffd.bcap.uamc.domain.model.group.GroupData;

import java.util.List;

public class GroupTreeVo extends GroupData implements IValueObject {
    private static final long serialVersionUID = -1967149415404552587L;
    private List<GroupData> children;

    public List<GroupData> getChildren() {
        return children;
    }

    public void setChildren(List<GroupData> children) {
        this.children = children;
    }
}
