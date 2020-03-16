package com.xh.blogs.service;

import com.xh.blogs.domain.po.User;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.exception.LoginException;
import me.zhyd.oauth.model.AuthCallback;

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
    * @return java.lang.String
    */
    String getQQAuthorizeUrl() throws LoginException;

    /**
    * @Name getOAuthUserByQQAPI
    * @Description 通过QQ API调用获取用户信息
    * @Author wen
    * @Date 2019/7/10
    * @param authCallback
    * @return User
    */
    User getOAuthUserByQQAPI(AuthCallback authCallback) throws BusinessException;

}
