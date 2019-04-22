package com.xh.blogs.advice;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.JsonUtil;
import com.xh.blogs.vo.WebApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;


/**
 * @Name CommonExceptionHandler
 * @Description 全局异常处理类
 * @Date 2019年3月24日
 * @Author wen
 */
//@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * 定义exceptionhandler解决未被controller层吸收的exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebApiResult handlerException(HttpServletRequest request, Exception ex) {
        log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), request.getRequestURI(), ex);
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            return WebApiResult.fail(businessException.getErrCode(), businessException.getErrMsg());
        } else {
            return WebApiResult.fail(EmError.UNKNOWN_ERROR.getErrCode(), EmError.UNKNOWN_ERROR.getErrMsg());
        }
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebApiResult handlerHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
        log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), request.getRequestURI(), ex);
        return WebApiResult.fail(EmError.CONTENT_TYPE_NOT_SUPPORTED.getErrCode(), EmError.CONTENT_TYPE_NOT_SUPPORTED.getErrMsg());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebApiResult handlerHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), request.getRequestURI(), ex);
        return WebApiResult.fail(EmError.REQUEST_METHOD_NOT_SUPPORTED.getErrCode(), EmError.REQUEST_METHOD_NOT_SUPPORTED.getErrMsg() + request.getMethod());
    }

}
