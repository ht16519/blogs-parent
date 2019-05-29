package com.xh.blogs.utils;

import com.xh.blogs.domain.po.User;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
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

    public static User getUser()throws BusinessException{
        try {
            return (User) getSubject().getPrincipal();
        } catch (Exception e) {
            throw new BusinessException(EmError.USER_SESSION_NOT_EXITS);
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
}
