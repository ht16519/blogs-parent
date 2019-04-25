package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_notify")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notify implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 消息id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 事件
     */
    private Integer event;

    /**
     * 触发者id
     */
    @Column(name = "from_id")
    private Integer fromId;

    /**
     * 接收者id
     */
    @Column(name = "to_id")
    private Integer toId;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 状态（1：未读，1：已读）
     */
    private Integer status;

    /** 相关文章*/
    private Article article;

    /** 触发者*/
    private User user;

}