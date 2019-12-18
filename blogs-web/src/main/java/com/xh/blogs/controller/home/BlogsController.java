package com.xh.blogs.controller.home;

import com.xh.blogs.api.IArticleService;
import com.xh.blogs.api.IAsyncEsAricleHandleService;
import com.xh.blogs.api.IAttachsService;
import com.xh.blogs.api.IEsArticleService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.entity.EArticleHandleMessage;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.ArticleVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.domain.vo.WebApiResult;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.CommonUtil;
import com.xh.blogs.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    private IAttachsService attachsService;
    @Autowired
    private IEsArticleService esArticleService;
    @Autowired
    private IAsyncEsAricleHandleService asyncEsAricleService;

    /**
    * @Name affiche
    * @Description 公告页面
    * @Author wen
    * @Date 2019/6/6
    * @param id
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/affiche/{id}")
    public String affiche(@PathVariable("id") int id, ModelMap model) throws BusinessException {
        model.put(KeyConst.SITE_ATTACHS_DETAILS_KEY, attachsService.getById(id));
        return ViewUrl.BLOG_AFFICHES;
    }

    /**
     * @Name removeArticle
     * @Description 移除文章
     * @Author wen
     * @Date 2019/5/7
     * @param id
     * @return java.lang.String
     */
    @GetMapping("/home/article/delete/{id}")
    @ResponseBody
    public WebApiResult removeArticle(@PathVariable("id") int id) throws BusinessException {
        //1.移除文章
        int res = articleService.removeById(id, super.getProfile().getId());
        if(res > 0){
            //2.发送删除es中的文章异步请求
            asyncEsAricleService.sendHandeESArticleMsg(EArticleHandleMessage.delete(id));
        }
        return WebApiResult.getResult(res);
    }

    /**
    * @Name doEditArticle
    * @Description 修改文章操作
    * @Author wen
    * @Date 2019/5/7
    * @param articleVo
    * @param model
    * @return java.lang.String
    */
    @PostMapping("/home/article/edit")
    public String doEditArticle(ArticleVo articleVo, ModelMap model){
        try {
            //1.修改数据库文章信息
            articleVo.setAuthorId(super.getProfile().getId());
            Article article = articleService.updateArticleById(articleVo);
            //2.发送修改es文章信息的异步请求
            asyncEsAricleService.sendHandeESArticleMsg(EArticleHandleMessage.save(article));
        } catch (BusinessException e) {
            super.getModelMap(e, model);
            model.put(CommonConst.DATA_RESULT_KEY, articleVo);
            return ViewUrl.ROUTE_POST_UPDATE;
        }
        return RequestUrl.REDIRECT_HOME;
    }

    /**
    * @Name articleEdit
    * @Description 文章编辑
    * @Author wen
    * @Date 2019/5/7
    * @param id
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/home/article/edit/{id}")
    public String editArticleView(@PathVariable("id") int id, ModelMap model){
        try {
            //获取文章信息
            Article article = articleService.getByUserId(id, super.getProfile().getId());
            model.put(CommonConst.DATA_RESULT_KEY, article);
        } catch (BusinessException e) {
            super.getModelMap(e, model);
            return ViewUrl.DEFAULT_ERROR;
        }
        return ViewUrl.ROUTE_POST_UPDATE;
    }

    /**
    * @Name index
    * @Description 首页文章
    * @Author wen
    * @Date 2019/4/26
    * @param
    * @return java.lang.String
    */
    @GetMapping({"", "/index"})
    public String index(ModelMap model) {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getInfoWithPage(CommonConst.ARTICLE_ORDER_NEWSET, CommonConst.PAGE_NUMBER));
        model.put("ord", CommonConst.ARTICLE_ORDER_NEWSET);
        return ViewUrl.INDEX;
    }

    /**
    * @Name article
    * @Description 通过标签获取文章
    * @Author wen
    * @Date 2019/5/29
    * @param name
    * @param number
    * @param model
    * @return java.lang.String 
    */
    @GetMapping("/article/{name}/{number}")
    public String article(@PathVariable("name") String name, @PathVariable("number") int number, ModelMap model) {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getInfoByTagWithPage(name, number));
        model.put(KeyConst.ARTICLE_TAG_PARAMETER_KEY, name);
        return ViewUrl.ARTICLE;
    }

    @GetMapping("/article/g/{groupId}/{number}")
    public String articleByGroup(@PathVariable("groupId") int groupId, @PathVariable("number") int number, ModelMap model) {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getInfoByGroupWithPage(groupId, number));
        model.put(KeyConst.ARTICLE_GROUP_PARAMETER_KEY, groupId);
        return ViewUrl.ARTICLE_GROUP;
    }

    /**
    * @Name articleSearch
    * @Description 文章搜索
    * @Author wen
    * @Date 2019/5/25
    * @param q
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/article/search")
    public String articleSearch(String q, int pn, ModelMap model) {
        String keyword = CommonUtil.handleSpecial(q);
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, StringUtils.isEmpty(keyword) ? PageResult.createNull() : esArticleService.search(keyword, pn));
        model.put(KeyConst.ARTICLE_SEARCH_PARAMETER_KEY, q);
        return ViewUrl.ARTICLE_SEARCH;
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
    * @return java.lang.String
    */
    @GetMapping("/home/article/new")
    public String newArticle() {
        return ViewUrl.ARTICLE_PUBLISH;
    }

    /**
    * @Name addArticle
    * @Description 发文章
    * @Author wen
    * @Date 2019/4/26
    * @param articleVo
    * @return java.lang.String
    */
    @PostMapping("/home/article/push")
    public String addArticle(ArticleVo articleVo, ModelMap model) {
        try {
            //1.新增数据库文章
            articleVo.setAuthorId(super.getProfile().getId());
            Article article = articleService.addArticle(articleVo);
            //2.发送保存es文章信息的异步请求
            asyncEsAricleService.sendHandeESArticleMsg(EArticleHandleMessage.save(article));
        } catch (BusinessException e) {
            super.getModelMap(e, model);
            return ViewUrl.ARTICLE_PUBLISH;
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
    @GetMapping("/article/details/{id}")
    public String viewArticle(@PathVariable("id") int id, HttpServletRequest request, ModelMap model) {
        articleService.addViews(id, RequestUtil.getIpAddress(request));
        Article article = articleService.getById(id);
        if(article == null){
            super.getModelMap(EmError.ARTICLE_IS_NOT_EXIST, model);
            return RequestUrl.INDEX_URL;
        }
        model.put(CommonConst.COMMON_RETURN_RESULT_KEY, article);
        return ViewUrl.ARTICLE_VIEW;
    }




}
