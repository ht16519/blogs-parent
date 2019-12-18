package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_role_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

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

}