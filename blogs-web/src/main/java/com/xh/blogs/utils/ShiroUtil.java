package com.xh.blogs.utils;

import com.xh.blogs.domain.po.User;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @Name ShiroUtil
 * @Description
 * @Author wen
 * @Date 2019-04-23
 */
@Slf4j
public class ShiroUtil {

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static User getUser() throws LoginException{
        try {
            return (User) getSubject().getPrincipal();
        } catch (Exception e) {
            throw new LoginException(EmError.USER_SESSION_NOT_EXITS);
        }
    }

    public static Session getSession(){
        return getSubject().getSession(true);
    }

    public static void sessionSetValue(String k, Object v){
        getSession().setAttribute(k, v);
    }

    public static void sessionRemoveValue(String k){
        getSession().removeAttribute(k);
    }

    public static Object sessionGetValue(String key){
        return getSession().getAttribute(key);
    }

    /**
    * @Name checkLogin
    * @Description 检验登陆
    * @Author wen
    * @Date 2019/7/12
    * @param userName
    * @param password
    * @param rememberMe
    * @return void
    */
    public static void checkLogin(String userName, String password, int rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        if (rememberMe == 1) {
            token.setRememberMe(true);
        }
        ShiroUtil.getSubject().login(token);
    }
}
