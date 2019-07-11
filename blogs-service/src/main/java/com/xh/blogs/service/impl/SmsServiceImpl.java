package com.xh.blogs.service.impl;

import com.xh.blogs.api.IMailService;
import com.xh.blogs.api.ISmsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.dao.cache.RedisCacheUtil;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Name SmsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-07-11
 */
@Service
@Slf4j
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private IMailService mailService;
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Override
    public void createEmailMQ(boolean flag){
        log.info("======================== START初始化RabbitMQ的邮箱配置 >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if(flag){
            log.info("---------------- 0.删除原有的{}交换机，及{}队列 ----------------", MQ_EMAIL_EXCHANGE, MQ_EMAIL_QUEUE);
            //--删除已有的--
            amqpAdmin.deleteExchange(MQ_EMAIL_EXCHANGE);
            amqpAdmin.deleteQueue(MQ_EMAIL_QUEUE);
        }
        //1.创建交换机
        amqpAdmin.declareExchange(new DirectExchange(MQ_EMAIL_EXCHANGE));
        log.info("---------------- 1.创建{}交换机 ----------------", MQ_EMAIL_EXCHANGE);
        //2.创建队列
        amqpAdmin.declareQueue(new Queue(MQ_EMAIL_QUEUE, true));
        log.info("---------------- 2.创建{}队列 ----------------", MQ_EMAIL_QUEUE);
        //3.绑定
        Binding binding = new Binding(MQ_EMAIL_QUEUE, Binding.DestinationType.QUEUE, MQ_EMAIL_EXCHANGE, MQ_EMAIL_ROUTINGKEY, null);
        log.info("---------------- 3.绑定{}交换机和{}队列 ----------------", MQ_EMAIL_EXCHANGE, MQ_EMAIL_QUEUE);
        amqpAdmin.declareBinding(binding);
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<< END初始化RabbitMQ的邮箱配置 ========================");
    }

    @Override
    public <T> void send(String exchange, String routingKey, T t) {
        rabbitTemplate.convertAndSend(exchange, routingKey, t);
    }

    @Override
    public <T> void sendEmailMsg(T t) {
        this.send(MQ_EMAIL_EXCHANGE, MQ_EMAIL_ROUTINGKEY, t);
    }

    @Override
    @RabbitListener(queues = {MQ_EMAIL_QUEUE})
    public void receiveAndSendEmailValidation(Map<String, Object> data) {
        System.out.println(JsonUtil.serialize(data));
//        try {
//            //1-1.组装参数
//            String email = (String) data.get(KeyConst.SEND_TO_EMAIL_KEY);
//            String templateTitle = (String) data.get(KeyConst.SEND_EMAIL_TITLE_KEY);
//            String verifyCode = (String) data.get(KeyConst.RESTUL_EMAIL_CODE_KEY);
//            String nickName = (String) data.get(KeyConst.USER_NICK_NAME_KEY);
//            //1-2.发送模板邮件
//            mailService.sendHtmlMail(email, templateTitle, data, ViewUrl.ACCOUNT_ACTIVATE_EMAIL);
//            //2.存储邮箱和验证码
//            redisCacheUtil.set(nickName, (email + CommonConst.SEPARATOR + verifyCode));
//            log.info("======================== 异步发送邮件成功！ ========================");
//        } catch (BusinessException e) {
//            log.error("Send Email Validation Exception:{}", e);
//        }
    }


}
