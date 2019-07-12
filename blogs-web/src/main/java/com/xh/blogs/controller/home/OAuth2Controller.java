package com.xh.blogs.controller.home;

import com.xh.blogs.api.ILoginService;
import com.xh.blogs.api.IUserService;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.OAuthUser;
import com.xh.blogs.domain.vo.OAuthUserVo;
import com.xh.blogs.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Name OAuth2Controller
 * @Description 第三方登录控制层
 * @Author wen
 * @Date 2019-07-10
 */
@Controller
@Slf4j
public class OAuth2Controller extends BaseController{

    @Autowired
    private IUserService userService;
    @Autowired
    private ILoginService loginService;

    /**
     * @Name authorizeUrl
     * @Description 获取qq授权地址
     * @Author wen
     * @Date 2019/7/10
     * @param request
     * @return java.lang.String
     */
    @GetMapping("/login/qq/authorize")
    public String authorizeUrl(HttpServletRequest request){
        //重定向到QQ授权页面
        return KeyConst.REDIRECT_PREFIX_KEY1 + loginService.getQQAuthorizeUrl(request);
    }

    /**
     * @Name qqLoginCallBack
     * @Description qq登录回调
     * @Author wen
     * @Date 2019/7/10
     * @param request
     * @return java.lang.String
     */
    @GetMapping("/login/qq/callback")
    public String qqLoginCallBack(HttpServletRequest request, ModelMap model) {
        //1.检查本地是否有与登录QQ绑定的用户数据
        OAuthUser authUser = loginService.getOAuthUserByQQAPI(request);
        User user = authUser.getUser();
        if(user == null){
            //用户QQ未关联系统，去关联，返回token，并跳转绑定页面
            model.put(KeyConst.OAUTH_TOKEN_KEY, authUser.getToken());
            return ViewUrl.BLOG_OAUTH_REG;
        }else {
            //1.已关联，直接登录，检验登录信息
            ShiroUtil.checkLogin(user.getUserName(), ConfigConst.DEFAULT_DEFAULT_PASSWORD, 0);
            //2.保存登录用户信息
            super.putProfile(model);
            //TODO 3.登录记录生成
            return RequestUrl.REDIRECT_HOME;
        }
    }

    /**
    * @Name doOAuthRegisterAndLogin
    * @Description QQ登录用户绑定
    * @Author wen
    * @Date 2019/7/12
    * @param authUserVo
    * @param model
    * @return java.lang.String
    */
    @PostMapping("/login/oauth/bind")
    public String doOAuthRegisterAndLogin(OAuthUserVo authUserVo, ModelMap model) {
        //1.绑定操作
        int res = userService.oauthBind(authUserVo);
        if(res > 0){
            //2-1.登陆验证
            ShiroUtil.checkLogin(authUserVo.getUserName(), authUserVo.getPassword(), 0);
            //2-2.登陆成功，设置用户信息缓存
            super.putProfile(model);
            //TODO 3.登录记录生成
            return RequestUrl.REDIRECT_HOME;
        }
        return ViewUrl.BLOG_OAUTH_REG;
    }

}
