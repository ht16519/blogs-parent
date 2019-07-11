package com.xh.blogs.controller.home;

import com.xh.blogs.api.IMailService;
import com.xh.blogs.api.ISmsService;
import com.xh.blogs.api.IUploadService;
import com.xh.blogs.api.IUserService;
import com.xh.blogs.consts.*;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.dao.cache.RedisCacheUtil;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.domain.vo.*;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.CommonUtil;
import com.xh.blogs.utils.ShiroUtil;
import com.xh.blogs.utils.VerificationCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name ProfileController
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Slf4j
@Controller
@RequestMapping("/home/account")
public class ProfileController extends BaseController {

    @Autowired
    private IMailService mailService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUploadService uploadService;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ISmsService smsService;


    /**
     * @param
     * @return java.lang.String
     * @Name accountPasswordDo
     * @Description 修改密码操作
     * @Author wen
     * @Date 2019/5/4
     */
    @PostMapping("/password")
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
     * @param
     * @return java.lang.String
     * @Name accountPassword
     * @Description 修改密码跳转
     * @Author wen
     * @Date 2019/5/4
     */
    @GetMapping("/password")
    public String accountPassword() {
        return ViewUrl.ACCOUNT_PASSWORD;
    }

    /**
     * @param
     * @return java.lang.String
     * @Name accountAvatarDo
     * @Description 修改头像操作
     * @Author wen
     * @Date 2019/5/4
     */
    @PostMapping("/avatar")
    public String accountAvatarDo(MultipartFile file, AvatarVo avatarVo, ModelMap model) {
        try {
            //1.上传图片
            String path = uploadService.uploadThum4Image(file, avatarVo, super.getProfile().getUserName());
            //2.修改用户头像路径
            User user = userService.updateAvatarById(super.getProfile().getId(), path);
            //3.更新用户缓存
            super.putProfile(user);
            super.getModelMap(model);
        } catch (BusinessException ex) {
            if (ex.getErrCode() == ErrorConst.PARAMETER_VERIFICATION_ERROR_CODE) {
                ex.setErrMsg(StringConst.AVATAR_PARAMETERS_ERROR);
            }
            super.getModel(ex, model);
        }
        return ViewUrl.ACCOUNT_AVATAR;
    }

    /**
     * @param
     * @return java.lang.String
     * @Name accountAvatar
     * @Description 修改头像跳转
     * @Author wen
     * @Date 2019/5/4
     */
    @GetMapping("/avatar")
    public String accountAvatar() {
        return ViewUrl.ACCOUNT_AVATAR;
    }

    /**
     * @param
     * @return java.lang.String
     * @Name profile
     * @Description 修改基本信息操作
     * @Author wen
     * @Date 2019/5/3
     */
    @PostMapping("/basic")
    public String accountBasicDo(UserBasicVo userBasicVo, ModelMap model) {
        try {
            //1.修改用户信息
            AccountProfile profile = super.getProfile();
            userBasicVo.setId(profile.getId());
            User user = userService.updateBasicById(userBasicVo);
            //2.更新session中用户基本信息
            super.putProfile(user);
            super.getModelMap(model);
        } catch (BusinessException e) {
            super.getModel(e, model);
        }
        return ViewUrl.ACCOUNT_PROFILE;
    }

    /**
     * @param
     * @return java.lang.String
     * @Name profile
     * @Description 修改基本信息跳转
     * @Author wen
     * @Date 2019/5/3
     */
    @GetMapping("/basic")
    public String accountBasic(ModelMap model) {
        try {
            model.put(CommonConst.DATA_RESULT_KEY, super.getProfile());
        } catch (BusinessException e) {
            super.getModel(e, model);
        }
        return ViewUrl.ACCOUNT_PROFILE;
    }

    /**
    * @Name email
    * @Description 邮箱认证页面
    * @Author wen
    * @Date 2019/5/13
    * @param
    * @return java.lang.String
    */
    @GetMapping("/email/back")
    public String email(ModelMap model) {
        Object emailAndCode = ShiroUtil.sessionGetValue(KeyConst.EMAIL_CODE_KEY);
        if(emailAndCode == null){
            super.getModelMap(EmError.EMAIL_OR_CODE_IS_ERROR, model);
            return ViewUrl.ACCOUNT_EMAIL;
        }
        String s = emailAndCode.toString();
        String[] split = s.split(CommonConst.SEPARATOR);
        if(split.length != 2){
            super.getModelMap(EmError.EMAIL_OR_CODE_IS_ERROR, model);
            return ViewUrl.ACCOUNT_EMAIL;
        }
        String email = split[0];
        String code = split[1];
        model.put(KeyConst.RESTUL_EMAIL_CODE_KEY, code);
        model.put(KeyConst.USER_INPUT_EMAIL_KEY, email);
        return ViewUrl.ACCOUNT_EMAIL;
    }

    @GetMapping("/email")
    public String emailView() {
        return ViewUrl.ACCOUNT_EMAIL;
    }

    @GetMapping("/active/email")
    public String activeMailView(ModelMap model) throws BusinessException {
        User user = userService.getById(super.getProfile().getId());
        if(user.getActiveEmail().equals(CommonConst.EFFECTIVE_STATUS)){
            super.getModelMap(EmError.USER_EMAIL_IS_ACTIVE.getErrMsg(), model);
            return ViewUrl.ACCOUNT_PROFILE;
        }
        return ViewUrl.ACCOUNT_EMAIL;
    }

    /**
    * @Name emailPost
    * @Description 邮箱认证
    * @Author wen
    * @Date 2019/5/13
    * @param
    * @return java.lang.String
    */
    @PostMapping("/email")
    public String emailPost(EmailVo emailVo, ModelMap model) {
        String userName = null;
        try {
            AccountProfile profile = super.getProfile();
            userName = profile.getUserName();
            //1-1.获取verifyCode缓存
            String emailCode = redisCacheUtil.get(profile.getNickName());
            //1-2.移除verifyCode缓存
            redisCacheUtil.delete(profile.getNickName());
            //2.验证邮箱和验证码是否正确
            mailService.mailAuthentication(emailVo, emailCode);
            //3.更新session中用户基本信息
            User user = userService.activeEmailById(emailVo, profile.getId());
            super.putProfile(user);
            super.getModelMap(model);
        } catch (BusinessException e) {
            super.getModelMap(e, model);
            log.error("邮箱验证异常，用户名:{}", userName);
        }
        return ViewUrl.ACCOUNT_EMAIL;
    }

    /**
    * @Name sendEmail
    * @Description 发邮件
    * @Author wen
    * @Date 2019/5/13
    * @param email
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    @PostMapping("/email/send.json")
    @ResponseBody
    public WebApiResult sendEmail(@RequestParam("email") String email, @RequestParam("code") String code) throws BusinessException {
        //1-1.校验验证码
        VerificationCodeUtil.check(code);
        //1-2.验证邮箱信息
        AccountProfile profile = super.getProfile();
        userService.validationEmail(email, profile);
        //2.创建并发送消息到邮件MQ
        this.createAndSendEmailMsg(email, profile);
        return WebApiResult.success();
    }

    /**
    * @Name createAndSendEmailMsg
    * @Description 创建并发送消息到邮件MQ
    * @Author wen
    * @Date 2019/7/11
    * @param email
    * @param profile
    * @return void
    */
    private void createAndSendEmailMsg(String email, AccountProfile profile) {
        //2-1.组建模板数据
        Map<String, Object> data = new HashMap();
        data.put(KeyConst.USER_NICK_NAME_KEY, profile.getNickName());
        data.put(KeyConst.RESTUL_EMAIL_CODE_KEY, CommonUtil.getRAS(6));
        data.put(KeyConst.SITE_DOMAIN, this.getSiteDomin());
        data.put(KeyConst.SITE_NAME, this.getSiteName());
        data.put(KeyConst.SEND_TO_EMAIL_KEY, email);
        data.put(KeyConst.SEND_EMAIL_TITLE_KEY, this.getTemplateTitle());
        //2-2.发送邮件MQ消息
        smsService.sendEmailMsg(data);
    }

    private String getSiteDomin() {
        Object obj = servletContext.getAttribute(KeyConst.SITE_DOMAIN);
        if(obj == null){
            return CommonConst.EMPTY_STRING;
        }
        return obj.toString();
    }

    private String getSiteName() {
        Object obj = servletContext.getAttribute(KeyConst.SITE_NAME);
        if(obj == null){
            return ConfigConst.DEFAULT_SYSTEM_EMAIL_NAME;
        }
        return obj.toString();
    }

    /**
    * @Name getTemplateTitle
    * @Description 获取邮件模板title
    * @Author wen
    * @Date 2019/6/1
    * @param 
    * @return java.lang.String 
    */
    private String getTemplateTitle() {
        Object obj = servletContext.getAttribute(KeyConst.EMAIL_TEMPLATE_TITLE_KEY);
        if(obj == null){
            return ConfigConst.DEFAULT_SYSTEM_EMAIL_TITLE;
        }
        return obj.toString();
    }

}
