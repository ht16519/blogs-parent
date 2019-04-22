package com.xh.blogs.controller;

import com.xh.blogs.po.User;
import com.xh.blogs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Name UserController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/{id}")
    @ResponseBody
    public User user(@PathVariable("id") int id){
        return userService.getById(id);
    }

}
