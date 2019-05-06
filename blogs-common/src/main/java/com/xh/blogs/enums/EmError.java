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

    //200开头为用户信息相关错误
    USER_DATA_ERROR(20002, "用户不存在"),
    USER_NAME_OR_PASSWORD_ERROR(20003, "用户名或密码错误"),
    USER_IS_DISABLE(20004, "用户被禁用"),
    USER_SESSION_NOT_EXITS(20005, "用户未登录或登录已超时"),
    USER_NAME_IS_EXIST(20006, "用户名已存在"),
    USER_NICK_NAME_IS_EXIST(20007, "昵称已存在"),
    USER_EMAIL_IS_EXIST(20008, "邮箱已被注册"),
    USER_AUTHENTICATION_FAILED(20009, "认证失败，请联系管理员"),
    USER_NOT_LOGGED_IN(200010, "请登录后再进行此操作"),
    PASSWORD_MISMATCH(20011, "两次密码输入不一致"),
    USER_PASSWORD_ERROR(20012, "当前密码输入错误"),

    //300开头为文件相关错误
    FILE_WRITE_ERROR(30001, "文件写入失败"),
    FILE_IS_NOT_PICTURE(30003, "不支持的文件类型"),
    MAX_UPLOAD_SIZE_EXCEEDED(30002, "文件不能超过1MB"),

    //400开头为文章相关错误
    ARTICLE_IS_NOT_EXIST(40001, "文章不存在或已被删除"),
    COMMENT_IS_NOT_EXIST(40002, "只能删除自己的存在的评论"),
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
