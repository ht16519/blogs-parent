package com.xh.blogs.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.JsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    /**
     *
     */
    private static final String CODE_KEY = "code";

    /**
     *
     */
    private static final String MSG_KEY = "msg";


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        String requestURI = request.getRequestURI();
        log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), requestURI, ex);
        ModelAndView mv;
        //判断是否ajax请求（本系统ajax请求后缀统一为.json）
        if (requestURI.endsWith(".json")) {
            this.writerExceptionInfo(response, ex);
            mv = new ModelAndView();
        } else {
            mv = new ModelAndView("/error", this.getExceptionResultMap(ex));
        }
        return mv;
    }

    /**
    * @Name getExceptionResultMap
    * @Description 获取组装好的异常信息结果map
    * @Author wen
    * @Date 2019/4/22
    * @param ex
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    private Map<String, Object> getExceptionResultMap(Exception ex){
        Map<String, Object> map = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            map.put(CODE_KEY, businessException.getErrCode());
            map.put(MSG_KEY, businessException.getErrMsg());
            return map;
        } else {
            map.put(CODE_KEY, EmError.UNKNOWN_ERROR.getErrCode());
            map.put(MSG_KEY, EmError.UNKNOWN_ERROR.getErrMsg());
            return map;
        }
    }

    /**
    * @Name setExceptionInfo
    * @Description 通过response将异常信息输出到前台
    * @Author wen
    * @Date 2019/4/22
    * @param response
    * @param ex
    * @return void
    */
    private void writerExceptionInfo(HttpServletResponse response, Exception ex) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().println(JsonUtil.serialize(this.getExceptionResultMap(ex)));
        } catch (IOException e) {
            log.error("IOException:{}", e);
        }
    }

}
