package com.xh.blogs.api;

import java.util.Map;

/**
 * @Name ISmsService
 * @Description
 * @Author wen
 * @Date 2019-07-11
 */
public interface ISmsService {

    String MQ_EMAIL_EXCHANGE = "BLOGS_EMAIL_EXCHANGE";

    String MQ_EMAIL_QUEUE = "EMAIL_QUEUE";

    String MQ_EMAIL_ROUTINGKEY = "EMAIL_ROUTINGKEY";

    /**
    * @Name createEmailMQ
    * @Description 创建RabbitMQ的邮件配置
    * @Author wen
    * @Date 2019/7/11
    * @param flag
    * @return void
    */
    void createEmailMQ(boolean flag);

    /**
    * @Name send
    * @Description 发送消息到指定交换机及路由
    * @Author wen
    * @Date 2019/7/11
    * @param exchange
    * @param routingKey
    * @param t
    * @return void 
    */
    <T> void send(String exchange, String routingKey, T t);

    /**
    * @Name sendEmailMsg
    * @Description 发送到邮件消息
    * @Author wen
    * @Date 2019/7/11
    * @param t
    * @return void 
    */
    <T> void sendEmailMsg(T t);

    /**
    * @Name receiveAndSendEmail
    * @Description 接收消息并发送邮件
    * @Author wen
    * @Date 2019/7/11
    * @param data
    * @return void
    */
    void receiveAndSendEmailValidation(Map<String, Object> data);
}
