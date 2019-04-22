package com.xh.blogs.service;

import com.xh.blogs.po.User;

/**
 * @Name IUserService
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
public interface IUserService {

    /**
    * @Name getById获取用户信息
    * @Description 通过用户id
    * @Author wen
    * @Date 2019/4/22
    * @param id
    * @return com.xh.blogs.po.User
    */
    User getById(Integer id);

    /**
    * @Name getByUserName
    * @Description 根据用户名获取用户信息
    * @Author wen
    * @Date 2019/4/22
    * @param userName
    * @return com.xh.blogs.po.User 
    */
    User getByUserName(String userName);

}
