package com.xh.blogs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * @Name UploadFileConfig
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${fileUpload.rootSavePath}")
    private String rootSavePath;
    @Value("${fileUpload.maxFileSize}")
    private int maxFileSize;
    @Value("${blogs.accessory.path}")
    private String accessoryPath;
    @Value("${blogs.accessoryPrefix}")
    private String accessoryMappingPrefix;

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
    }


//    @Bean
//    public ServletContextInitializer servletContextInitializer() {
//        return servletContext -> {
//            servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
//            servletContext.getSessionCookieConfig().setHttpOnly(true);
//        };
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HttpInterceptor());
//    }
}
