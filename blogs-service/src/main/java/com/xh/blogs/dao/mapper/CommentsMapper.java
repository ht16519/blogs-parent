package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.entity.EArticleComments;
import com.xh.blogs.domain.entity.EComments;
import com.xh.blogs.domain.po.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
    * @Name selectByCondition
    * @Description 条件查询评论
    * @Author wen
    * @Date 2019/5/6
    * @param parameters
    * @return java.util.List<com.xh.blogs.domain.entity.EComments> 
    */
    List<EComments> selectByCondition(Map<String, Object> parameters);

    /**
    * @Name removeByIds
    * @Description 批量移除
    * @Author wen
    * @Date 2019/5/6
    * @param ids
    * @return int
    */
    int removeByIds(Set<Integer> ids);

    /**
    * @Name selectPidsByIds
    * @Description 查询pids by ids
    * @Author wen
    * @Date 2019/5/6
    * @param ids
    * @return List<Integer>
    */
    List<Integer> selectIdsByPids(Set<Integer> ids);
}