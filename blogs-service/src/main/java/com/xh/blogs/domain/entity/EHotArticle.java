package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name EHostArticle
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EHotArticle {

    private Integer id;

    private String title;

    private Integer views;

    private Integer favors;

}
