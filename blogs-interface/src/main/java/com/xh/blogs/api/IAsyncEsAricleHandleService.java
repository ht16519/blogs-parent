package com.xh.blogs.api;

import com.xh.blogs.domain.entity.EArticleHandleMessage;

/**
 * @Name IAsyncEsAricleService
 * @Description 异步ES文章处理业务接口
 * @Author wen
 * @Date 2019-07-22
 */
public interface IAsyncEsAricleHandleService {

    String MQ_ES_ARTICLE_EXCHANGE = "ES_ARTICLE_EXCHANGE";

    String MQ_ES_ARTICLE_QUEUE = "ES_ARTICLE_QUEUE";

    String MQ_ES_ARTICLE_ROUTINGKEY = "ES_ARTICLE_ROUTINGKEY";

    /**
    * @Name sendHandeArticleMsg
    * @Description 发送处理ES文章相关信息
    * @Author wen
    * @Date 2019/7/22
    * @param data
    * @return void
    */
    void sendHandeESArticleMsg(EArticleHandleMessage data);

    /**
    * @Name createESAricleMQ
    * @Description 创建ES文章相关MQ配置
    * @Author wen
    * @Date 2019/7/23
    * @param flag
    * @return void
    */
    void createESAricleMQ(boolean flag);

}
