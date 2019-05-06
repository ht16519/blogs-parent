package com.xh.blogs.controller.home;

import com.xh.blogs.api.IArticleService;
import com.xh.blogs.api.IGroupService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.ArticleVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Name BlogsController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Controller
@Slf4j
public class BlogsController extends BaseController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private IGroupService groupService;

    /**
    * @Name index
    * @Description 首页文章
    * @Author wen
    * @Date 2019/4/26
    * @param
    * @return java.lang.String
    */
    @GetMapping("/index")
    public String index(ModelMap model) {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getInfoWithPage(CommonConst.ARTICLE_ORDER_NEWSET, CommonConst.PAGE_NUMBER));
        model.put("ord", CommonConst.ARTICLE_ORDER_NEWSET);
        return ViewUrl.INDEX;
    }

    /**
    * @Name index
    * @Description 首页文章排序
    * @Author wen
    * @Date 2019/4/27
    * @param sort
    * @param number
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/index/{sort}/{number}")
    public String index(@PathVariable("sort") int sort, @PathVariable("number") int number, ModelMap model) {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getInfoWithPage(sort, number));
        model.put("ord", sort);
        return ViewUrl.INDEX;
    }

    /**
    * @Name newArticle
    * @Description 写文章
    * @Author wen
    * @Date 2019/4/26
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/home/article/new")
    public String newArticle(ModelMap model) {
        model.put(CommonConst.ARTICLE_GROUP, groupService.getByShow());
        return ViewUrl.ARTICLE_PUBLISH;
    }

    /**
    * @Name addArticle
    * @Description 发文章
    * @Author wen
    * @Date 2019/4/26
    * @param article
    * @return java.lang.String
    */
    @PostMapping("/home/article/push")
    public String addArticle(ArticleVo article, ModelMap model) {
        try {
            article.setAuthorId(super.getProfile().getId());
            articleService.addArticle(article);
        } catch (BusinessException e) {
            super.getModel(e, model);
        }
        return RequestUrl.REDIRECT_HOME;
    }

    /**
    * @Name viewArticle
    * @Description 查看文章详情
    * @Author wen
    * @Date 2019/4/26
    * @param id
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable("id") int id, ModelMap model) {
        Article article = articleService.getById(id);
        if(article == null){
            super.getModelMap(EmError.ARTICLE_IS_NOT_EXIST, model);
            return RequestUrl.INDEX_URL;
        }
        model.put(CommonConst.COMMON_RETURN_RESULT_KEY, article);
        //TODO 文章浏览量 +1
        return ViewUrl.ARTICLE_VIEW;
    }




}
