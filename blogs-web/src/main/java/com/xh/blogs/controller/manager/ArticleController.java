package com.xh.blogs.controller.manager;

import com.xh.blogs.domain.po.Article;
import com.xh.blogs.service.IArticleService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.domain.vo.WebApiResult;
import com.xh.blogs.service.ISolrArticleService;
import com.xh.blogs.utils.JsonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    private ISolrArticleService solrArticleService;

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
       //1.查询所有有效的文章
        List<Article> articles = articleService.getAllByStatus();
        //2.生成文章的全文检索信息
        solrArticleService.createEsLibrary(JsonUtil.serialize(articles));
        return WebApiResult.success();
    }


}
