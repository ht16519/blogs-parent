package com.xh.blogs.domain.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_article_content")
public class ArticleContent {
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

    /**
     * 获取文章id
     *
     * @return id - 文章id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置文章id
     *
     * @param id 文章id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}