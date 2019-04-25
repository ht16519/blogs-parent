package com.xh.blogs.controller;

import com.xh.blogs.api.INotifyService;
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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Name HomeController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{

    @Autowired
    private IUserService userService;
    @Autowired
    private INotifyService notifyService;
    @Autowired
    private IArticleService articleService;

    /**
    * @Name homeNotifies
    * @Description 我的通知分页
    * @Author wen
    * @Date 2019/4/25
    * @param number
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/notifies/{number}")
    public String homeNotifies(@PathVariable("number") int number, ModelMap model) {
        //1.分页查询消息
        int userId = super.getProfile().getId();
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, notifyService.getByUserIdWithPage(userId, number));
        //2.设置消息为已读
        notifyService.setStatusByUserId(userId);
        return ViewUrl.HOME_NOTIFIES;
    }

    /**
    * @Name homeFeeds
    * @Description 我的动态分页
    * @Author wen
    * @Date 2019/4/25
    * @param number
    * @return java.lang.String
    */
    @GetMapping("/feeds/{number}")
    public String homeFeeds(@PathVariable("number") int number, ModelMap model) {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByIdWithPage(super.getProfile().getId(), number));
        return ViewUrl.HOME_FEEDS;
    }

    /**
    * @Name homeArticles
    * @Description 我的文章分页
    * @Author wen
    * @Date 2019/4/25
    * @param number
    * @return java.lang.String
    */
    @GetMapping("/articles/{number}")
    public String homeArticles(@PathVariable("number") int number, ModelMap model) {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByIdWithPage(super.getProfile().getId(), number));
        return ViewUrl.HOME_ARTICLES;
    }



}
