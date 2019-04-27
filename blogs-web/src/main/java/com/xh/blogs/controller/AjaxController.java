package com.xh.blogs.controller;

import com.xh.blogs.api.*;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.consts.StringConst;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.domain.entity.EHotArticle;
import com.xh.blogs.domain.entity.EHotUser;
import com.xh.blogs.domain.po.Comments;
import com.xh.blogs.domain.vo.CommentsVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.domain.vo.WebApiResult;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Name AjaxController
 * @Description ajax请求controller TODO 全局缓存优化
 * @Author wen
 * @Date 2019-04-25
 */
@RestController
@RequestMapping("/api")
public class AjaxController extends BaseController{

    @Autowired
    private IStatisticalService statisticalService;
    @Autowired
    private IFavorsService favorsService;
    @Autowired
    private IFollowsService followsService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private INotifyService notifyService;
    @Autowired
    private ICommentsService commentsService;

    @GetMapping("/comment/")
    public WebApiResult listComments(@PathVariable("articleId") Integer articleId){
        PageResult<Comments> page = commentsService.getByArticleIdWithPage(articleId);
        return WebApiResult.success();
    }

    /**
     * @Name commentSubmit
     * @Description 提交
     * @Author wen
     * @Date 2019/4/27
     * @param commentsVo
     * @return com.xh.blogs.domain.vo.WebApiResult
     */
    @PostMapping("/comment/submit.json")
    public WebApiResult commentSubmit(@RequestBody CommentsVo commentsVo) throws BusinessException {
        commentsVo.setUserId(super.getProfile().getId());
        commentsService.add(commentsVo);
        return WebApiResult.success();
    }

    /**
    * @Name UnreadMessageCount
    * @Description 获取用户的未读通知数
    * @Author wen
    * @Date 2019/4/25
    * @param
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    @GetMapping("/messages.json")
    public WebApiResult UnreadMessageCount(){
        return WebApiResult.success(notifyService.getUnreadCountByUserId(super.getProfile().getId()));
    }
    /**
    * @Name checkFollowIsExist
    * @Description 检查是否关注
    * @Author wen
    * @Date 2019/4/25
    * @param uid
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    @GetMapping("/check_follow.json/{uid}")
    public WebApiResult checkFollowIsExist(@PathVariable("uid") Integer uid){
        return WebApiResult.getResult(followsService.checkIsExistByUserId(uid, super.getProfile().getId()));
    }

    /**
    * @Name addFollow
    * @Description 添加关注并发通知
    * @Author wen
    * @Date 2019/4/25
    * @param uid
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    @GetMapping("/follow.json/{uid}")
    public WebApiResult addFollow(@PathVariable("uid") Integer uid){
        //1.判断是否是自己
        int ownId = super.getProfile().getId();
        if(uid.equals(ownId)){
            return WebApiResult.fail(StringConst.FOCUS_ON_YOURSELF_MSG);
        }
        //2.添加关注
        if(followsService.addFollow(uid, ownId) <= 0){
            return WebApiResult.fail(StringConst.FOLLOW_FOCUS_IS_EXIST);
        }
        //3.关注成功，发送通知消息
        notifyService.sendMsg(ownId, uid, NotifyConst.EVENT_FOLLOWS, null);
        return WebApiResult.success();
    }

    /**
    * @Name addFavor
    * @Description 添加收藏并发送通知
    * @Author wen
    * @Date 2019/4/27
    * @param articleId
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    @GetMapping("/favor.json/{uid}/{articleId}")
    @Transactional
    public WebApiResult addFavor(@PathVariable("uid") int uid, @PathVariable("articleId") Integer articleId){
        //1.添加收藏
        int ownId = super.getProfile().getId();
        if(favorsService.addFavor(ownId, articleId) < 0){
            return WebApiResult.fail(StringConst.FAVOR_FOCUS_IS_EXIST);
        }
        //2.文章收藏量增加
        articleService.updateFavors(articleId);
        //3.收藏成功，发送通知
        notifyService.sendMsg(ownId, uid, NotifyConst.EVENT_FAVORS, articleId);
        return WebApiResult.success();
    }

    /**
    * @Name latestArticles
    * @Description 获取最新文章
    * @Author wen
    * @Date 2019/4/25
    * @param
    * @return com.xh.blogs.domain.vo.WebApiResult<java.util.List<com.xh.blogs.domain.entity.EHotArticle>>
    */
    @GetMapping("/latests.json")
    public WebApiResult<List<EHotArticle>> latestArticles(){
        return WebApiResult.success(statisticalService.getLatestsArticles(ConfigConst.STATISTICAL_COUNT));
    }

    /**
    * @Name hottestArticles
    * @Description 获取最热文章
    * @Author wen
    * @Date 2019/4/25
    * @param
    * @return com.xh.blogs.domain.vo.WebApiResult<java.util.List<com.xh.blogs.domain.entity.EHotArticle>>
    */
    @GetMapping("/hottests.json")
    public WebApiResult<List<EHotArticle>> hottestArticles(){
        return WebApiResult.success(statisticalService.getHottestArticles(ConfigConst.STATISTICAL_COUNT));
    }

    /**
    * @Name hottestUsers
    * @Description 获取最热用户
    * @Author wen
    * @Date 2019/4/25
    * @param
    * @return com.xh.blogs.domain.vo.WebApiResult<com.xh.blogs.domain.entity.EHotUser>
    */
    @GetMapping("/hotusers.json")
    public WebApiResult<EHotUser> hottestUsers(){
        return WebApiResult.success(statisticalService.getHottestUsers(ConfigConst.STATISTICAL_COUNT));
    }




}
