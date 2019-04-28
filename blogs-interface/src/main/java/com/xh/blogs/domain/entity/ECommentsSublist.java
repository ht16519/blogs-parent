package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name EComments
 * @Description
 * @Author wen
 * @Date 2019-04-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ECommentsSublist {

    private Integer id;

    private String content;

    private String createTime;

    private Integer userId;

    private String nickName;

}
