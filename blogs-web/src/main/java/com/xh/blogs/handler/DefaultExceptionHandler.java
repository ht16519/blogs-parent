package com.xh.blogs.handler;

import com.xh.blogs.consts.ErrorConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.JsonUtil;
import com.xh.blogs.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//@Component
@Slf4j
@Deprecated
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    private static final String CODE_KEY = "code";

    private static final String MSG_KEY = "msg";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        String requestURI = request.getRequestURI();
//        log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), requestURI, ex);
        ModelAndView mv;
        //判断是否ajax请求（本系统ajax请求后缀统一为.json）
        if (requestURI.contains(".json")) {
            ResponseUtil.writer(this.getExceptionResultMap(ex, request, requestURI), response);
            return null;
        } else {
            return new ModelAndView(ViewUrl.DEFAULT_ERROR, this.getExceptionResultMap(ex, request, requestURI));
        }
    }

    /**
    * @Name getExceptionResultMap
    * @Description 获取组装好的异常信息结果map
    * @Author wen
    * @Date 2019/4/22
    * @param ex
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    private Map<String, Object> getExceptionResultMap(Exception ex, HttpServletRequest request, String requestURI){
        Map<String, Object> map = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            if(businessException.getErrCode() == ErrorConst.USER_NOT_LOGGED_IN_EXCEPTION_CODE){
                log.error("请求异常： params:{}, url:{}, errorMsg:{}", JsonUtil.serialize(request.getParameterMap()), requestURI, businessException.getErrMsg());
            }else {
                log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), requestURI, ex);
            }
            map.put(CODE_KEY, businessException.getErrCode());
            map.put(MSG_KEY, businessException.getErrMsg());
            return map;
        } else if(ex instanceof UnauthorizedException) {
            log.error("请求异常： params:{}, url:{}, errorMsg:{}", JsonUtil.serialize(request.getParameterMap()), requestURI, EmError.USER_UNAUTHORIZED.getErrMsg());
            map.put(CODE_KEY, EmError.USER_UNAUTHORIZED.getErrCode());
            map.put(MSG_KEY, EmError.USER_UNAUTHORIZED.getErrMsg());
            return map;
        }else{
            log.error("请求异常： params:{}, url:{}", JsonUtil.serialize(request.getParameterMap()), requestURI, ex);
            map.put(CODE_KEY, EmError.UNKNOWN_ERROR.getErrCode());
            map.put(MSG_KEY, EmError.UNKNOWN_ERROR.getErrMsg());
            return map;
        }
    }

}
