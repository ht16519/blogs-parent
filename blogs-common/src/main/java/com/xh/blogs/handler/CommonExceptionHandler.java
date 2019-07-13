package com.xh.blogs.handler;

import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.domain.vo.ExceptionResult;
import com.xh.blogs.error.CommomError;
import com.xh.blogs.utils.RequestUtil;
import com.xh.blogs.utils.WebMvcUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Name CommonExceptionHandler
 * @Description 公共的异常处理类
 * @Author wen
 * @Date 2019-07-12
 */
public class CommonExceptionHandler {

    /**
     * @Name build
     * @Description 构建的异常处理返回结果
     * 返回指定状态码，跳转指定页面
     * @Author wen
     * @Date 2019/7/12
     * @return java.lang.Object
     */
    public Object build(HttpServletRequest request, HttpStatus httpStatus, CommomError ce, String url){
        if(RequestUtil.isAjaxRequest(request)){
            return ResponseEntity.status(403).body(ExceptionResult.build(ce));
        }
        return new ModelAndView(url, WebMvcUtil.createModel(ce));
    }

    /**
    * @Name build
    * @Description 构建的异常处理返回结果（重载方法）
     * 返回状态码200，跳转指定页面
    * @Author wen
    * @Date 2019/7/13
    * @return java.lang.Object
    */
    public Object build(HttpServletRequest request, CommomError ce, String url){
        return this.build(request, HttpStatus.OK, ce, url);
    }

    /**
     * @Name build
     * @Description 构建登录的异常处理返回结果
     * 返回状态码200，跳转登录页面
     * @Author wen
     * @Date 2019/7/12
     * @return java.lang.Object
     */
    public Object buildLoginResult(HttpServletRequest request, CommomError ce){
        return this.build(request, ce, ViewUrl.LOGIN);
    }

    /**
     * @Name build
     * @Description 构建默认错误的异常处理返回结果
     * 返回状态码200，跳转登录错误提示页面
     * @Author wen
     * @Date 2019/7/12
     * @return java.lang.Object
     */
    public Object buildErrorResult(HttpServletRequest request, CommomError ce){
        return this.build(request, ce, ViewUrl.DEFAULT_ERROR);
    }

}
