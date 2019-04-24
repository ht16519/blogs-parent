package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Name EUser
 * @Description
 * @Author wen
 * @Date 2019-04-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EUser implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String nickName;

    private String avatar;
}
