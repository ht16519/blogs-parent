package com.xh.blogs.api;

import com.xh.blogs.domain.es.EsArticle;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.PageResult;

import java.util.List;

/**
 * @Name IEsArticleService
 * @Description
 * @Author wen
 * @Date 2019-05-24
 */
public interface IEsArticleService {

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
    * @Name delete
    * @Description 删除
    * @Author wen
    * @Date 2019/5/24
    * @param esArticle
    * @return void 
    */
    void delete(EsArticle esArticle);

    /**
    * @Name save
    * @Description 保存操作
    * @Author wen
    * @Date 2019/5/24
    * @param esArticle
    * @return EsArticle
    */
    EsArticle save(EsArticle esArticle);

    /**
     * @Name save
     * @Description 保存操作
     * @Author wen
     * @Date 2019/5/30
     * @param article
     * @return void
     */
    void save(Article article);

    /**
    * @Name getById
    * @Description 获取by id
    * @Author wen
    * @Date 2019/5/24
    * @param id
    * @return com.xh.blogs.domain.es.EsArticle
    */
    EsArticle getById(int id);

    /**
    * @Name batchInsert
    * @Description 批量新增
    * @Author wen
    * @Date 2019/5/24
    * @param esArticles
    * @return void
    */
    void batchInsert(List<EsArticle> esArticles);

    /**
    * @Name initEsLibrary
    * @Description 初始化文章的全文检索信息
    * @Author wen
    * @Date 2019/5/24
    * @param 
    * @return void 
    */
    void createEsLibrary();

    /**
    * @Name batchDelete
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
    * @return PageResult<com.xh.blogs.domain.es.EsArticle>
    */
    PageResult<EsArticle> search(String keyword, int number);

}
