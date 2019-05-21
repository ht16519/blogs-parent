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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

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
     * 级别
     */
    private Integer level;

    /**
     * 父级id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 图标
     */
    private String icon;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_by")
    private Integer createBy;

    @Column(name = "update_by")
    private Integer updateBy;

    private Integer status;


}