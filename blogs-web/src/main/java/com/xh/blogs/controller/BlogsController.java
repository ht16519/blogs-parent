package com.xh.blogs.controller;

import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.ArticleVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.service.IArticleService;
import com.xh.blogs.service.IGroupService;
import com.xh.blogs.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @Name BlogsController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Controller
@Slf4j
public class BlogsController extends BaseController{

    @Autowired
    private IUserService userService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IGroupService groupService;

    @GetMapping("/index")
    public String index() {
        //TODO index
        return ViewUrl.INDEX;
    }

    @GetMapping("/article/new")
    public String newArticle(ModelMap model) {
        model.put(CommonConst.ARTICLE_GROUP, groupService.getAll());
        return ViewUrl.ARTICLE_PUBLISH;
    }

    @PostMapping("/article/push")
    public String addArticle(ArticleVo article) {
        article.setAuthorId(super.getProfile().getId());
        int res = articleService.addArticle(article);
        return RequestUrl.REDIRECT_HOME;
    }

    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable("id") int id, ModelMap model) {
        try {
            model.put(CommonConst.COMMON_RETURN_RESULT_KEY, articleService.getById(id));
            //TODO 文章浏览量 +1
        } catch (BusinessException ex) {
            super.getModelMap(ex, model);
        }
        return ViewUrl.ARTICLE_VIEW;
    }




}
