package com.xh.blogs.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Name HttpInterceptor
 * @Description 请求处理
 * @Date 2019年1月24日
 * @Author wen
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter{

	/**
	 * 请求处理之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
//		Map<String, String[]> parameterMap = request.getParameterMap();
		System.err.println("===============请求地址：" + url);
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
