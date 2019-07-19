package com.xh.blogs.controller.home;

import com.xh.blogs.api.IOAuth2Service;
import com.xh.blogs.consts.*;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
    private IOAuth2Service oauth2Service;

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
        return KeyConst.REDIRECT_PREFIX_KEY1 + oauth2Service.getQQAuthorizeUrl(request);
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
        //1.通过QQAPI获取用户账号
        User user = oauth2Service.getOAuthUserByQQAPI(request);
        if(user == null){
            super.getModelMap(EmError.OAUTH_DO_AUTHENTICATIONINFO_IS_FAIL, model);
            return ViewUrl.LOGIN;
        }
        //1.已关联，直接登录，检验登录信息
        ShiroUtil.checkLogin(user.getUserName(), ConfigConst.DEFAULT_DEFAULT_PASSWORD, CommonConst.DEFALUT_REMEMBER_VALUE);
        //2.保存登录用户信息
        super.putProfile(model, user.getPassword() == null ? 1 : 0);
        //TODO 3.登录记录生成
        return RequestUrl.REDIRECT_HOME;
    }

}
