package com.xh.blogs.api;

import com.xh.blogs.domain.po.Comments;
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
    PageResult<Comments> getByArticleIdWithPage(int articleId);
}
