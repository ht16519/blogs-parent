package com.xh.blogs.service;

import com.xh.blogs.consts.MQConst;
import com.xh.blogs.domain.entity.EArticleHandleMessage;

/**
 * @Name IAsyncEsAricleService
 * @Description 异步ES文章处理业务接口
 * @Author wen
 * @Date 2019-07-22
 */
public interface IAsyncEsAricleHandleService {

    String MQ_SEARCH_ARTICLE_EXCHANGE = MQConst.SEARCH_ARTICLE_EXCHANGE;

    String MQ_SEARCH_ARTICLE_QUEUE = MQConst.SEARCH_ARTICLE_QUEUE;

    String MQ_SEARCH_ARTICLE_ROUTINGKEY = MQConst.SEARCH_ARTICLE_ROUTINGKEY;

    /**
    * @Name sendHandeSearchArticleMsg
    * @Description 发送处理全文检索文章相关信息
    * @Author wen
    * @Date 2019/7/22
    * @param data
    * @return void
    */
    void sendHandeSearchArticleMsg(EArticleHandleMessage data);

    /**
    * @Name createSearchAricleMQ
    * @Description 创建全文检索文章相关MQ配置
    * @Author wen
    * @Date 2019/7/23
    * @param flag
    * @return void
    */
    void createSearchAricleMQ(boolean flag);

}
