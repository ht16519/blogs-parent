package com.xh.blogs.utils;

import com.xh.blogs.consts.CommonConst;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Name CommonUtil
 * @Description
 * @Author wen
 * @Date 2019-04-23
 */
public class CommonUtil {

    public static int null2Int(String s) {
        int v = -1;
        try {
            v = Integer.parseInt(s);
        } catch (Exception e) {
        }
        return v;
    }


    public static int null2Int(Float f) {
        return null2Int(f, -1);
    }


    public static int null2Int(Float f, int r) {
        try {
            return f.intValue();
        } catch (Exception e) {
            return r;
        }
    }

    /**
     * @param length
     * @return java.lang.String
     * @Name getRS
     * @Description 获取随机字符串
     * @Author wen
     * @Date 2019/5/12
     */
    public static String getRS(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String getRAS(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String getSalt() {
        return getRAS(7);
    }

    public static String getRAS32() {
        return getRAS(32);
    }

    /**
     * @param
     * @return int
     * @Name getRandom
     * @Description 获取6位随机数
     * @Author wen
     * @Date 2019/5/13
     */
    public static int getRandom() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    /**
    * @Name handleSpecial
    * @Description 处理字符串中的特殊字符替换为""
    * @Author wen
    * @Date 2019/5/25
    * @param source
    * @return java.lang.String
    */
    public static String handleSpecial(String source){
        if(StringUtils.isEmpty(source)){
            return CommonConst.EMPTY_STRING;
        }
        String regEx = "[`~!@#$%^&*()\\-+={}':;,\\[\\].<>/?￥%…（）_+|【】‘；：”“’。，、？\\s]";
        return Pattern.compile(regEx).matcher(source).replaceAll(CommonConst.EMPTY_STRING);
    }

    /**
    * @Name isEmail
    * @Description 验证是否是邮箱格式字符串
    * @Author wen
    * @Date 2019/5/25
    * @param email
    * @return boolean
    */
    public static boolean isEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return Pattern.compile(regEx1).matcher(email).matches();
    }

    /**
    * @Name isInteger
    * @Description 判断字符串是否纯数字
    * @Author wen
    * @Date 2019/5/13
    * @param str
    * @return boolean
    */
    public static boolean isInteger(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[-\\+]?[\\d]*$").matcher(str).matches();
    }

}
