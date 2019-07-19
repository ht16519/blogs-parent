package com.xh.blogs.config;

import com.xh.blogs.api.ISmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Name RabbitMQConfig
 * @Description 初始化RabbitMQ的邮件配置
 * @Author wen
 * @Date 2019-07-19
 */
@Configuration
@Slf4j
public class RabbitMQConfig {

    @Bean
    public DirectExchange directExchange(){
        log.info("---------------- 1.创建{}交换机 ----------------", ISmsService.MQ_EMAIL_EXCHANGE);
        return new DirectExchange(ISmsService.MQ_EMAIL_EXCHANGE);
    }

    @Bean
    public Queue queue(){
        log.info("---------------- 2.创建{}队列 ----------------", ISmsService.MQ_EMAIL_QUEUE);
        return new Queue(ISmsService.MQ_EMAIL_QUEUE, true);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange){
        log.info("---------------- 3.绑定{}交换机和{}队列 ----------------", ISmsService.MQ_EMAIL_EXCHANGE, ISmsService.MQ_EMAIL_QUEUE);
        return BindingBuilder.bind(queue).to(directExchange).with(ISmsService.MQ_EMAIL_ROUTINGKEY);
    }

}
