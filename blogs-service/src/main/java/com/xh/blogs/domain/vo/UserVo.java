package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Name UserVo
 * @Description
 * @Author wen
 * @Date 2019-04-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVo implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private String mobile;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 16, min = 3, message = "密码长度应在3到16位之间")
    private String password;

    private String rePassword;

    @NotEmpty(message = "用户名不能为空")
    @Size(max = 16, min = 5, message = "用户名长度应在5到16位之间")
    private String userName;

    @NotEmpty(message = "昵称不能为空")
    @Size(max = 9, min = 1, message = "昵称长度应在1到9位之间")
    private String nickName;
}
