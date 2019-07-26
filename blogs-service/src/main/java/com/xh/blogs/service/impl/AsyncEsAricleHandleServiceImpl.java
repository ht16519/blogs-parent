package com.xh.blogs.service.impl;

import com.xh.blogs.api.IAsyncEsAricleHandleService;
import com.xh.blogs.api.IEsArticleService;
import com.xh.blogs.domain.entity.EArticleHandleMessage;
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

    @Autowired
    private IEsArticleService esArticleService;

    @Override
    public void sendHandeESArticleMsg(EArticleHandleMessage data) {
        super.send(MQ_ES_ARTICLE_EXCHANGE, MQ_ES_ARTICLE_ROUTINGKEY, data);
    }

    @Override
    public void createESAricleMQ(boolean flag) {
        super.createMQConfig(flag, MQ_ES_ARTICLE_EXCHANGE, MQ_ES_ARTICLE_QUEUE, MQ_ES_ARTICLE_ROUTINGKEY);
    }

    /**
    * @Name receiveHandleESArticleRequest
    * @Description 接收并处理ES异步请求
    * @Author wen
    * @Date 2019/7/23
    * @param data
    * @return void
    */
    @RabbitListener(queues = {MQ_ES_ARTICLE_QUEUE})
    private void receiveHandleESArticleRequest(EArticleHandleMessage data) {
        try {
            if (data.getId() == null) {
                //保存操作
                esArticleService.save(data.getArticle());
                log.info("MQ执行:保存[文章ID:{}]索引到ES", data.getArticle().getId());
            } else {
                //删除操作
                esArticleService.deleteById(data.getId());
                log.info("MQ执行:删除ES中[文章ID:{}]索引", data.getId());
            }
        } catch (RuntimeException ex) {
            log.error("MQ执行:ES处理文章索引失败:", ex);
        }
    }
}
