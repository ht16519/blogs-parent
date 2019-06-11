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
    SYSTEM_SECURITY_CODE_ERROR(10021, "验证码已过期，请刷新再试"),
    INPUT_SECURITY_CODE_ERROR(10022, "验证码不正确"),

    //200开头为用户信息相关错误
    USER_DATA_ERROR(20002, "用户不存在"),
    USER_NAME_OR_PASSWORD_ERROR(20003, "用户名或密码错误"),
    USER_IS_DISABLE(20004, "用户被禁用"),
    USER_SESSION_NOT_EXITS(20005, "用户未登录或登录已超时"),
    USER_NAME_IS_EXIST(20006, "用户名已存在"),
    USER_NICK_NAME_IS_EXIST(20007, "昵称已存在"),
    USER_AUTHENTICATION_FAILED(20009, "认证失败，请联系管理员"),
    USER_NOT_LOGGED_IN(200010, "请登录后再进行此操作"),
    PASSWORD_MISMATCH(20011, "两次密码输入不一致"),
    USER_PASSWORD_ERROR(20012, "当前密码输入错误"),
    USER_IS_NOT_EXIST(20013, "该用户不存在"),
    USER_IS_UNAVAILABLE(20014, "该账户已被冻结，暂时无法操作"),
    USER_UNAUTHORIZED(20014, "权限不够，请联系管理员"),

    //300开头为文件相关错误
    FILE_WRITE_ERROR(30001, "文件上传失败"),
    FILE_IS_NOT_PICTURE(30002, "不支持的文件类型"),
    MAX_UPLOAD_SIZE_EXCEEDED(30003, "单个文件大小不能超过1MB"),
    FILE_IS_NOT_EXIST(30004, "图片文件不能为空"),

    //400开头为文章相关错误
    ARTICLE_IS_NOT_EXIST(40001, "文章不存在或已被删除"),
    COMMENT_IS_NOT_EXIST(40002, "只能删除自己的存在的评论"),
    CANT_HANDLE_OTHER(40003, "只能操作自己发布的文章"),
    ARTICLE_TAGS_NAME_IS_EXIST(40004, "该标签名称已存在"),
    ARTICLE_TAGS_SORT_IS_EXIST(40005, "该标签排序已存在"),
    ARTICLE_TAGS_SORT_IS_START(40006, "该标签排序已是第一位"),
    ARTICLE_TAGS_SORT_IS_END(40007, "该标签排序已是最后一位"),

    //500开头为邮件相关信息
    SEND_EMAIL_FAIL(50001, "邮件发送失败"),
    EMAIL_INCORRECT_FORMAT(50002, "邮箱格式不正确"),
    EMAIL_IS_ERROR(50003, "邮箱不正确，请填写收到激活码的邮箱地址"),
    CODE_IS_ERROR(50004, "验证码不正确或已失效"),
    USER_EMAIL_IS_EXIST(50005, "邮箱已被注册"),
    USER_EMAIL_IS_ACTIVE(50006, "您的邮箱已是激活状态"),
    EMAIL_OR_CODE_IS_ERROR(50007, "邮箱或验证码不正确或已失效"),

    //600开头为角色相关错误
    ROLE_INFO_IS_NOT_EXIST(60001, "角色不存在"),
    ROLE_INFO_IS_EXIST(60002, "角色名称已经存在，不可重复"),

    //700开头为网站配置错误
    SITE_ATTACHS_SORT_IS_START(70001, "该公告排序已是第一位"),
    SITE_ATTACHS_SORT_IS_END(70002, "该公告排序已是最后一位"),
    SITE_ATTACHS_IS_NOT_EXIST(70003, "未找到页面信息"),
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
