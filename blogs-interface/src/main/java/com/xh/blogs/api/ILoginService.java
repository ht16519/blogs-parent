package com.xh.blogs.api;

import com.xh.blogs.domain.vo.OAuthUser;
import com.xh.blogs.exception.BusinessException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

/**
 * @Name ILoginService
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
public interface ILoginService {

    /**
    * @Name getQQAuthorizeUrl
    * @Description 获取qq授权地址
    * @Author wen
    * @Date 2019/7/10
    * @param request
    * @return java.lang.String 
    */
    String getQQAuthorizeUrl(HttpServletRequest request) throws BusinessException;

    /**
    * @Name qqLoginCallBack
    * @Description
    * @Author wen
    * @Date 2019/7/10
    * @param request
    * @return OAuthUser
    */
    OAuthUser qqLoginCallBack(HttpServletRequest request) throws BusinessException;
}
