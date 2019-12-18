package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.entity.EHotArticle;
import com.xh.blogs.domain.po.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends IBaseMapper<Article> {

    /**
    * @Name selectInfoWithPage
    * @Description 分页查询文章信息包括图片
    * @Author wen
    * @Date 2019/4/23
    * @param parameters
    * @return java.util.List<com.xh.blogs.domain.po.Article>
    */
    List<Article> selectInfoWithPage(Map<String, Object> parameters);

    /**
    * @Name selectById
    * @Description 通过id查询完整信息
    * @Author wen
    * @Date 2019/4/24
    * @param id
    * @return com.xh.blogs.domain.po.Article
    */
    Article selectById(@Param("id") int id);

    /**
    * @Name selectByHost
    * @Description 查询排名文章
    * @Author wen
    * @Date 2019/4/25
    * @param count
    * @return java.util.List<com.xh.blogs.domain.entity.EHostArticle> 
    */
    List<EHotArticle> selectByRank(@Param("sort") int sort, @Param("count") int count);

    /**
    * @Name addFavors
    * @Description 文章收藏量+1
    * @Author wen
    * @Date 2019/4/27
    * @param articleId
    * @return int
    */
    int addFavors(@Param("articleId") int articleId);

    /**
    * @Name minusFavor
    * @Description 文章收藏量-1
    * @Author wen
    * @Date 2019/4/28
    * @param articleId
    * @return int
    */
    int minusFavors(@Param("articleId") int articleId);

    /**
    * @Name addComments
    * @Description 文章评论数+1
    * @Author wen
    * @Date 2019/4/30
    * @param articleId
    * @return int 
    */
    int addComments(@Param("articleId") int articleId);

    /**
     * @Name minusComments
     * @Description 文章评论数-1
     * @Author wen
     * @Date 2019/4/30
     * @param articleId
     * @return int
     */
    int minusComments(@Param("articleId") int articleId);

    /**
    * @Name selectByUserId
    * @Description 查询by 文章和用户id
    * @Author wen
    * @Date 2019/5/7
    * @param id
    * @param userId
    * @return com.xh.blogs.domain.po.Article
    */
    Article selectByUserId(@Param("id") int id, @Param("userId") int userId);

    /**
    * @Name addViews
    * @Description 浏览量 +1
    * @Author wen
    * @Date 2019/5/29
    * @param
    * @return int
    */
    int addViews(@Param("id") int id);
}