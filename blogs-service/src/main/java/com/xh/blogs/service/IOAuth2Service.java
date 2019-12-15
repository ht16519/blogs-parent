package com.xh.blogs.service;

import com.xh.blogs.domain.po.User;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.exception.LoginException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

/**
 * @Name ILoginService
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
public interface IOAuth2Service {

    /**
    * @Name getQQAuthorizeUrl
    * @Description 获取qq授权地址
    * @Author wen
    * @Date 2019/7/10
    * @param request
    * @return java.lang.String 
    */
    String getQQAuthorizeUrl(HttpServletRequest request) throws LoginException;

    /**
    * @Name getOAuthUserByQQAPI
    * @Description 通过QQ API调用获取用户信息
    * @Author wen
    * @Date 2019/7/10
    * @param request
    * @return User
    */
    User getOAuthUserByQQAPI(HttpServletRequest request) throws BusinessException;

}
