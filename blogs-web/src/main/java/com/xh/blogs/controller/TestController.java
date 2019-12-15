package com.xh.blogs.controller;

import com.xh.blogs.service.IAsyncEsAricleHandleService;
import com.xh.blogs.service.IAsyncSmsService;
import com.xh.blogs.domain.entity.EArticleHandleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name TestController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IAsyncSmsService asyncSmsService;
    @Autowired
    private IAsyncEsAricleHandleService asyncEsAricleHandleService;

    @GetMapping("/index")
    public String test(){
        return "Hellow Spring Boot!";
    }

    @GetMapping("/test")
    public String test01(){
        int i = 1 / 0;
        return "Hellow Spring Boot!";
    }

    @GetMapping("/mq")
    public Object testMQ(){
        EArticleHandleMessage data = new EArticleHandleMessage();
        data.setId(1);
        asyncEsAricleHandleService.sendHandeSearchArticleMsg(data);

//        Map<String, Object> data = new HashMap<>();
//        data.put("wwww", "itcloud");
//        data.put("userNmae", "1111111");
//        asyncSmsService.send(IAsyncSmsService.MQ_EMAIL_EXCHANGE, IAsyncSmsService.MQ_EMAIL_ROUTINGKEY, data);
        return data;
    }

}
