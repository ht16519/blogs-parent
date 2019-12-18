package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * @Name EComments
 * @Description
 * @Author wen
 * @Date 2019-04-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EComments {

    private Integer id;

    private String content;

    private String createTime;

    private Integer userId;

    private Integer pid;

    private String nickName;

    private String avatar;

    private List<EComments> childs;
}
