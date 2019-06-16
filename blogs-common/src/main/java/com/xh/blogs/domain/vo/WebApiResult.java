package com.xh.blogs.domain.vo;

import com.xh.blogs.enums.EmError;
import lombok.Data;

/**
 * @author deng.wenqin
 * @Name AJAXResult
 * @Description ajax请求返回结果对象
 * @Date 2018-03-21
 */
@Data
public class WebApiResult<T> {

    /**
     * AJAXResult返回码状态为成功 0
     */
    private static transient final int SUCCESS = 0;

    /**
     * AJAXResult返回码状态为失败 -1
     */
    private static transient final int FAIL = -1;

    /**
     * 状态，对应请求的返回结果（success | fail）
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据，若status = success，返回给前端json数据
     * 否则，返回错误信息
     */
    private T data;

    public static <T> WebApiResult success(T data) {
        return success(data, EmError.SUCCESS.getErrMsg());
    }

    public static WebApiResult success() {
        return new WebApiResult(SUCCESS, EmError.SUCCESS.getErrMsg());
    }

    public static WebApiResult success(String msg) {
        return new WebApiResult(SUCCESS, msg);
    }

    public static <T> WebApiResult success(T data, String msg) {
        return new WebApiResult(SUCCESS, msg, data);
    }

    public static WebApiResult getResult(int res) {
        return res > 0 ? success() : fail();
    }

    public static WebApiResult getResult(int res, String msg) {
        return res > 0 ? success(msg) : fail();
    }

    public static WebApiResult fail() {
        return fail(EmError.FAIL.getErrMsg());
    }

    public static WebApiResult fail(int code, String msg) {
        return new WebApiResult(code, msg);
    }

    public static WebApiResult fail(String msg) {
        return fail(FAIL, msg);
    }

    public WebApiResult() {
        super();
    }

    public WebApiResult(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public WebApiResult(int code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

}
