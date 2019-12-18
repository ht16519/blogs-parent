package com.xh.blogs.api;

import com.xh.blogs.domain.vo.EmailVo;
import com.xh.blogs.exception.BusinessException;

import java.util.Map;

/**
 * @Name IMailService
 * @Description
 * @Author wen
 * @Date 2019-05-12
 */
public interface IMailService {

    void sendSimpleMail(String to, String title, String content);

    /**
     * @param to
     * @param title
     * @param content
     * @return void
     * @Name sendHtmlMail
     * @Description 发送富文本内容邮件
     * @Author wen
     * @Date 2019/4/30
     */
    void sendHtmlMail(String to, String title, String content) throws BusinessException;

    /**
    * @Name sendHtmlMail
    * @Description 发送模板邮件
    * @Author wen
    * @Date 2019/5/13
    * @param to
    * @param title
    * @param data
    * @param templatePath
    * @return void
    */
    void sendHtmlMail(String to, String title, Map<String, Object> data, String templatePath) throws BusinessException;

    /**
     * @param to
     * @param title
     * @param content
     * @param filePaths
     * @return void
     * @Name sendAttachmentMail
     * @Description 发送多附件邮件
     * @Author wen
     * @Date 2019/4/30
     */
    void sendAttachmentMail(String to, String title, String content, String[] filePaths) throws BusinessException;

    /**
     * @param to
     * @param title
     * @param content
     * @param rscPath
     * @param rscId
     * @return void
     * @Name sendInlinResourceMail
     * @Description 发送图片邮件
     * @Author wen
     * @Date 2019/4/30
     */
    void sendInlinResourceMail(String to, String title, String content, String rscPath, String rscId) throws BusinessException;

    /**
    * @Name mailAuthentication
    * @Description 验证邮箱和验证码是否正确
    * @Author wen
    * @Date 2019/7/11
    * @param emailVo
    * @param emailCode
    * @return void
    */
    void mailAuthentication(EmailVo emailVo, Object emailCode) throws BusinessException;
}
