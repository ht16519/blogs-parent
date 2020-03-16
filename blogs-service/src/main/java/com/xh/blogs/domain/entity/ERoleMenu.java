package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Name EMenuRole
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ERoleMenu implements Serializable {

    private static final long serialVersionUID = 26632745189297144L;

    private Integer id;

    private String name;

    private String permission;

    private Integer sort;

    private String url;

    private Integer level;

    private Integer parentId;

    private String parentIds;

    private String icon;

    private Date createTime;

    private Date updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Integer status;

    private Integer roleId;

    private List<ERoleMenu> childs;

}
