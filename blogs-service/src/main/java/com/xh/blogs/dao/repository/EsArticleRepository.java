package com.xh.blogs.dao.repository;

import com.xh.blogs.domain.es.EsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Name ArticleRepository
 * @Description
 * @Author wen
 * @Date 2019-05-24
 */
public interface EsArticleRepository extends ElasticsearchRepository<EsArticle, Integer>{

    /**
    * @Name findDistinctEsArticleByTitleContainingOrSummaryContainingOrTagsContaining
    * @Description 通过文章标题或者简介或者标签全文检索
    * @Author wen
    * @Date 2019/5/24
    * @param title
    * @param summary
    * @param tags
    * @param pageable
    * @return org.springframework.data.domain.Page<com.xh.blogs.domain.es.EsArticle>
    */
    Page<EsArticle> findDistinctEsArticleByTitleContainingOrSummaryContainingOrTagsContaining(String title, String summary, String tags, Pageable pageable);

    /**
    * @Name findById
    * @Description 通过id检索
    * @Author wen
    * @Date 2019/5/24
    * @param id
    * @return com.xh.blogs.domain.es.EsArticle
    */
    EsArticle findById(int id);
}
