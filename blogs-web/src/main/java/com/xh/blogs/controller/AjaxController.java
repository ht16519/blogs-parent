package com.xh.blogs.controller;

import com.xh.blogs.api.IFollowsService;
import com.xh.blogs.api.INotifyService;
import com.xh.blogs.api.IStatisticalService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.domain.entity.EHotArticle;
import com.xh.blogs.domain.entity.EHotUser;
import com.xh.blogs.domain.vo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private IFollowsService followsService;
    @Autowired
    private INotifyService notifyService;

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
    @GetMapping("/follow.json/{uid}")
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
    @GetMapping("/favor.json/{uid}")
    public WebApiResult addFollow(@PathVariable("uid") Integer uid){
        //1.判断是否是自己
        int ownId = super.getProfile().getId();
        if(uid.equals(ownId)){
            return WebApiResult.fail(CommonConst.FOCUS_ON_YOURSELF_MSG);
        }
        //2.添加关注
        if(followsService.addFollow(uid, ownId) <= 0){
            return WebApiResult.fail(CommonConst.FOCUS_IS_EXIST);
        }
        //3.关注成功，发送通知消息
        notifyService.sendMsg(ownId, uid, NotifyConst.EVENT_FOLLOWS, null);
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
//    @GetMapping("/latests.json")
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
//    @GetMapping("/hottests.json")
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
