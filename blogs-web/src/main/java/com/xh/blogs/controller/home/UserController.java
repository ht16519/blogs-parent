package com.xh.blogs.controller.home;

import com.xh.blogs.api.IArticleService;
import com.xh.blogs.api.IUploadService;
import com.xh.blogs.api.IUserService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.StringConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.vo.RegisterSuccess;
import com.xh.blogs.domain.vo.UserVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Name UserController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Controller
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUploadService uploadService;
    @Autowired
    private IArticleService articleService;

    /**
    * @Name bloggerInfo
    * @Description 博主详情
    * @Author wen
    * @Date 2019/4/27
    * @param uid
    * @param number
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/ta/{uid}/{number}")
    public String bloggerInfo(@PathVariable("uid") int uid, @PathVariable("number") int number, ModelMap model) {
        model.put(CommonConst.USER_INFO_KEY, userService.getById(uid));
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, articleService.getByIdWithPage(uid, number));
        return ViewUrl.BLOGGER_HOME;
    }

    /**
    * @Name doLogout
    * @Description 退出登录
    * @Author wen
    * @Date 2019/4/27
    * @param
    * @return java.lang.String
    */
    @GetMapping("/logout")
    public String doLogout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject != null) {
            subject.logout();
        }
        return RequestUrl.REDIRECT_INDEX;
    }

    /**
    * @Name loginView
    * @Description 登录页面
    * @Author wen
    * @Date 2019/4/27
    * @param
    * @return java.lang.String
    */
    @GetMapping("/login")
    public String loginView() {
        return ViewUrl.LOGIN;
    }

    /**
    * @Name registerView
    * @Description 注册页面
    * @Author wen
    * @Date 2019/4/27
    * @param
    * @return java.lang.String
    */
    @GetMapping("/register")
    public String registerView() {
        return ViewUrl.REGISTER;
    }

    /**
    * @Name doRegister
    * @Description 注册操作
    * @Author wen
    * @Date 2019/4/27
    * @param userVo
    * @param model
    * @return java.lang.String
    */
    @PostMapping("/register")
    public String doRegister(UserVo userVo, ModelMap model) {
        try {
            //1.注册操作
            int res = userService.register(userVo);
            //TODO 发送邮件
            if(res > 0){
                super.getModelMap(new RegisterSuccess(RequestUrl.LOGIN_URL, StringConst.TO_LOG_IN), StringConst.REGISTERED_SUCCESSFULLY_MSG, model);
                return ViewUrl.REGISTER_RESULT;
            }
        }catch (BusinessException ex){
            super.getModel(ex, model);
        }catch (Exception e) {
            log.error("register exception:{}", e);
        }
        return ViewUrl.REGISTER;
    }

    /**
    * @Name doLogin
    * @Description 登录操作
    * @Author wen
    * @Date 2019/4/27
    * @param userName
    * @param password
    * @param rememberMe
    * @param model
    * @return java.lang.String
    */
    @PostMapping("/login")
    public String doLogin(String userName, String password, @RequestParam(value = "rememberMe",defaultValue = "0") int rememberMe, ModelMap model) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            if (rememberMe == 1) {
                token.setRememberMe(true);
            }
            ShiroUtil.getSubject().login(token);
            super.putProfile(model);
            //TODO 登录记录生成
            return RequestUrl.REDIRECT_HOME;
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                super.getModelMap(EmError.USER_DATA_ERROR, model);
            } else if (e instanceof LockedAccountException) {
                super.getModelMap(EmError.USER_IS_DISABLE, model);
            } else {
                super.getModelMap(EmError.USER_NAME_OR_PASSWORD_ERROR, model);
            }
            log.error("login exception:{}", e);
        }
        return ViewUrl.LOGIN;
    }


}
