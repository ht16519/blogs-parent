package com.xh.blogs.domain.po;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_role_menu")
public class RoleMenu {
    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单id
     *
     * @return menu_id - 菜单id
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单id
     *
     * @param menuId 菜单id
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}