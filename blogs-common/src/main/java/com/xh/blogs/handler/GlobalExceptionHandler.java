package com.xh.blogs.handler;

import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Name FileUploacExceptionHandler
 * @Description 全局异常处理器
 * @Author wen
 * @Date 2019-05-30
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends CommonExceptionHandler{

    /**
    * @Name handleBusinessException
    * @Description 处理业务异常
    * @Author wen
    * @Date 2019/7/12
    * @return java.lang.Object
    */
    @ExceptionHandler(BusinessException.class)
    public Object handleBusinessException(HttpServletRequest request, BusinessException ex){
        //TODO 日志需要记载请求参数
        log.error("BusinessException Exception: [requestIP:{}, requestUrl:{}, errorCode:{}, errorMsg:{}]",
                RequestUtil.getIpAddress(request), request.getRequestURI(), ex.getErrCode(), ex.getErrMsg());
        return super.buildErrorResult(request, ex);
    }

    /**
    * @Name doException
    * @Description 处理文件上传大小超出异常
    * @Author wen
    * @Date 2019/5/30
    */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Object handleMaxUploadSizeExceededException(HttpServletRequest request, MaxUploadSizeExceededException ex){
        log.error("MaxUploadSizeExceededException: [requestUrl:{}, errorMsg:文件上传失败{}]", request.getRequestURI(), ex.getMessage());
        return super.buildErrorResult(request, EmError.MAX_UPLOAD_SIZE_EXCEEDED);
    }

    /**
    * @Name handleRuntimeException
    * @Description 处理所有运行时异常
    * @Author wen
    * @Date 2019/7/12
    */
    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntimeException(HttpServletRequest request, RuntimeException ex){
        //TODO 日志需要记载请求参数
        log.error("System Exception: [requestUrl:{}, errorInfo:{}]", request.getRequestURI(), ex);
        return super.buildErrorResult(request, EmError.UNKNOWN_ERROR);
    }




}
