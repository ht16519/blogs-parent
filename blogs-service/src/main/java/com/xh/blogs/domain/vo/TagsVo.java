package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Name TagsVo
 * @Description
 * @Author wen
 * @Date 2019-05-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagsVo {

    private Integer id;

    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;

    @NotNull(message = "状态不能为空")
    private Integer status;

    @NotNull(message = "排序字段不能为空")
    private Integer sort;
}
