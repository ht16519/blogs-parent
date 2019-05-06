package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_article_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleContent implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章内容
     */
    private String content;

}