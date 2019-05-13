package com.xh.blogs.config;

import com.xh.blogs.content.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
public class WebMvcFileConfig extends WebMvcConfigurerAdapter{

    @Value("${fileUpload.rootSavePath}")
    private String rootSavePath;
    @Value("${fileUpload.maxFileSize}")
    private int maxFileSize;
    @Value("${blogs.accessory.path}")
    private String accessoryPath;
    @Value("${blogs.accessoryPrefix}")
    private String accessoryMappingPrefix;
    @Autowired
    private HttpInterceptor httpInterceptor;

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(maxFileSize);
        factory.setMaxRequestSize(maxFileSize * 5);
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(accessoryPath).addResourceLocations(accessoryMappingPrefix + rootSavePath);
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor);
        super.addInterceptors(registry);
    }
}
