package com.xh.blogs.config;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${oauth2.qq.appId}")
    private String appId;
    @Value("${oauth2.qq.appKey}")
    private String appKey;
    @Value("${oauth2.qq.callback}")
    private String appCallback;


    /**QQ授权Oauth添加到IOC容器**/
//    @Bean
////    public Oauth oauth(){
////        return new Oauth();
////    }

    /**
     * JustAuth联合授权Oauth添加到IOC容器
     **/
    @Bean
    public AuthRequest authQqRequest() {
        return new AuthQqRequest(AuthConfig.builder()
                .clientId(appId)
                .clientSecret(appKey)
                .redirectUri(appCallback)
                .build());
    }



    /**
     * ValueOperations添加到IOC容器
     **/
    @Bean
    public ValueOperations<String, String> valueOperations(StringRedisTemplate stringRedisTemplate) {
        return stringRedisTemplate.opsForValue();
    }

    /**
     * 使用自定义序列化MQ（Jackson）
     **/
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
