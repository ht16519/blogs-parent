package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Name UserPassWordVo
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordVo {

    private Integer id;

    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 16, min = 3, message = "密码长度应在3到16位之间")
    private String oldPassword;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 16, min = 3, message = "密码长度应在3到16位之间")
    private String password;

    private String rePassword;


}
