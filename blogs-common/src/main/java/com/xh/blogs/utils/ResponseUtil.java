package com.xh.blogs.utils;

import com.xh.blogs.domain.vo.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    public static void writer(int code, String msg, HttpServletResponse response) {
        writer(ExceptionResult.build(code, msg), response);
    }

    public static void writer(Object result) {
        writer(result, ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse());
    }

    public static void writer(Object result, HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (PrintWriter writer = response.getWriter()) {
            writer.println(JsonUtil.serialize(result));
            writer.flush();
        } catch (IOException ex) {
            log.error("Web Response IOException:{}", ex);
        }
    }

}
