package com.xh.blogs.config;

import com.qq.connect.oauth.Oauth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @Name BeanConfig
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
@Configuration
public class BeanConfig {

    @Bean
    public Oauth oauth(){
        return new Oauth();
    }

    @Bean
    public ValueOperations<String, String> valueOperations(StringRedisTemplate stringRedisTemplate){
        return stringRedisTemplate.opsForValue();
    }

}
