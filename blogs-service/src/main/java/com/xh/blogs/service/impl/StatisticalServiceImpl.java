package com.xh.blogs.service.impl;

import com.xh.blogs.api.IStatisticalService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.dao.mapper.UserMapper;
import com.xh.blogs.domain.entity.EHotArticle;
import com.xh.blogs.domain.entity.EHotUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name StatisticalServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Service
public class StatisticalServiceImpl implements IStatisticalService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<EHotArticle> getHottestArticles(int count) {
        return articleMapper.selectByCondition(CommonConst.ARTICLE_ORDER_HOTTEST, count);
    }

    @Override
    public List<EHotArticle> getLatestsArticles(int count){
        return articleMapper.selectByCondition(CommonConst.ARTICLE_ORDER_NEWSET, count);
    }

    @Override
    public List<EHotUser> getHottestUsers(int count){
        return userMapper.selectByHottest(count);
    }

}
