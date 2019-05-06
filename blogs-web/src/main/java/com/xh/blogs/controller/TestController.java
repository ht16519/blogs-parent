package com.xh.blogs.controller;

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

}
