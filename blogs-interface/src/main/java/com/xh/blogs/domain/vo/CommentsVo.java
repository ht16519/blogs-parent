package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private String content;

    private Integer pid;

    @NotNull(message = "文章id不能为空")
    private Integer articleId;

    @NotNull(message = "评论用户id不能为空")
    private Integer userId;


}
