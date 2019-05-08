package com.xh.blogs.utils;

/**
 * @Name CommonUtil
 * @Description
 * @Author wen
 * @Date 2019-04-23
 */
public class CommonUtil {

    public static int null2Int(String s){
        int v = -1;
        try {
            v = Integer.parseInt(s);
        } catch (Exception e) {
        }
        return v;
    }


    public static int null2Int(Float f){
        return null2Int(f, -1);
    }


    public static int null2Int(Float f, int r){
        try {
            return f.intValue();
        } catch (Exception e) {
           return r;
        }
    }

}
