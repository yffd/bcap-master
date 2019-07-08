package com.yffd.bcap.uamc.domain.model.group.valobj;

import com.yffd.bcap.common.ddd.domain.valobj.IValueObj;
import com.yffd.bcap.uamc.domain.model.group.entity.GroupEntity;
import lombok.Data;

import java.util.List;

@Data
public class GroupTreeVo extends GroupEntity implements IValueObj {
    private static final long serialVersionUID = -1967149415404552587L;
    private List<GroupEntity> children;
}
