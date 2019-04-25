package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.entity.EHotArticle;
import com.xh.blogs.domain.po.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends IBaseMapper<Article> {

    /**
    * @Name selectArticleInfoWithPage
    * @Description 分页查询文章信息
    * @Author wen
    * @Date 2019/4/23
    * @param parameters
    * @return java.util.List<com.xh.blogs.domain.po.Article>
    */
    List<Article> selectArticleInfoWithPage(Map<String, Object> parameters);

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
    * @Description 查询热门文章
    * @Author wen
    * @Date 2019/4/25
    * @param count
    * @return java.util.List<com.xh.blogs.domain.entity.EHostArticle> 
    */
    List<EHotArticle> selectByCondition(@Param("orderBy") Integer orderBy, @Param("count") Integer count);
}