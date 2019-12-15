package com.xh.blogs.listener;

import com.xh.blogs.consts.MQConst;
import com.xh.blogs.domain.entity.EArticleHandleMessage;
import com.xh.blogs.service.ISolrArticleService;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Name RabbitListener
 * @Description
 * @Author wen
 * @Date 2019-12-15
 */
@Slf4j
@Component
public class RabbitListener {

    private static final String MQ_SEARCH_ARTICLE_QUEUE = MQConst.SEARCH_ARTICLE_QUEUE;

    @Autowired
    private ISolrArticleService solrArticleService;

    /**
     * @Name receiveHandleESArticleRequest
     * @Description 接收并处理全文检索异步请求
     * @Author wen
     * @Date 2019/7/23
     * @param data
     * @return void
     */
    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = {MQ_SEARCH_ARTICLE_QUEUE})
    private void receiveHandleSearchArticleRequest(EArticleHandleMessage data) {
        try {
            if (data.getId() == null) {
                //保存操作
                solrArticleService.save(JsonUtil.serialize(data.getArticle()));
                log.info("MQ执行:保存[文章ID:{}]索引到全文检索服务", data.getArticle().getId());
            } else {
                //删除操作
                solrArticleService.deleteById(data.getId());
                log.info("MQ执行:删除全文检索服务中[文章ID:{}]", data.getId());
            }
        } catch (RuntimeException ex) {
            log.error("MQ执行:全文检索服务处理文章索引失败:", ex);
        }
    }
}
