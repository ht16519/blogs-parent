package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_auth_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthMenu implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级ids
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 权限
     */
    private String permission;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 父级节点id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 图标
     */
    private String icom;

}