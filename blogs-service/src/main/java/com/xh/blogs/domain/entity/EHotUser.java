package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name EHotUser
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EHotUser {

    private Integer id;

    private String nickName;

    private String avatar;

    private Integer fans;

}
