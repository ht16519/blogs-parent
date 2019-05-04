package com.xh.blogs.api;

import com.xh.blogs.domain.entity.EArticleComments;
import com.xh.blogs.domain.entity.EComments;
import com.xh.blogs.domain.vo.CommentsVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.exception.BusinessException;

/**
 * @Name ICommentsService
 * @Description
 * @Author wen
 * @Date 2019-04-27
 */
public interface ICommentsService {

    /**
    * @Name add
    * @Description 新增评论
    * @Author wen
    * @Date 2019/4/27
    * @param commentsVo
    * @return int
    */
    int add(CommentsVo commentsVo) throws BusinessException;

    /**
    * @Name getByArticleIdWithPage
    * @Description 通过文章id获取评论
    * @Author wen
    * @Date 2019/4/27
    * @param articleId
    * @return com.xh.blogs.domain.vo.PageResult<com.xh.blogs.domain.po.Comments>
    */
    PageResult<EComments> getByArticleIdWithPage(int articleId, int number);

    /**
    * @Name getByUserIdWithPage
    * @Description 用户评论列表
    * @Author wen
    * @Date 2019/5/4
    * @param userId
    * @param number
    * @return com.xh.blogs.domain.vo.PageResult<com.xh.blogs.domain.entity.EComments>
    */
    PageResult<EArticleComments> getByUserIdWithPage(int userId, int number);

    /**
    * @Name removeById
    * @Description 移除评论
    * @Author wen
    * @Date 2019/5/4
    * @param id
    * @return int
    */
    int removeById(int id, int userId) throws BusinessException;
}
