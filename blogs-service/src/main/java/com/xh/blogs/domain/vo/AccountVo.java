package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Name AccountVo
 * @Description
 * @Author wen
 * @Date 2019-05-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountVo implements Serializable {

    private static final long serialVersionUID = -3376551803423560261L;

    @NotEmpty(message = "验证码不为空")
    private String securityCode;

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 3, max = 16, message = "用户名由3-16个字符组成")
    private  String userName;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 3, max = 16, message = "用户名由3-18个字符组成")
    private String password;

    private int rememberMe = 0;


}
