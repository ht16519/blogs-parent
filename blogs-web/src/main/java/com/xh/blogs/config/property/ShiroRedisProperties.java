package com.xh.blogs.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * redis属性配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class ShiroRedisProperties {

    private int database = 0;
    private String host = "localhost";
    private int port = 6379;
    private String password;
    private Duration timeout;
    /**
     * 默认7天（记住我时间）
     */
    private int remenberMe = 60 * 60 * 24 * 7;

    /**
     *  用户登录过期时间
     * */
    private int expire = 60 * 30;
}
