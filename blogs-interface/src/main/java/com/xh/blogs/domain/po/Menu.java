package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单状态（-1：弃用，1：启用）
     */
    private Integer status;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人id
     */
    @Column(name = "create_by")
    private Integer createBy;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 对应请求地址
     */
    private String url;

    /**
     * 父级菜单id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 所有上级id，格式：(1)(2)(3)
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 菜单等级(1，2，3）
     */
    private Integer level;

    /**
     * 图标
     */
    private String icon;

    /** 排序*/
    private Integer seq;

}