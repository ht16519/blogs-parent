package com.xh.blogs.controller.home;

import com.xh.blogs.service.*;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.exception.BusinessException;
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
public class HomeController extends BaseController {

    @Autowired
    private INotifyService notifyService;
    @Autowired
    private IFollowsService followsService;
    @Autowired
    private IFavorsService favorsService;
    @Autowired
    private ICommentsService commentsService;
    @Autowired
    private IArticleService articleService;

    @GetMapping("/comments/{number}")
    public String comments(@PathVariable("number") int number, ModelMap model) throws BusinessException {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, commentsService.getByUserIdWithPage(super.getProfile().getId(), number));
        return ViewUrl.HOME_COMMENTS;
    }

    /**
    * @Name favors
    * @Description 用户收藏的文章
    * @Author wen
    * @Date 2019/4/28
    * @param number
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/favors/{number}")
    public String favors(@PathVariable("number") int number, ModelMap model) throws BusinessException {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, favorsService.getByUserIdWithPage(super.getProfile().getId(), number));
        return ViewUrl.HOME_FAVORS;
    }

    /**
    * @Name follows
    * @Description 用户关注
    * @Author wen
    * @Date 2019/4/28
    * @param number
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/follows/{number}")
    public String follows(@PathVariable("number") int number, ModelMap model) throws BusinessException {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, followsService.getFollowsByUserIdWithPage(super.getProfile().getId(), number));
        return ViewUrl.HOME_FOLLOWS;
    }

    /**
    * @Name fans
    * @Description 用户粉丝
    * @Author wen
    * @Date 2019/4/28
    * @param number
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/fans/{number}")
    public String fans(@PathVariable("number") int number, ModelMap model) throws BusinessException {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, followsService.getFansByUserIdWithPage(super.getProfile().getId(), number));
        return ViewUrl.HOME_FANS;
    }

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
        try {
            //1.分页查询消息
            int userId = super.getProfile().getId();
            model.put(CommonConst.RESULT_PAGE_INFO_KEY, notifyService.getByUserIdWithPage(userId, number));
            //2.设置消息为已读
            notifyService.setStatusByUserId(userId);
        } catch (BusinessException e) {
            super.getModelMap(e, model);
        }
        return ViewUrl.HOME_NOTIFIES;
    }

    @GetMapping("/feeds/{number}")
    public String homeFeeds(@PathVariable("number") int number, ModelMap model) {
        try {
            model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByIdWithPage(super.getProfile().getId(), number));
        } catch (BusinessException e) {
            super.getModelMap(e, model);
        }
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
        try {
            model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByIdWithPage(super.getProfile().getId(), number));
        } catch (BusinessException e) {
            super.getModelMap(e, model);
        }
        return ViewUrl.HOME_ARTICLES;
    }



}
