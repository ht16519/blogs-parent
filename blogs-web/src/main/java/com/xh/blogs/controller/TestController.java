package com.xh.blogs.controller;

import com.xh.blogs.api.ISmsService;
import com.xh.blogs.domain.po.User;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private ISmsService smsService;

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
        Map<String, Object> data = new HashMap<>();
        data.put("wwww", "itcloud");
        data.put("userNmae", "1111111");
        smsService.send(ISmsService.MQ_EMAIL_EXCHANGE, ISmsService.MQ_EMAIL_ROUTINGKEY, data);
        return data;
    }

}
