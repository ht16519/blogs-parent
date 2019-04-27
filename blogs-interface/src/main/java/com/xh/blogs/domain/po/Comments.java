package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "t_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments implements Serializable{

    /**
     * 评论id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论的用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 评论内容
     */
    private String content;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 上级id
     */
    private Integer pid;

    /**
     * 状态（-1， 10：删除，正常）
     */
    private Integer status;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    private List<Comments> childs;

    private Article article;
}