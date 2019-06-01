package com.xh.blogs.runner;

import com.xh.blogs.api.IConfigService;
import com.xh.blogs.api.IEsArticleService;
import com.xh.blogs.api.IGroupService;
import com.xh.blogs.api.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Name ApplicationRunner
 * @Description
 * @Author wen
 * @Date 2019-05-05
 */
@Slf4j
@Order(1)
@Component
public class InitializationRunner implements ApplicationRunner {

    @Autowired
    private IEsArticleService esArticleService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IConfigService configService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //初始化系统常量配置
        configService.createSystemConfig();
        //1.初始化用户角色菜单
        menuService.createRoleMenuCache();
        //2.初始化header分类
        groupService.createShowCache();
        //3.初始化用户角色菜单关系树
        menuService.createRoleMenuTreeCache();
        //4.初始化文章的全文检索信息
        esArticleService.createEsLibrary();
    }



}
