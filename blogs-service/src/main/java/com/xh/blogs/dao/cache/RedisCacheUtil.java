package com.xh.blogs.dao.cache;

import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Name RedisCacheUtil
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
@Slf4j
@Component
public class RedisCacheUtil {

    private static final long DEFAULT_REDIS_KEY_DURATION = 30 * 60;

    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String get(String k){
        return valueOperations.get(k);
    }

    public <T> T get(String k, Class<T> clazz){
        return JsonUtil.parse(valueOperations.get(k), clazz);
    }

    public void set(String k, Object v, long l, TimeUnit timeUnit){
        valueOperations.set(k, JsonUtil.serialize(v), l, timeUnit);
    }

    public void set(String k, Object v, long l){
        this.set(k, v, l, TimeUnit.SECONDS);
    }

    public void set(String k, Object v){
        this.set(k, v, DEFAULT_REDIS_KEY_DURATION, TimeUnit.SECONDS);
    }

    public void setNoTime(String k, Object v){
        valueOperations.set(k, JsonUtil.serialize(v));
    }

    public void delete(String k){
        stringRedisTemplate.delete(k);
    }

    public long increment(String k, long v){
        return valueOperations.increment(k, v);
    }

    public long increment(String k){
        return this.increment(k, 1);
    }

    public void expire(String k, long s, TimeUnit timeUnit) {
        stringRedisTemplate.expire(k, s, timeUnit);
    }
}
