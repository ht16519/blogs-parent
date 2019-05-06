package com.xh.blogs.runner;

import com.xh.blogs.api.IMenuService;
import com.xh.blogs.domain.entity.ERoleMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    private IMenuService menuService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("============ START初始化角色菜单 ===========");
        if(null == menuService.createRoleMenuCache()){
            log.info("============ END角色菜单初始化失败 ===========");
        } else {
            log.info("============ END角色菜单初始化成功 ===========");
        }
    }

}
