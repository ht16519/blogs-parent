package com.xh.blogs.service.impl;

import com.xh.blogs.service.IMailService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.domain.vo.EmailVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @Name MailService
 * @Description
 * @Author wen
 * @Date 2019-04-30
 */
@Service
@Slf4j
public class MailServiceImpl implements IMailService {

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private Configuration configuration;

    /**
     * @param to
     * @param title
     * @param content
     * @return void
     * @Name sendSimpleMail
     * @Description 发送简单的邮件
     * @Author wen
     * @Date 2019/4/30
     */
    @Override
    public void sendSimpleMail(String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        message.setFrom(from);
        sender.send(message);
    }

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
    @Override
    public void sendHtmlMail(String to, String title, String content) throws BusinessException {
        sender.send(this.getMimeMessage(to, title, content).getMessage());
    }

    @Override
    public void sendHtmlMail(String to, String title, Map<String, Object> data, String templatePath) throws BusinessException {
        try {
            Template template = configuration.getTemplate(ViewUrl.ACCOUNT_ACTIVATE_EMAIL);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
            //3.发送模板邮件
            this.sendHtmlMail(to, title, html);
        } catch (Exception e) {
            throw new BusinessException(EmError.SEND_EMAIL_FAIL);
        }
    }

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
    @Override
    public void sendAttachmentMail(String to, String title, String content, String[] filePaths) throws BusinessException {
        HelperBean helperBean = this.getMimeMessage(to, title, content);
        try {
            for (String filePath : filePaths) {
                FileSystemResource file = new FileSystemResource(new File(filePath));
                helperBean.getHelper().addAttachment(file.getFilename(), file);
            }
            sender.send(helperBean.getMessage());
        } catch (MessagingException e) {
            log.error("邮件发送失败:", e);
            throw new BusinessException(EmError.SEND_EMAIL_FAIL);
        }
    }

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
    @Override
    public void sendInlinResourceMail(String to, String title, String content, String rscPath, String rscId) throws BusinessException {
        HelperBean helperBean = this.getMimeMessage(to, title, content);
        FileSystemResource res = new FileSystemResource(new File(rscPath));
        try {
            helperBean.getHelper().addInline(rscId, res);
            sender.send(helperBean.getMessage());
        } catch (MessagingException e) {
            log.error("邮件发送失败:", e);
            throw new BusinessException(EmError.SEND_EMAIL_FAIL);
        }
    }

    @Override
    public void mailAuthentication(EmailVo emailVo, Object emailCode) throws BusinessException {
        //1.参数检验
        BeanValidator.check(emailVo);
        //2.邮箱验证
        if(emailCode == null){
            throw new BusinessException(EmError.CODE_IS_ERROR);
        }
        String str = (String)emailCode;
        String[] split = str.split(CommonConst.SEPARATOR);
        String email = split[0];
        String code = split[1];
        if(!email.equals(emailVo.getEmail())){
            throw new BusinessException(EmError.EMAIL_IS_ERROR);
        }
        if(!code.equals(emailVo.getVerifyCode())){
            throw new BusinessException(EmError.CODE_IS_ERROR);
        }
    }

    /**
     * @param to
     * @param title
     * @param content
     * @return javax.mail.internet.MimeMessage
     * @Name getMimeMessage
     * @Description 公共方法部分
     * @Author wen
     * @Date 2019/4/30
     */
    private HelperBean getMimeMessage(String to, String title, String content) throws BusinessException {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content, true);
            helper.setFrom(from);
            return new HelperBean(message, helper);
        } catch (MessagingException e) {
            log.error("邮件发送失败:", e);
            throw new BusinessException(EmError.SEND_EMAIL_FAIL);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class HelperBean {
        private MimeMessage message;
        private MimeMessageHelper helper;
    }

}
