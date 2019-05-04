package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.entity.EComments;
import com.xh.blogs.domain.po.Comments;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
//    List<EComments> selectByArticleId(@Param("articleId") int articleId, @Param("status") int status);

    @MapKey("id")
    Map<Integer, EComments> selectByArticleId(@Param("articleId") int articleId, @Param("status") int status);

    List<EComments> selectSublistByArticleId(@Param("articleId") int articleId, @Param("status") int status);
}