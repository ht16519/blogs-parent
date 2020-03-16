package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Name EHotUser
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EHotUser implements Serializable {

    private static final long serialVersionUID = 5688331938562946932L;

    private Integer id;

    private String nickName;

    private String avatar;

    private Integer fans;

}
