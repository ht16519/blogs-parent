package com.xh.blogs.config;

import com.qq.connect.oauth.Oauth;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    //QQ授权Oauth添加到IOC
    @Bean
    public Oauth oauth(){
        return new Oauth();
    }

    //ValueOperations添加到IOC
    @Bean
    public ValueOperations<String, String> valueOperations(StringRedisTemplate stringRedisTemplate){
        return stringRedisTemplate.opsForValue();
    }

    //使用自定义序列化MQ（Jackson）
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
