package com.xh.blogs.controller.manager;

import com.xh.blogs.api.IArticleService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ViewUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/list")
    public String listArticle(String title, Integer pn, ModelMap model){
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByConditionWithPage(title, pn));
        model.put(CommonConst.ARTICLE_TITLE_KEY, title);
        model.put(CommonConst.PAGE_NUMBER_KEY, pn);
        return ViewUrl.ADMIN_ARTICLE_LIST;
    }

}
