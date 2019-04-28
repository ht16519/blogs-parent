package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
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
    List<Comments> selectByArticleId(@Param("articleId") int articleId, @Param("status") int status);
}