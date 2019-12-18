package com.xh.blogs.utils;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

/**
 * @Name MD5Util
 * @Description md5加密工具类
 * @Author wen
 * @Date 2019-04-05
 */
public class MD5Util {

    /**
     * 前端盐值
     */
    private static final String SALT = "1a2b3c4d";

    /**
    * @Name encrypt
    * @Description 基础md5加密方法
    * @Author wen
    * @Date 2019/4/11
    * @param source
    * @return java.lang.String
    */
    public static String encrypt(String source){
        return DigestUtils.md5Hex(source);
    }

    /**
    * @Name inputPass2FormPass
    * @Description 将输入密码第一次加密
    * @Author wen
    * @Date 2019/4/11
    * @param inputPass
    * @return java.lang.String
    */
    public static String inputPass2FormPass(String inputPass){
        StringBuffer sb = new StringBuffer();
        sb.append(SALT.charAt(0)).append(SALT.charAt(2)).append(inputPass).append(SALT.charAt(5)).append(SALT.charAt(4));
        return encrypt(sb.toString());
    }

    /**
    * @Name formPass2DBPass
    * @Description 将第一次加密后的密码进行第二次加密
    * @Author wen
    * @Date 2019/4/11
    * @param formPass
    * @param salt
    * @return java.lang.String
    */
    public static String formPass2DBPass(String formPass, String salt){
        StringBuffer sb = new StringBuffer();
        sb.append(salt.charAt(0)).append(salt.charAt(2)).append(formPass).append(salt.charAt(5)).append(salt.charAt(4));
        return encrypt(sb.toString());
    }

    /**
    * @Name inputPass2DBPass
    * @Description 将用户输入的密码直接加密为数据库保存的密码
    * @Author wen
    * @Date 2019/4/11
    * @param inputPass
    * @param salt
    * @return java.lang.String
    */
    public static String inputPass2DBPass(String inputPass, String salt){
        return formPass2DBPass(inputPass2FormPass(inputPass), salt);
    }

    public static void main(String[] args) {
        String salt = CommonUtil.getSalt();
        System.out.println(salt);
        System.out.println(inputPass2DBPass("111", salt));
    }
}
