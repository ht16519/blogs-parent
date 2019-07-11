package com.xh.blogs.controller.home;

import com.xh.blogs.api.IUserService;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.vo.OAuthUserVo;
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
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Name OAuthRegisterController
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
@Controller
@Slf4j
public class OAuthRegisterController extends BaseController{

    @Autowired
    private IUserService userService;

    @PostMapping("/login/oauth/bind")
    public String doOAuthRegisterAndLogin(OAuthUserVo authUserVo, ModelMap model, HttpServletRequest request) {
        try {
            //1.绑定操作
            int res = userService.oauthBind(authUserVo);
            if(res > 0){
                //2-1.登陆验证
                UsernamePasswordToken token = new UsernamePasswordToken(authUserVo.getUserName(), authUserVo.getPassword());
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
        }catch (BusinessException ex){
            log.error("login exception, message:{}, ip:{}", ex.getErrMsg(), RequestUtil.getIpAddress(request));
            super.getModelMap(ex, model);
        }catch (Exception e) {
            log.error("register exception:{}", e);
            super.getModelMap(EmError.UNKNOWN_ERROR, model);
        }
        return ViewUrl.BLOG_OAUTH_REG;
    }

}
