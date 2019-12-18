package com.xh.blogs.runner;

import com.xh.blogs.domain.po.Article;
import com.xh.blogs.service.*;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private IArticleService articleService;
    @Autowired
    private ISolrArticleService solrArticleService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IAttachsService attachsService;
    @Autowired
    private IConfigService configService;
    @Autowired
    private IFriendLinkService friendLinkService;

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
        //1.查询所有有效的文章
        List<Article> articles = articleService.getAllByStatus();
        //2.生成文章的全文检索信息
        solrArticleService.createEsLibrary(JsonUtil.serialize(articles));
        //5.初始化底部顶链接
        attachsService.createAttachsCache();
        //6.创建友情链接
        friendLinkService.updateShowCache();
    }



}
