package com.xh.blogs.handler;

import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.LoginException;
import com.xh.blogs.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Name FileUploacExceptionHandler
 * @Description 系统登陆异常处理器
 * @Author wen
 * @Date 2019-05-30
 */
@Slf4j
@ControllerAdvice
public class LoginExceptionHandler extends CommonExceptionHandler{

    /**
    * @Name handleLoginException
    * @Description 登录相关异常处理
    * @Author wen
    * @Date 2019/7/12
    * @return java.lang.Object
    */
    @ExceptionHandler(LoginException.class)
    public Object handleLoginException(HttpServletRequest request, LoginException ex){
        //TODO 日志需要记载请求参数
        log.error("Login Exception: [requestIP:{}, requestUrl:{}, errorCode:{}, errorMsg:{}]",
                RequestUtil.getIpAddress(request), request.getRequestURI(), ex.getErrCode(), ex.getErrMsg());
        return super.backLoginResult(request, ex);
    }

    /**
    * @Name handleUnknownAccountException
    * @Description 用户账号不存在异常处理
    * @Author wen
    * @Date 2019/7/12
    * @return java.lang.Object
    */
    @ExceptionHandler(UnknownAccountException.class)
    public Object handleUnknownAccountException(HttpServletRequest request, UnknownAccountException ex){
        //TODO 日志需要记载请求参数
        log.error("Login Exception: [requestIP:{}, requestUrl:{}, inputUserName:{}, errorMsg:用户不存在]",
                RequestUtil.getIpAddress(request), request.getRequestURI(), ex.getMessage());
        return super.backLoginResult(request, EmError.USER_NAME_OR_PASSWORD_ERROR);
    }

    /**
    * @Name handleLockedAccountException
    * @Description 用户冻结被冻结异常处理
    * @Author wen
    * @Date 2019/7/12
    * @return java.lang.Object
    */
    @ExceptionHandler(LockedAccountException.class)
    public Object handleLockedAccountException(HttpServletRequest request, LockedAccountException ex){
        //TODO 日志需要记载请求参数
        log.error("Login Exception: [requestIP:{}, requestUrl:{}, inputUserName:{}, errorMsg:用户被禁用]",
                RequestUtil.getIpAddress(request), request.getRequestURI(), ex.getMessage());
        return super.backLoginResult(request, EmError.USER_IS_DISABLE);
    }

    /**
    * @Name handleAuthenticationException
    * @Description 用户认证失败（用户名或密码错误）异常处理
    * @Author wen
    * @Date 2019/7/12
    * @return java.lang.Object
    */
    @ExceptionHandler(AuthenticationException.class)
    public Object handleAuthenticationException(HttpServletRequest request){
        //TODO 日志需要记载请求参数
        log.error("Login Exception: [requestIP:{}, requestUrl:{}, errorMsg:用户认证失败]",
                RequestUtil.getIpAddress(request), request.getRequestURI());
        return super.backLoginResult(request, EmError.USER_NAME_OR_PASSWORD_ERROR);
    }

}
