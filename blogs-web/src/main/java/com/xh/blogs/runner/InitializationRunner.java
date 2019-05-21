package com.xh.blogs.runner;

import com.xh.blogs.api.IGroupService;
import com.xh.blogs.api.IMenuService;
import com.xh.blogs.api.IUserService;
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
    private IGroupService groupService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IUserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.初始化用户角色菜单
        menuService.createRoleMenuCache();
        //2.初始化header分类
        groupService.createShowCache();
        //3.初始化系统账户
        userService.initSystemAccount();
        //4.初始化用户角色菜单关系树
        menuService.createRoleMenuTreeCache();
    }

}
