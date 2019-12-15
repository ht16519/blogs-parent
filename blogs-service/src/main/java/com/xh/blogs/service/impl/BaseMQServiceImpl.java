package com.xh.blogs.service.impl;

import com.xh.blogs.service.IBaseMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Name BaseMQServiceImpl
 * @Description 基本的MQ实现类
 * @Author wen
 * @Date 2019-07-22
 */
@Service
@Slf4j
public class BaseMQServiceImpl implements IBaseMQService {

    @Autowired
    protected AmqpAdmin amqpAdmin;
    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @Override
    public <T> void send(String exchange, String routingKey, T t) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, t);
            log.info("MQ通过[交换机:{}],[路由键:{}]发送消息成功-", exchange, routingKey);
        } catch (RuntimeException ex) {
            log.error("MQ通过交换机:{},路由键:{}发送消息失败:", ex);
        }
    }

    @Override
    public void createMQConfig(boolean flag, String exchange, String queue, String routingKey){
        log.info("======================== START初始化RabbitMQ的配置 >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if(flag){
            log.info("---------------- 0.删除原有的{}交换机，及{}队列 ----------------", exchange, queue);
            //--删除已有的--
            amqpAdmin.deleteExchange(exchange);
            amqpAdmin.deleteQueue(queue);
        }
        //1.创建交换机
        amqpAdmin.declareExchange(new DirectExchange(exchange));
        log.info("---------------- 1.创建{}交换机 ----------------", exchange);
        //2.创建队列
        amqpAdmin.declareQueue(new Queue(queue, true));
        log.info("---------------- 2.创建{}队列 ----------------", queue);
        //3.绑定
        Binding binding = new Binding(queue, Binding.DestinationType.QUEUE, exchange, routingKey, null);
        log.info("---------------- 3.绑定{}交换机和{}队列 ----------------", exchange, queue);
        amqpAdmin.declareBinding(binding);
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<< END初始化RabbitMQ的配置 ========================");
    }
}
