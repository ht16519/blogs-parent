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
        } catch (NumberFormatException e) {
        }
        return v;
    }

    public static void main(String[] args) {
        String s = "2---https://img-blog.csdnimg.cn/20190302220242474.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTMyOTQwOTc=,size_16,color_FFFFFF,t_70---https://img-blog.csdnimg.cn/20190302220242474.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTMyOTQwOTc=,size_16,color_FFFFFF,t_70---1===3---https://img-blog.csdnimg.cn/20190302220425956.png---https://img-blog.csdnimg.cn/20190302220425956.png---1===4---https://img-blog.csdnimg.cn/20190302220408346.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTMyOTQwOTc=,size_16,color_FFFFFF,t_70---https://img-blog.csdnimg.cn/20190302220408346.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTMyOTQwOTc=,size_16,color_FFFFFF,t_70---1";
        String[] split = s.split("===");
        for (String s1 : split) {
            System.err.println(s1);
            String[] split1 = s1.split("---");
            for (String s2 : split1) {
                System.out.println(s2);
            }
        }
    }

}
