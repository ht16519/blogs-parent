package com.xh.blogs.api;


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
    * @return int
    */
    int addArticle(ArticleVo articleVo) throws BusinessException;

    /**
    * @Name getById
    * @Description 获取文章by id
    * @Author wen
    * @Date 2019/4/24
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
}
