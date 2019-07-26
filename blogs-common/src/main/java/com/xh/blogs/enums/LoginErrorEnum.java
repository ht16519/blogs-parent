package com.xh.blogs.enums;

import com.xh.blogs.error.CommomError;

/**
 * @Name LoginErrorEnum
 * @Description
 * @Author wen
 * @Date 2019-07-12
 */
public class LoginErrorEnum implements CommomError{

    @Override
    public int getErrCode() {
        return 0;
    }

    @Override
    public String getErrMsg() {
        return null;
    }

    @Override
    public CommomError setErrMsg(String errMsg) {
        return null;
    }
}
