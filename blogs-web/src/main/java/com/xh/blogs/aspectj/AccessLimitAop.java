package com.xh.blogs.aspectj;

import com.xh.blogs.annotation.AccessLimit;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.cache.RedisCacheUtil;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.RequestUtil;
import com.xh.blogs.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Name AccessLimitAop
 * @Description
 * @Author wen
 * @Date 2019-07-11
 */
@Aspect
@Component
@Slf4j
public class AccessLimitAop {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 切入@AccessLimit注解的方法
     */
    @Pointcut("@annotation(com.xh.blogs.annotation.AccessLimit)")
    public void accessLimitPointCut() {
    }

    /**
    * @Name requestLimit
    * @Description 配置前置通知
    * @Author wen
    * @Date 2019/7/11
    * @param limit
    * @return void
    */
    @Before("accessLimitPointCut() && @annotation(limit)")
    public void requestLimit(AccessLimit limit) throws BusinessException {
        //1.组装参数
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = RequestUtil.getIpAddress(request);
        String url = request.getRequestURL().toString();
        String key = CommonConst.REQUEST_LIMIT_PREFIX.concat((url.concat(CommonConst.HORIZONTAL_LINE).concat(ip)).replace(CommonConst.THE_COLON, CommonConst.UNDERLINE));
        //2.使用redis实现接口限流
        this.checkWithRedis(limit, key, ip, url);
    }

    /**
    * @Name checkWithRedis
    * @Description 使用redis实现接口限流
    * @Author wen
    * @Date 2019/7/11
    * @param limit
    * @param key
    * @param ip
    * @param url
    * @return void
    */
    private void checkWithRedis(AccessLimit limit, String key, String ip, String url) throws BusinessException {
        //1.获取限流参数
        long seconds = limit.seconds();
        int maxCount = limit.maxCount();
        //2.判断访问接口是否需要登录
        boolean needLogin = limit.needLogin();
        if(needLogin && (ShiroUtil.getUser() == null)){
            throw new BusinessException(EmError.USER_NOT_LOGGED_IN);
        }
        //3.判断接口是否限流
        if (seconds > 0 && maxCount > 0) {
            //3-1.访问接口，次数 +1
            long count = redisCacheUtil.increment(key);
            //3-2.如果次数为1，设置失效时间
            if (count == 1) {
                redisCacheUtil.expire(key, seconds, TimeUnit.SECONDS);
            }
            //3-3.指定时间内，访问次数超出，抛出异常
            if (count > maxCount) {
                log.info("Lequest Limited: [用户ip:{}],[规定的时间:{}s],[访问地址:{}超过了限定的次数:{}次]", ip, seconds, url, maxCount);
                throw new BusinessException(EmError.REQUEST_LIMITED);
            }
        }
    }

}
