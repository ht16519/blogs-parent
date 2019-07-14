package com.xh.blogs.utils;

import com.xh.blogs.consts.HttpConst;
import com.xh.blogs.error.CommomError;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name WebMvcUtil
 * @Description
 * @Author wen
 * @Date 2019-07-12
 */
public class WebMvcUtil {

    /**
    * @Name createModel
    * @Description 创建一个通用mvc返回ModelMap对象
    * @Author wen
    * @Date 2019/7/12
    * @param code
    * @param msg
    * @return org.springframework.ui.ModelMap
    */
    public static ModelMap createModel(int code, String msg) {
        ModelMap model = new ModelMap();
        model.put(HttpConst.HTTP_RESPONSE_CODE_KEY, code);
        model.put(HttpConst.HTTP_RESPONSE_MSG_KEY, msg);
        return model;
    }

    /**
    * @Name createModel
    * @Description 创建一个通用mvc返回ModelMap对象
    * @Author wen
    * @Date 2019/7/12
    * @param ce
    * @return org.springframework.ui.ModelMap
    */
    public static ModelMap createModel(CommomError ce) {
        return createModel(ce.getErrCode(), ce.getErrMsg());
    }

    /**
    * @Name createMap
    * @Description 创建一个通用mvc返回Map对象
    * @Author wen
    * @Date 2019/7/12
    * @param code
    * @param msg
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    public static Map<String, Object> createMap(int code, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put(HttpConst.HTTP_RESPONSE_CODE_KEY, code);
        map.put(HttpConst.HTTP_RESPONSE_MSG_KEY, msg);
        return map;
    }

    /**
    * @Name createMap
    * @Description 创建一个通用mvc返回Map对象
    * @Author wen
    * @Date 2019/7/12
    * @param ce
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    public static Map<String, Object> createMap(CommomError ce) {
        return createMap(ce.getErrCode(), ce.getErrMsg());
    }

    /**
    * @Name cerateMAV
    * @Description 创建一个试图模型对象
    * @Author wen
    * @Date 2019/7/13
    * @return org.springframework.web.servlet.ModelAndView
    */
    public static ModelAndView cerateMAV(CommomError ce, String url){
        return new ModelAndView(url, createModel(ce));
    }

}
