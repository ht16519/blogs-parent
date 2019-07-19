package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
* @Name AccountProfile
* @Description
* @Author wen
* @Date 2019/4/24
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfile implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String nickName;

    private String email;

    private String mobile;

    private Date lastLogin;

    private String avatar;

    private Integer activeEmail;

    private Integer sex;

    private String signature;

    /** 是否需要绑定账号**/
    private int bingType;

}
