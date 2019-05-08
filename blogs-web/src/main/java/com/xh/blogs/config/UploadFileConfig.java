package com.xh.blogs.config;

import com.xh.blogs.consts.ConfigConst;
import org.springframework.beans.factory.annotation.Value;
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

    private final String ACCESSORY_MAPPING_PREFIX = "file:/";

    @Value("${fileUpload.rootSavePath}")
    private String rootSavePath;

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(ConfigConst.MAX_FILE_SIZE);
        factory.setMaxRequestSize(ConfigConst.MAX_FILE_SIZE * 5);
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ConfigConst.CONFIG_ACCESSORY_PATH).addResourceLocations(ACCESSORY_MAPPING_PREFIX + rootSavePath);
        super.addResourceHandlers(registry);
    }
}
