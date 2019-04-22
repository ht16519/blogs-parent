package com.xh.blogs.enums;


import com.xh.blogs.error.CommomError;

/**
 * @author deng.wenqin
 * @Name EmBusinessError
 * @Description 业务错误枚举
 * @Date 2018-03-21
 */
public enum EmError implements CommomError {

    //基本类型
    SUCCESS(0, "操作成功"),
    FAIL(-1, "操作失败"),

    //100开头定义请求异常类型
    UNKNOWN_ERROR(10001, "未知错误"),
    CONTENT_TYPE_NOT_SUPPORTED(10002, "不支持内容类型"),
    REQUEST_METHOD_NOT_SUPPORTED(10003, "不支持的请求类型"),
    PARAMETER_VERIFICATION_ERROR(10020, "参数不合法"),

    //200开头为天气信息相关错误
    GET_WEATHER_INFO_ERROR(20001, "天气数据获取异常"),
    USER_DATA_ERROR(20002, "用户数据错误"),
    USER_PASSWORD_ERROR(20004, "密码错误"),
    USER_SESSION_NOT_EXITS(20003, "用户未登录或登录已超时"),

    //300开头为文件相关错误
    FILE_WRITE_ERROR(30001, "文件写入失败"),
    FILE_IS_NOT_PICTURE(30003, "不支持的文件类型"),
    MAX_UPLOAD_SIZE_EXCEEDED(30002, "文件不能超过1MB"),

    //400开头为秒杀相关错误
    SECKILL_DATA_REWRITE(40001, "秒杀失败，令牌错误或已失效"),
    SECKILL_IS_OVER(40002, "秒杀已经结束"),
    SECKILL_REPEATED(40003, "重复秒杀"),
    ORDER_INFO_NOT_EXIST(40004, "商品信息不存在"),
    USER_IS_NOT_EXIST(40005, "用户不存在"),
    SECKILL_SUCCESSED(40000, "秒杀成功"),
    SECKILL_FAILED(40500, "秒杀失败，请稍后再试")

    ;

    private int code;
    private String msg;

    EmError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getErrCode() {
        return this.code;
    }

    @Override
    public String getErrMsg() {
        return this.msg;
    }

    @Override
    public CommomError setErrMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static EmError codeOf(int index){
        for(EmError state : values()){
            if(state.getErrCode() == index){
                return state;
            }
        }
        return null;
    }

}
