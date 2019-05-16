package com.xh.blogs.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import javax.annotation.PostConstruct;

/**
 * @Name ShiroTagsFreeMarkerConfig
 * @Description
 * @Author wen
 * @Date 2019-05-15
 */
@Component
public class ShiroTagsConfig {

    /**
     *
     */
    private static final String name = "shiro";

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        //设置freeMarker 的shiro 标签"
        freeMarkerConfigurer.getConfiguration().setSharedVariable(name, new ShiroTags());
    }
}
