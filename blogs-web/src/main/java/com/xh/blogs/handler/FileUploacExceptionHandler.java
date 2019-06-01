package com.xh.blogs.handler;

import com.xh.blogs.enums.EmError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name FileUploacExceptionHandler
 * @Description 全局异常处理器
 * @Author wen
 * @Date 2019-05-30
 */
@ControllerAdvice
public class FileUploacExceptionHandler {

    /**
    * @Name doException
    * @Description 文件上传异常捕获
    * @Author wen
    * @Date 2019/5/30
    * @param
    * @return org.springframework.web.servlet.ModelAndView
    */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView doException() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", EmError.MAX_UPLOAD_SIZE_EXCEEDED.getErrCode());
        map.put("msg", EmError.MAX_UPLOAD_SIZE_EXCEEDED.getErrMsg());
        return new ModelAndView("/error", map);
    }
}
