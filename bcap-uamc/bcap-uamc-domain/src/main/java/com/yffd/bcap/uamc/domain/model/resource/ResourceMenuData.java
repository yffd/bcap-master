package com.yffd.bcap.uamc.domain.model.resource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uamc_resource_menu")
public class ResourceMenuData extends ResourceEntity {
    private static final long serialVersionUID = -2590355823732845094L;
    @Id
    private String menuId;//菜单ID
    private String menuName;//菜单名称
    private String parentId;//父菜单ID
    private String menuState;//状态，启用=enabled、停用=disabled
    private String menuUrl;//菜单地址
    private String icon;//菜单图标
    private Integer num;//排序号

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
