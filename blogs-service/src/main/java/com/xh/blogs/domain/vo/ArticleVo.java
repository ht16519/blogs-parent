package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

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
    @Size(max = 64, min = 3, message = "标题长度应在3到64位之间")
    private String title;

    @NotNull(message = "组id不能为空")
    private Integer belongGroup;

    private String editor;

    @NotEmpty(message = "标签不能为空")
    private String tags;

    @NotNull(message = "文章类型不能为空")
    @Range(min = 0, max = 1, message = "文章类型只能为1或0")
    private Integer type;

    @NotEmpty(message = "文章内容不能为空")
    @Size(min = 10, message = "内容长度不能少于10个字符")
    private String content;
}
