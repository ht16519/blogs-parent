package com.xh.blogs.service;


import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.ArticleVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.exception.BusinessException;

import java.util.List;


/**
 * @Name IUserService
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
public interface IArticleService {

    /**
    * @Name getArticleInfoWithPage
    * @Description 分页获取文章信息（排序）
    * @Author wen
    * @Date 2019/4/23
    * @param sort
    * @return PageResult<Article>
    */
    PageResult<Article> getInfoWithPage(int sort, int number);

    /**
    * @Name getByIdWithPage
    * @Description 分页查询文章by用户id
    * @Author wen
    * @Date 2019/4/23
    * @param userId
    * @return com.xh.blogs.domain.vo.PageResult<com.xh.blogs.domain.po.Article> 
    */
    PageResult<Article> getByIdWithPage(int userId, int number);

    /**
    * @Name addArticle
    * @Description 新增文章
    * @Author wen
    * @Date 2019/4/24
    * @param articleVo
    * @return Article
    */
    Article addArticle(ArticleVo articleVo) throws BusinessException;

    /**
    * @Name getById
    * @Description 获取文章by 文章和用户id
    * @Author wen
    * @Date 2019/4/24
    * @param id
    * @return com.xh.blogs.domain.po.Article
    */
    Article getByUserId(int id, int userId) throws BusinessException;

    /**
    * @Name getById
    * @Description 获取文章by 文章id
    * @Author wen
    * @Date 2019/5/7
    * @param id
    * @return com.xh.blogs.domain.po.Article
    */
    Article getById(int id) ;

    /**
    * @Name updateFavors
    * @Description 修改文章收藏量
    * @Author wen
    * @Date 2019/4/27
    * @param articleId
    * @return int
    */
    int updateFavors(int articleId);

    /**
    * @Name getByConditionWithPage
    * @Description 条件分页查询
    * @Author wen
    * @Date 2019/5/6
    * @param title
    * @param number
    * @return com.xh.blogs.domain.vo.PageResult<com.xh.blogs.domain.po.Article>
    */
    PageResult<Article> getByConditionWithPage(String title, Integer number);

    /**
    * @Name removeById
    * @Description 移除文章操作
    * @Author wen
    * @Date 2019/5/7
    * @param id
    * @param userId
    * @return int
    */
    int removeById(int id, int userId) throws BusinessException;

    /**
    * @Name updateArticleById
    * @Description 修改文章信息
    * @Author wen
    * @Date 2019/5/8
    * @param articleVo
    * @return Article
    */
    Article updateArticleById(ArticleVo articleVo) throws BusinessException;

    /**
    * @Name getInfoByTagWithPage
    * @Description 通过标签获取文章
    * @Author wen
    * @Date 2019/5/29
    * @param name
    * @param number
    * @return PageResult<Article>
    */
    PageResult<Article> getInfoByTagWithPage(String name, int number);

    /**
    * @Name getInfoByGroupWithPage
    * @Description 查询文章by group
    * @Author wen
    * @Date 2019/5/31
    * @param tagName
    * @param pageNumber
    * @return com.xh.blogs.domain.vo.PageResult<com.xh.blogs.domain.po.Article>
    */
    PageResult<Article> getInfoByGroupWithPage(int tagName, int pageNumber);

    /**
    * @Name addViews
    * @Description 增加浏览量
    * @Author wen
    * @Date 2019/5/29
    * @param id
    * @return
    */
    void addViews(int id, String ip);

    /**
    * @Name getAllByStatus
    * @Description 查询所有有效的文章
    * @Author wen
    * @Date 2019/12/15
    * @param
    * @return java.util.List<com.xh.blogs.domain.po.Article>
    */
    List<Article> getAllByStatus();
}
