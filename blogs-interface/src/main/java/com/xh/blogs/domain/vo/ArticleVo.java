package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Name ArticleVo
 * @Description
 * @Author wen
 * @Date 2019-04-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {

    private Integer id;

    @NotNull(message = "作者id不能为空")
    private Integer authorId;

    @NotEmpty(message = "标题不能为空")
    @Size(max = 18, min = 3, message = "标题长度应在3到18位之间")
    private String title;

    @NotNull(message = "组id不能为空")
    private Integer belongGroup;

    private String editor;

    @NotEmpty(message = "标签不能为空")
    private String tags;

    @NotEmpty(message = "文章内容不能为空")
    @Size(min = 10, message = "内容长度不能少于10个字符")
    private String content;
}
