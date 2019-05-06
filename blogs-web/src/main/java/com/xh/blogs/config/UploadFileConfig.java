package com.xh.blogs.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * @Name UploadFileConfig
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
@Configuration
public class UploadFileConfig extends WebMvcConfigurerAdapter{

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024 * 1024);
        factory.setMaxRequestSize(1024 * 1024 * 5);
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:/C:/images/");
        super.addResourceHandlers(registry);
    }
}
