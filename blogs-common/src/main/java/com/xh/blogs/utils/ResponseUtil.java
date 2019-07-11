package com.xh.blogs.utils;

import com.xh.blogs.domain.vo.ExceptionResult;
import com.xh.blogs.error.CommomError;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Name ResponseUtil
 * @Description
 * @Author wen
 * @Date 2019-07-11
 */
@Slf4j
public class ResponseUtil {

    /**
     * @Name writeContent
     * @Description 将内容输出到浏览器
     * @Author wen
     * @Date 2019/7/11
     * @param code
     * @param msg
     * @return void
     */
    public static void writer(int code, String msg, HttpServletResponse response) {
        if(response == null){
            response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getResponse();
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/json;charset=UTF-8");
        response.setHeader("icop-content-type", "exception");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println(JsonUtil.serialize(ExceptionResult.build(code, msg)));
        } catch (IOException ex) {
            log.error("IOException:{}", ex);
        } finally {
            if(writer != null){
                writer.flush();
                writer.close();
            }
        }
    }

    public static void writer(CommomError ce, HttpServletResponse response) {
        writer(ce.getErrCode(), ce.getErrMsg(), response);
    }

    public static void writer(CommomError ce) {
        writer(ce, null);
    }

}
