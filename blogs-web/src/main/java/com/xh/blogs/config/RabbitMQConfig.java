package com.xh.blogs.config;

import com.xh.blogs.service.IAsyncEsAricleHandleService;
import com.xh.blogs.service.IAsyncSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
/**
 * @Name RabbitMQConfig
 * @Description 初始化RabbitMQ的配置
 * @Author wen
 * @Date 2019-07-19
 */
@Slf4j
@Deprecated
//@Configuration
public class RabbitMQConfig {

    /** 1.创建邮件MQ **/
    /** 创建{BLOGS_EMAIL_EXCHANGE}交换机**/
    @Bean
    public DirectExchange emailDirectExchange(){
        log.info("---------------- 1.创建{}交换机 ----------------", IAsyncSmsService.MQ_EMAIL_EXCHANGE);
        return new DirectExchange(IAsyncSmsService.MQ_EMAIL_EXCHANGE);
    }

    /**创建{EMAIL_QUEUE}队列**/
    @Bean
    public Queue emailQueue(){
        log.info("---------------- 2.创建{}队列 ----------------", IAsyncSmsService.MQ_EMAIL_QUEUE);
        return new Queue(IAsyncSmsService.MQ_EMAIL_QUEUE, true);
    }

    /**绑定{BLOGS_EMAIL_EXCHANGE}交换机和{EMAIL_QUEUE}队列**/
    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange emailDirectExchange){
        log.info("---------------- 3.绑定{}交换机和{}队列 ----------------", IAsyncSmsService.MQ_EMAIL_EXCHANGE, IAsyncSmsService.MQ_EMAIL_QUEUE);
        return BindingBuilder.bind(emailQueue).to(emailDirectExchange).with(IAsyncSmsService.MQ_EMAIL_ROUTINGKEY);
    }

    /** 2.创建ES文章索引管理MQ **/
    /** 创建{BLOGS_EMAIL_EXCHANGE}交换机**/
    @Bean
    public DirectExchange esArticleDirectExchange(){
        log.info("---------------- 1.创建{}交换机 ----------------", IAsyncEsAricleHandleService.MQ_SEARCH_ARTICLE_EXCHANGE);
        return new DirectExchange(IAsyncEsAricleHandleService.MQ_SEARCH_ARTICLE_EXCHANGE);
    }

    /**创建{EMAIL_QUEUE}队列**/
    @Bean
    public Queue esArticleQueue(){
        log.info("---------------- 2.创建{}队列 ----------------", IAsyncEsAricleHandleService.MQ_SEARCH_ARTICLE_QUEUE);
        return new Queue(IAsyncEsAricleHandleService.MQ_SEARCH_ARTICLE_QUEUE, true);
    }

    /**绑定{BLOGS_EMAIL_EXCHANGE}交换机和{EMAIL_QUEUE}队列**/
    @Bean
    public Binding esArticleBinding(Queue esArticleQueue, DirectExchange esArticleDirectExchange){
        log.info("---------------- 3.绑定{}交换机和{}队列 ----------------", IAsyncEsAricleHandleService.MQ_SEARCH_ARTICLE_EXCHANGE, IAsyncEsAricleHandleService.MQ_SEARCH_ARTICLE_QUEUE);
        return BindingBuilder.bind(esArticleQueue).to(esArticleDirectExchange).with(IAsyncEsAricleHandleService.MQ_SEARCH_ARTICLE_ROUTINGKEY);
    }



}
