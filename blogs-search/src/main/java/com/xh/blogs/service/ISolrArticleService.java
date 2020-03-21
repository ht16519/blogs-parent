package com.xh.blogs.service;


import com.xh.blogs.bean.SolrArticle;
import com.xh.blogs.domain.vo.PageResult;

import java.util.List;

/**
 * @Name ISearchService
 * @Description
 * @Author wen
 * @Date 2019-12-04
 */
public interface ISolrArticleService {

    /**
     * @Name deleteById
     * @Description 删除 by id
     * @Author wen
     * @Date 2019/5/24
     * @param id
     * @return void
     */
    void deleteById(int id);

    /**
     * @Name save
     * @Description 保存操作
     * @Author wen
     * @Date 2019/5/24
     * @param solrArticleJson
     * @return EsArticle
     */
    SolrArticle save(String solrArticleJson);

    /**
     * @Name getById
     * @Description 获取by id
     * @Author wen
     * @Date 2019/5/24
     * @param id
     * @return com.xh.blogs.domain.es.EsArticle
     */
    SolrArticle getById(int id);

    /**
     * @Name batchInsert
     * @Description 批量新增
     * @Author wen
     * @Date 2019/5/24
     * @param solrArticleListJson
     * @return void
     */
    void batchInsert(String solrArticleListJson);

    /**
     * @Name createEsLibrary
     * @Description 初始化文章的全文检索信息
     * @Author wen
     * @Date 2019/5/24
     * @param solrArticleListJson
     * @return void
     */
    void createEsLibrary(String solrArticleListJson);

    /**
     * @Name deleteAll
     * @Description 删除所有
     * @Author wen
     * @Date 2019/5/24
     * @param
     * @return void
     */
    void deleteAll();

    /**
     * @Name search
     * @Description 分页条件检索
     * @Author wen
     * @Date 2019/5/24
     * @param
     * @return PageResult
     */
    PageResult<SolrArticle> search(String keywords, long number);

    /**
    * @Name recommend
    * @Description 相关文章推荐
    * @Author wen
    * @Date 2020/3/20
    * @param keywords
    * @return java.util.List<com.xh.blogs.bean.SolrArticle>
    */
    List<SolrArticle> recommend(String keywords);

}
