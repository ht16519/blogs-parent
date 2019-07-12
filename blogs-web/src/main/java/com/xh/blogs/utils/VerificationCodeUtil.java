package com.xh.blogs.utils;

import com.google.code.kaptcha.Constants;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.LoginException;

/**
 * @Name KaptchaUtil
 * @Description
 * @Author wen
 * @Date 2019-05-29
 */
public class VerificationCodeUtil {

    /**
    * @Name check
    * @Description 校验验证码
    * @Author wen
    * @Date 2019/5/29
    * @param
    * @return void
    */
    public static void check(String inputSecurityCode) throws LoginException {
        //验证码校验
        Object securityCodeCache = ShiroUtil.sessionGetValue(Constants.KAPTCHA_SESSION_KEY);
        if(securityCodeCache == null){
            throw new LoginException(EmError.SYSTEM_SECURITY_CODE_ERROR);
        }
        String code = (String)securityCodeCache;
        //验证码不正确
        if(!inputSecurityCode.equals(code)){
            throw new LoginException(EmError.INPUT_SECURITY_CODE_ERROR);
        }
        //验证码缓存清除
        ShiroUtil.sessionRemoveValue(Constants.KAPTCHA_SESSION_KEY);
    }

}
