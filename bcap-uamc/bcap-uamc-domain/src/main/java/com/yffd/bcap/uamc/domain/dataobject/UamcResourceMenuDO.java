package com.yffd.bcap.uamc.domain.dataobject;

import com.yffd.bcap.common.support.model.DataObject;
import lombok.Data;

@Data
public class UamcResourceMenuDO extends DataObject{
    private static final long serialVersionUID = 24854088184227134L;
    private String menuId;//菜单ID
    private String menuName;//菜单名称
    private String parentId;//父菜单ID
    private String menuState;//状态，启用=enabled、停用=disabled
    private String menuUrl;//菜单地址
    private String icon;//菜单图标
    private Integer num;//排序号
}
