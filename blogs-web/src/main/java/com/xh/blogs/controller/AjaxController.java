package com.xh.blogs.controller;

import com.xh.blogs.api.*;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.consts.StringConst;
import com.xh.blogs.domain.entity.EHotArticle;
import com.xh.blogs.domain.entity.EHotUser;
import com.xh.blogs.domain.vo.CommentsVo;
import com.xh.blogs.domain.vo.WebApiResult;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private INotifyService notifyService;
    @Autowired
    private ICommentsService commentsService;

    /**
     * @Name unfavor
     * @Description 删除评论
     * @Author wen
     * @Date 2019/4/28
     * @param id
     * @return com.xh.blogs.domain.vo.WebApiResult
     */
    @GetMapping("/comment/delete.json/{id}")
    public WebApiResult deleteComment(@PathVariable("id") int id) throws BusinessException {
        return WebApiResult.getResult(commentsService.removeById(id, super.getProfile().getId()));
    }

    /**
    * @Name unfavor
    * @Description 取消收藏
    * @Author wen
    * @Date 2019/4/28
    * @param id
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    @GetMapping("/unfavor.json/{id}")
    public WebApiResult unfavor(@PathVariable("id") int id) throws BusinessException {
        return WebApiResult.getResult(favorsService.unfavor(id, super.getProfile().getId()));
    }

    /**
    * @Name unfollow
    * @Description 取消关注
    * @Author wen
    * @Date 2019/4/28
    * @param id
    * @return com.xh.blogs.domain.vo.WebApiResult 
    */
    @GetMapping("/unfollow.json/{id}")
    public WebApiResult unfollow(@PathVariable("id") int id) throws BusinessException {
        return WebApiResult.getResult(followsService.unfollow(id, super.getProfile().getId()));
    } 

    /**
    * @Name listComments
    * @Description 评论列表
    * @Author wen
    * @Date 2019/4/27
    * @param articleId
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    @GetMapping("/free/comment/list.json/{articleId}/{number}")
    public WebApiResult listComments(@PathVariable("articleId") int articleId, @PathVariable("number") int number){
        return WebApiResult.success(commentsService.getByArticleIdWithPage(articleId, number));
    }

    /**
     * @Name commentSubmit
     * @Description 提交评论
     * @Author wen
     * @Date 2019/4/27
     * @param commentsVo
     * @return com.xh.blogs.domain.vo.WebApiResult
     */
    @PostMapping("/free/comment/submit.json")
    public WebApiResult commentSubmit(CommentsVo commentsVo) throws BusinessException {
        commentsVo.setUserId(super.getProfile().getId());
        return WebApiResult.getResult(commentsService.add(commentsVo));
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
    public WebApiResult UnreadMessageCount() throws BusinessException {
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
    @GetMapping("/free/follow/check.json/{uid}")
    public WebApiResult checkFollowIsExist(@PathVariable("uid") int uid) throws BusinessException {
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
    @GetMapping("/free/follow.json/{uid}")
    public WebApiResult addFollow(@PathVariable("uid") int uid) throws BusinessException {
        //1.判断是否是自己
        int ownId = super.getProfile().getId();
        if(uid == ownId){
            return WebApiResult.fail(StringConst.FOCUS_ON_YOURSELF_MSG);
        }
        //2.添加关注
        if(followsService.addFollow(uid, ownId) <= 0){
            return WebApiResult.fail(StringConst.FOLLOW_FOCUS_IS_EXIST);
        }
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
    @GetMapping("/free/favor.json/{uid}/{articleId}")
    public WebApiResult addFavor(@PathVariable("uid") int uid, @PathVariable("articleId") Integer articleId) throws BusinessException {
        //1.添加收藏
        int ownId = super.getProfile().getId();
        if(favorsService.addFavor(uid, ownId, articleId) <= 0){
            return WebApiResult.fail(StringConst.FAVOR_FOCUS_IS_EXIST);
        }
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
    @GetMapping("/free/latests.json")
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
    @GetMapping("/free/hottests.json")
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
    @GetMapping("/free/hotusers.json")
    public WebApiResult<EHotUser> hottestUsers(){
        return WebApiResult.success(statisticalService.getHottestUsers(ConfigConst.STATISTICAL_COUNT));
    }




}
