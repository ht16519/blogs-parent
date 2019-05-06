package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Table(name = "t_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /** 角色权限*/
    Set<Permission> permissions;

}