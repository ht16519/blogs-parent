package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer authorId;

    private String title;

    private Integer belongGroup;

    private String editor;

    private String tags;

    private String content;
}
