package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.ArticleAccessory;
import org.apache.ibatis.annotations.Param;

public interface ArticleAccessoryMapper extends IBaseMapper<ArticleAccessory> {

    /**
    * @Name removeByArticleId
    * @Description 逻辑删除文章所属图片
    * @Author wen
    * @Date 2019/5/7
    * @param id
    * @return int
    */
    int removeByArticleId(@Param("id") int id);

    /**
    * @Name deleteOldAccessorysByArticleId
    * @Description 删除更改之前的图片by 文章id
    * @Author wen
    * @Date 2019/5/8
    * @param articleId
    * @return int
    */
    int deleteOldAccessorysByArticleId(@Param("articleId") int articleId);
}