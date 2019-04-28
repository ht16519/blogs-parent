package com.xh.blogs.api;

import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.PageResult;

/**
 * @Name IFavorsService
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
public interface IFavorsService {

    /**
    * @Name addFavor
    * @Description 新增文章收藏
    * @Author wen
    * @Date 2019/4/27
    * @param ownId
    * @param articleId
    * @return int
    */
    int addFavor(int ownId, int articleId);

    /**
    * @Name getByUserIdWithPage
    * @Description 获取收藏的文章by userId
    * @Author wen
    * @Date 2019/4/28
    * @param userId
    * @param number
    * @return java.util.List<com.xh.blogs.domain.po.Favors>
    */
    PageResult<Article> getByUserIdWithPage(int userId, int number);

    /**
    * @Name unfavor
    * @Description 取消收藏by userId
    * @Author wen
    * @Date 2019/4/28
    * @param id
    * @param userId
    * @return int
    */
    int unfavor(int id, int userId);
}
