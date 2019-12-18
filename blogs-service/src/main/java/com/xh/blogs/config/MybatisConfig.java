package com.xh.blogs.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Name MybatisConfig
 * @Description
 * @Author wen
 * @Date 2019-12-15
 */
@Configuration
@MapperScan(basePackages = {"com.xh.blogs.dao.mapper"})
public class MybatisConfig {
}
