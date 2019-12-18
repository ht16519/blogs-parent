package com.xh.blogs.service.impl;

import com.xh.blogs.service.IAsyncEsAricleHandleService;
import com.xh.blogs.domain.entity.EArticleHandleMessage;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Name AsyncEsAricleServiceImpl
 * @Description 异步ES文章处理业务实现
 * @Author wen
 * @Date 2019-07-22
 */
@Service
@Slf4j
public class AsyncEsAricleHandleServiceImpl extends BaseMQServiceImpl implements IAsyncEsAricleHandleService {

    @Override
    public void sendHandeSearchArticleMsg(EArticleHandleMessage data) {
        super.send(MQ_SEARCH_ARTICLE_EXCHANGE, MQ_SEARCH_ARTICLE_ROUTINGKEY, data);
    }

    @Override
    public void createSearchAricleMQ(boolean flag) {
        super.createMQConfig(flag, MQ_SEARCH_ARTICLE_EXCHANGE, MQ_SEARCH_ARTICLE_QUEUE, MQ_SEARCH_ARTICLE_ROUTINGKEY);
    }


}
