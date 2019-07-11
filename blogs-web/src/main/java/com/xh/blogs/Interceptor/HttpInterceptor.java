package com.xh.blogs.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Name HttpInterceptor
 * @Description 自定义拦截器，处理请求（可用于日志操作）
 * @Date 2019年1月24日
 * @Author wen
 */
@Slf4j
@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {

    /**
     * 请求处理之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            //获取方法注解
//            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
//            if (accessLimit != null) {
//                boolean needLogin = accessLimit.needLogin();
//                //1.判断用户是否需要登录
//                if (needLogin && (ShiroUtil.getUser() == null)) {
//                    ResponseUtil.writer(response, EmError.USER_NOT_LOGGED_IN);
//                    return false;
//                }
//                //2.判断访问限制
//                long seconds = accessLimit.seconds();
//                int maxCount = accessLimit.maxCount();
//                if (seconds > 0 && maxCount > 0) {
//
//                }
//            }
//        }
        //检查是否限制用户访问接口
//		if(!this.checkAccessLimit(handler, request, response)){
        System.err.println("=======================访问拦截器===================");
        return true;
//		}
//		log.info("---请求路径:{}，---请求参数:{}", request.getRequestURI(), JsonUtil.serialize(request.getParameterMap()));
    }

    /**
     * @param handler
     * @param request
     * @return void
     * @Name checkAccessLimit
     * @Description 检查是否限制用户访问接口
     * @Author wen
     * @Date 2019/7/11
     */
    private boolean checkAccessLimit(Object handler, HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    /**
     * 请求正常处理结束后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 请求结束之后（任何情况，包括异常）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        super.afterCompletion(request, response, handler, ex);
    }

}
