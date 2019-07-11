package com.xh.blogs.controller.home;

import com.xh.blogs.api.ILoginService;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.OAuthUser;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.RequestUtil;
import com.xh.blogs.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Name LoginController
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
@Controller
@Slf4j
public class LoginController extends BaseController{

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
    public String authorizeUrl(HttpServletRequest request, ModelMap model){
        try {
            return KeyConst.REDIRECT_PREFIX_KEY1 + loginService.getQQAuthorizeUrl(request);
        } catch (BusinessException e) {
            super.getModelMap(e, model);
            return ViewUrl.LOGIN;
        }
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
        try {
            OAuthUser authUser = loginService.qqLoginCallBack(request);
            User user = authUser.getUser();
            if(user == null){            //用户QQ未关联系统，去关联
                model.put(KeyConst.OAUTH_TOKEN_KEY, authUser.getToken());
                return ViewUrl.BLOG_OAUTH_REG;
            }else {                      //已关联，直接登录
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), ConfigConst.DEFAULT_DEFAULT_PASSWORD);
                ShiroUtil.getSubject().login(token);
                super.putProfile(model);
                //TODO 登录记录生成
                return RequestUrl.REDIRECT_HOME;
            }
        } catch (UnknownAccountException e) {
            super.getModelMap(EmError.USER_NAME_OR_PASSWORD_ERROR, model);
            log.error("login exception, username:{}, message:{}, ip:{}", e.getMessage(), EmError.USER_NAME_OR_PASSWORD_ERROR.getErrMsg(), RequestUtil.getIpAddress(request));
        } catch (LockedAccountException e) {
            super.getModelMap(EmError.USER_IS_DISABLE, model);
            log.error("login exception, username:{}, message:{}, ip:{}", e.getMessage() ,EmError.USER_IS_DISABLE.getErrMsg(), RequestUtil.getIpAddress(request));
        } catch (AuthenticationException e) {
            super.getModelMap(EmError.USER_NAME_OR_PASSWORD_ERROR, model);
            log.error("login exception, username:{}, message:{}, ip:{}", e.getMessage() ,EmError.USER_NAME_OR_PASSWORD_ERROR.getErrMsg(), RequestUtil.getIpAddress(request));
        } catch (BusinessException e) {
            log.error("login exception, message:{}, ip:{}", e.getErrMsg(), RequestUtil.getIpAddress(request));
            super.getModelMap(e, model);
        }catch (Exception e) {
            log.error("register exception:{}", e);
            super.getModelMap(EmError.UNKNOWN_ERROR, model);
        }
        return ViewUrl.BLOG_OAUTH_REG;
    }


}
