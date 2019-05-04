package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @Name CommentsVo
 * @Description
 * @Author wen
 * @Date 2019-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsVo {

    @NotEmpty(message = "消息内容不能为空")
    @Size(max = 255, message = "评论内容不能超过255个字符")
    private String content;

    @Min(value = 0, message = "评论上级id不能小于1")
    private Integer pid;

    @NotNull(message = "文章id不能为空")
    @Min(value = 1, message = "所属文章id不能小于1")
    private Integer articleId;

    @NotNull(message = "评论用户id不能为空")
    @Min(value = 1, message = "所属用户id不能小于1")
    private Integer userId;


}
