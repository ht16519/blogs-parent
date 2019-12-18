package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Name EmailVo
 * @Description
 * @Author wen
 * @Date 2019-05-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVo {

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotEmpty(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "验证码只能是6位")
    private String verifyCode;

}
