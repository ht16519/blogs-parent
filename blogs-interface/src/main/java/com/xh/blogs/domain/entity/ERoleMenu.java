package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Name EMenuRole
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ERoleMenu {

    private Integer id;

    private String name;

    private Integer status;

    private Date updateTime;

    private Integer createBy;

    private Date createTime;

    private String url;

    private Integer parentId;

    private String parentIds;

    private Integer level;

    private String icon;

    private Integer seq;

    private Integer roleId;

}
