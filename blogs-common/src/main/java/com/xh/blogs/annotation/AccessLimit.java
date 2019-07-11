package com.xh.blogs.annotation;

import java.lang.annotation.*;

/**
 * @Name AccessLimit
 * @Description 接口限流，设置指定时间内允许访问该注解下的方法次数，并判断是否需要登录
 * @Author wen
 * @Date 2019-07-11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /** 设置时间（单位：s，0为无限次）*/
    long seconds() default 0;

    /** 指定时间内最大访问次数（0为无限次）*/
    int maxCount() default 0;

    /** 是否需要登录 true: 是, false: 否*/
    boolean needLogin() default false;
}
