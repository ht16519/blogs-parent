package com.xh.blogs.controller.home;

import com.xh.blogs.api.IArticleService;
import com.xh.blogs.api.IUploadService;
import com.xh.blogs.api.IUserService;
import com.xh.blogs.consts.*;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.*;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
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
import org.springframework.web.multipart.MultipartFile;

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
    * @Name accountPasswordDo
    * @Description 修改密码操作
    * @Author wen
    * @Date 2019/5/4
    * @param 
    * @return java.lang.String 
    */
    @PostMapping("/account/profile/password")
    public String accountPasswordDo(UserPasswordVo passwordVo, ModelMap model) {
        try {
            passwordVo.setId(super.getProfile().getId());
            passwordVo.setUserName(super.getProfile().getUserName());
            userService.updatePasswordById(passwordVo);
            super.getModelMap(model);
        } catch (BusinessException e) {
            super.getModel(e, model);
        }
        return ViewUrl.ACCOUNT_PASSWORD;
    }

    /**
    * @Name accountPassword
    * @Description 修改密码跳转
    * @Author wen
    * @Date 2019/5/4
    * @param 
    * @return java.lang.String 
    */
    @GetMapping("/account/profile/password")
    public String accountPassword() {
        return ViewUrl.ACCOUNT_PASSWORD;
    }

    /**
    * @Name accountAvatarDo
    * @Description 修改头像操作
    * @Author wen
    * @Date 2019/5/4
    * @param
    * @return java.lang.String
    */
    @PostMapping("/account/profile/avatar")
    public String accountAvatarDo(MultipartFile file, AvatarVo avatarVo, ModelMap model) {
        try {
            //1.上传图片
            String path = uploadService.uploadThum4Image(file, avatarVo, super.getProfile().getUserName());
            //2.修改用户头像路径
            User user = userService.updateAvatarById(super.getProfile().getId(), path);
            //3.更新用户缓存
            super.putProfile(user);
        } catch (BusinessException ex) {
            if(ex.getErrCode() == ErrorConst.PARAMETER_VERIFICATION_ERROR_CODE){
                ex.setErrMsg(StringConst.AVATAR_PARAMETERS_ERROR);
            }
            super.getModel(ex, model);
        }
        return ViewUrl.ACCOUNT_AVATAR;
    }

    /**
    * @Name accountAvatar
    * @Description 修改头像跳转
    * @Author wen
    * @Date 2019/5/4
    * @param
    * @return java.lang.String
    */
    @GetMapping("/account/profile/avatar")
    public String accountAvatar() {
        return ViewUrl.ACCOUNT_AVATAR;
    }

    /**
     * @Name profile
     * @Description 修改基本信息操作
     * @Author wen
     * @Date 2019/5/3
     * @param
     * @return java.lang.String
     */
    @PostMapping("/account/profile/basic")
    public String accountBasicDo(UserBasicVo userBasicVo, ModelMap model){
        try {
            //1.修改用户信息
            AccountProfile profile = super.getProfile();
            userBasicVo.setId(profile.getId());
            User user = userService.updateBasicById(userBasicVo);
            //2.更新session中用户基本信息
            super.putProfile(user);
            super.getModelMap(StringConst.UPDATE_USER_INFO_SUCCESSED, model);
        } catch (BusinessException e) {
            super.getModel(e, model);
        }
        return ViewUrl.ACCOUNT_PROFILE;
    }

    /**
    * @Name profile
    * @Description 修改基本信息跳转
    * @Author wen
    * @Date 2019/5/3
    * @param
    * @return java.lang.String
    */
    @GetMapping("/account/profile/basic")
    public String accountBasic(ModelMap model) {
        try {
            model.put(CommonConst.DATA_RESULT_KEY, super.getProfile());
        } catch (BusinessException e) {
            super.getModel(e, model);
        }
        return ViewUrl.ACCOUNT_PROFILE;
    }

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
