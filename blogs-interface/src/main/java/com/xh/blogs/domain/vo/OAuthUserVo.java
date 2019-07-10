package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Name OAuthUserVo
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OAuthUserVo {

    @NotEmpty(message = "用户名不能为空")
    @Size(max = 16, min = 5, message = "用户名长度应在5到16位之间")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 16, min = 3, message = "密码长度应在3到16位之间")
    private String password;

    private String nickName;

    private String openId;

    private Integer type;

    private Integer sex;

    private String avatar;

    private String authToken;

}
