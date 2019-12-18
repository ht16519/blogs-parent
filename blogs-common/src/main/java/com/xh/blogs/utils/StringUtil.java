package com.xh.blogs.utils;

import com.xh.blogs.consts.NumberConst;

import java.util.UUID;

/**
 * @Name StringUtil
 * @Description
 * @Author wen
 * @Date 2019-07-19
 */
public class StringUtil {

    /**
    * @Name getUUID16
    * @Description 获取16位不重复的字符串
    * @Author wen
    * @Date 2019/7/19
    * @param
    * @return java.lang.String
    */
    public static String getUUID16() {
        int machineId = NumberConst.INT_1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < NumberConst.INT_0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }
}
