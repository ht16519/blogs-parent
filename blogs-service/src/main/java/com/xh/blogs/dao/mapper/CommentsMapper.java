package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.entity.EArticleComments;
import com.xh.blogs.domain.entity.EComments;
import com.xh.blogs.domain.po.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsMapper extends IBaseMapper<Comments> {

    /**
    * @Name selectByArticleId
    * @Description 查询评论by articleId
    * @Author wen
    * @Date 2019/4/28
    * @param articleId
    * @param status
    * @return java.util.List<com.xh.blogs.domain.po.Comments>
    */
    List<EComments> selectByArticleId(@Param("articleId") int articleId, @Param("status") int status);

    /**
    * @Name selectSublistByArticleId
    * @Description 获取文章的下级评论
    * @Author wen
    * @Date 2019/5/4
    * @param articleId
    * @param status
    * @return java.util.List<com.xh.blogs.domain.entity.EComments>
    */
    List<EComments> selectSublistByArticleId(@Param("articleId") int articleId, @Param("status") int status);

    /**
    * @Name selectByUserId
    * @Description 查询用户的评论
    * @Author wen
    * @Date 2019/5/4
    * @param userId
    * @param status
    * @return java.util.List<com.xh.blogs.domain.entity.EComments>
    */
    List<EArticleComments> selectByUserId(@Param("userId") int userId, @Param("status") int status);

    /**
    * @Name removeByPid
    * @Description 逻辑删除评论下级节点by 上级pid
    * @Author wen
    * @Date 2019/5/4
    * @param pid
    * @return void
    */
    int removeByPid(@Param("pid") int pid);
}