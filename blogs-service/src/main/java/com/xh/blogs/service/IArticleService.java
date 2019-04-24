package com.xh.blogs.service;


import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.ArticleVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.exception.BusinessException;


/**
 * @Name IUserService
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
public interface IArticleService {

    /**
    * @Name getArticleInfoWithPage
    * @Description 分页获取文章信息
    * @Author wen
    * @Date 2019/4/23
    * @param order
    * @return PageResult<Article>
    */
    PageResult<Article> getArticleInfoWithPage(String order);

    /**
    * @Name getByIdWithPage
    * @Description 分页查询文章by用户id
    * @Author wen
    * @Date 2019/4/23
    * @param id
    * @return com.xh.blogs.domain.vo.PageResult<com.xh.blogs.domain.po.Article> 
    */
    PageResult<Article> getByIdWithPage(int id, int pageNum);

    /**
    * @Name addArticle
    * @Description 新增文章
    * @Author wen
    * @Date 2019/4/24
    * @param articleVo
    * @return int
    */
    int addArticle(ArticleVo articleVo);

    /**
    * @Name getById
    * @Description 获取文章by id
    * @Author wen
    * @Date 2019/4/24
    * @param id
    * @return com.xh.blogs.domain.po.Article
    */
    Article getById(int id) throws BusinessException;
}
