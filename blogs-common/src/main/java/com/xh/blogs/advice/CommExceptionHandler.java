package com.xh.blogs.advice;

import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.JsonUtil;
import com.xh.blogs.vo.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


/**
 * @Name CommonExceptionHandler
 * @Description 全局异常处理类
 * @Date 2019年3月24日
 * @Author wen
 */
@ControllerAdvice
@Slf4j
public class CommExceptionHandler {
	
	/**
	 * 定义exceptionhandler解决未被controller层吸收的exception
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionResult> handlerException(HttpServletRequest request, BusinessException ex) {
		log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResult(ex.getCommomError()));
	}

	/**
	* @Name handlerException
	* @Description 未知的业务异常
	* @Author wen
	* @Date 2019/3/30
	* @param request
	* @param ex
	* @return org.springframework.http.ResponseEntity<ExceptionResult>
	*/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResult> handlerException(HttpServletRequest request, Exception ex) {
		log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResult(EmError.UNKNOWN_ERROR));
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ExceptionResult> handlerException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
		log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResult(EmError.CONTENT_TYPE_NOT_SUPPORTED));
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResult> handlerException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
		log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), request.getRequestURI(), ex);
		return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResult(EmError.REQUEST_METHOD_NOT_SUPPORTED));
	}

}
