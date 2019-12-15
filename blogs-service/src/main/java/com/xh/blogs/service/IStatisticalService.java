package com.xh.blogs.service;

import com.xh.blogs.domain.entity.EHotArticle;
import com.xh.blogs.domain.entity.EHotUser;

import java.util.List;

/**
 * @Name IStatisticalService
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
public interface IStatisticalService {

    /**
    * @Name getHottestArticles
    * @Description 查询最热的文章
    * @Author wen
    * @Date 2019/4/25
    * @param count
    * @return java.util.List<com.xh.blogs.domain.entity.EHostArticle> 
    */
    List<EHotArticle> getHottestArticles(int count);

    /**
    * @Name getLatestsArticle
    * @Description 查询最新的文章
    * @Author wen
    * @Date 2019/4/25
    * @param count
    * @return java.util.List<com.xh.blogs.domain.entity.EHostArticle> 
    */
    List<EHotArticle> getLatestsArticles(int count);

    /**
    * @Name getHottestUser
    * @Description 获取最热博主
    * @Author wen
    * @Date 2019/4/25
    * @param count
    * @return java.util.List<com.xh.blogs.domain.entity.EHotUser>
    */
    List<EHotUser> getHottestUsers(int count);
}
