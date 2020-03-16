package com.xh.blogs.runner;

import com.xh.blogs.service.IAsyncEsAricleHandleService;
import com.xh.blogs.service.IAsyncSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Name InitializationRabbitMQConfig
 * @Description 初始化执行方法
 * @Author wen
 * @Date 2019-07-23
 */
@Deprecated
//@Component
public class InitializationExecutor {

    @Autowired
    private IAsyncSmsService asyncSmsService;
    @Autowired
    private IAsyncEsAricleHandleService asyncEsAricleHandleService;

    @PostConstruct
    public void init() {
        //1.创建RabbitMQ的邮件配置
        asyncSmsService.createEmailMQ(false);
        //2.创建ES文章相关MQ配置
        asyncEsAricleHandleService.createSearchAricleMQ(false);
    }
}
