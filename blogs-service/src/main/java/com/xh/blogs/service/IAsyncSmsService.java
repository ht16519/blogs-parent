package com.xh.blogs.service;

import java.util.Map;

/**
 * @Name IAsyncSmsService
 * @Description
 * @Author wen
 * @Date 2019-07-11
 */
public interface IAsyncSmsService {

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
    * @Name sendEmailMsg
    * @Description 发送到邮件消息
    * @Author wen
    * @Date 2019/7/11
    * @param t
    * @return void 
    */
    <T> void sendEmailMsg(T t);

}
