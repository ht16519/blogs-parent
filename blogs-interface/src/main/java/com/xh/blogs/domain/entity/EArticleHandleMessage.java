package com.xh.blogs.domain.entity;

import com.xh.blogs.domain.po.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name EArticleMessage
 * @Description
 * @Author wen
 * @Date 2019-07-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EArticleHandleMessage {

    private Integer id;

    private Article article;

    public static EArticleHandleMessage delete(Integer id) {
        return new EArticleHandleMessage(id, null);
    }

    public static EArticleHandleMessage save(Article article) {
        return new EArticleHandleMessage(null, article);
    }

}
