package com.xh.blogs.service.impl;

import com.xh.blogs.api.IAsyncSmsService;
import com.xh.blogs.api.IMailService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.dao.cache.RedisCacheUtil;
import com.xh.blogs.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
public class AsyncSmsServiceImpl extends BaseMQServiceImpl implements IAsyncSmsService {

    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private IMailService mailService;

    @Override
    public void createEmailMQ(boolean flag){
        super.createMQConfig(flag, MQ_EMAIL_EXCHANGE, MQ_EMAIL_QUEUE, MQ_EMAIL_ROUTINGKEY);
    }

    @Override
    public <T> void sendEmailMsg(T t) {
        super.send(MQ_EMAIL_EXCHANGE, MQ_EMAIL_ROUTINGKEY, t);
    }

    /**
    * @Name receiveAndSendEmailValidation
    * @Description 接收消息并发送邮件
    * @Author wen
    * @Date 2019/7/23
    * @param data
    * @return void
    */
    @RabbitListener(queues = {MQ_EMAIL_QUEUE})
    private void receiveAndSendEmailValidation(Map<String, Object> data) {
//        System.out.println(JsonUtil.serialize(data));
        try {
            //1-1.组装参数
            String email = (String) data.get(KeyConst.SEND_TO_EMAIL_KEY);
            String templateTitle = (String) data.get(KeyConst.SEND_EMAIL_TITLE_KEY);
            String verifyCode = (String) data.get(KeyConst.RESTUL_EMAIL_CODE_KEY);
            String nickName = (String) data.get(KeyConst.USER_NICK_NAME_KEY);
            //1-2.发送模板邮件
            mailService.sendHtmlMail(email, templateTitle, data, ViewUrl.ACCOUNT_ACTIVATE_EMAIL);
            //2.存储邮箱和验证码
            redisCacheUtil.set(nickName, (email + CommonConst.SEPARATOR + verifyCode));
            log.info("======================== MQ执行:异步发送邮件成功[nickName:{}, toEmail:{}] ========================",  nickName, email);
        } catch (BusinessException e) {
            log.error("MQ执行:Send Email Validation Exception:{}", e);
        }
    }


}
