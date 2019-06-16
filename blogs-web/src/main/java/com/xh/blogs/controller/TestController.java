package com.xh.blogs.controller;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Name TestController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    @ResponseBody
    public String test(){
        return "Hellow Spring Boot!";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test01(){
        int i = 1 / 0;
        return "Hellow Spring Boot!";
    }

}
