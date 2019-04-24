package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserVo implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private String email;

    private String mobile;

    private String password;

    private String rePassword;

    private String userName;

    private String nickName;
}
