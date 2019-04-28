package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_comments_sublist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsSublist implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态（-1， 10：删除，正常）
     */
    private Integer status;

    /**
     * 评论者id
     */
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "article_id")
    private Integer articleId;

}