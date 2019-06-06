package com.xh.blogs.controller.manager;

import com.xh.blogs.api.IArticleService;
import com.xh.blogs.api.IEsArticleService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.domain.vo.WebApiResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Name ArticleController
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private IEsArticleService esArticleService;

    @GetMapping("/list")
    @RequiresPermissions("sys:article:view")
    public String listArticle(String title, Integer pn, ModelMap model){
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByConditionWithPage(title, pn));
        model.put(CommonConst.ARTICLE_TITLE_KEY, title);
        model.put(CommonConst.PAGE_NUMBER_KEY, pn);
        return ViewUrl.ADMIN_ARTICLE_LIST;
    }

    @PostMapping("/es/reset.json")
    @RequiresPermissions("sys:article:edit")
    public @ResponseBody WebApiResult resetEsIndex(){
        //生成文章的全文检索信息
        esArticleService.createEsLibrary();
        return WebApiResult.success();
    }


}
