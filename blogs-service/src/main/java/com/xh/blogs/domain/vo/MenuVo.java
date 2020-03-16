package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Name MenuVo
 * @Description
 * @Author wen
 * @Date 2019-05-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo implements Serializable {

    private static final long serialVersionUID = 8772989326884355239L;

    private Integer id;

    /**
     * 名称
     */
    @NotEmpty(message = "菜单名称不能为空")
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
    private Integer parentId;

    private String parentIds;

    /**
     * 图标
     */
    private String icon;

}
