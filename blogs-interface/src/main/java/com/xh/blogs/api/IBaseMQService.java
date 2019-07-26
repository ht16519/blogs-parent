package com.xh.blogs.api;

/**
 * @Name IBaseMQService
 * @Description 基本的MQ接口
 * @Author wen
 * @Date 2019-07-22
 */
public interface IBaseMQService {

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
    * @Name createMQConfig
    * @Description 初始化MQ配置
    * @Author wen
    * @Date 2019/7/23
    * @param flag
    * @param exchange
    * @param queue
    * @param routingKey
    * @return void
    */
    void createMQConfig(boolean flag, String exchange, String queue, String routingKey);
}
