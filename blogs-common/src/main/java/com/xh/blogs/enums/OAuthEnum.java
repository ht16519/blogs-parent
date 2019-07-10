package com.xh.blogs.enums;

/**
 * @Name OAuthEnum
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
public enum OAuthEnum {

    QQ(1), WECHAR(2), WEIBO(3);

    private int code;

    public int getCode() {
        return code;
    }

    OAuthEnum(int code) {
        this.code = code;
    }

}
