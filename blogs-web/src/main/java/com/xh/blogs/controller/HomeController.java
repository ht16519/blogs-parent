package com.xh.blogs.controller;

import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.service.IArticleService;
import com.xh.blogs.service.IUserService;
import com.xh.blogs.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Name HomeController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Controller
@Slf4j
public class HomeController extends BaseController{

    @Autowired
    private IUserService userService;
    @Autowired
    private IArticleService articleService;

    @GetMapping("/home/feeds/{number}")
    public String homeFeeds(@PathVariable("number") int number, ModelMap model) throws BusinessException {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByIdWithPage(ShiroUtil.getUser().getId(), number));
        return ViewUrl.HOME_FEEDS;
    }

    @GetMapping("/home/articles/{number}")
    public String homeArticles(@PathVariable("number") int number, ModelMap model) throws BusinessException {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByIdWithPage(ShiroUtil.getUser().getId(), number));
        return ViewUrl.HOME_ARTICLES;
    }



}
