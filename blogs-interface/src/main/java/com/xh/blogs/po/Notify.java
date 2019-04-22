package com.xh.blogs.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_notify")
public class Notify {
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
     * 所属用户id
     */
    @Column(name = "own_id")
    private Integer ownId;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 状态（1：未读，1：已读）
     */
    private Integer status;

    /**
     * 获取消息id
     *
     * @return id - 消息id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置消息id
     *
     * @param id 消息id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取事件
     *
     * @return event - 事件
     */
    public Integer getEvent() {
        return event;
    }

    /**
     * 设置事件
     *
     * @param event 事件
     */
    public void setEvent(Integer event) {
        this.event = event;
    }

    /**
     * 获取触发者id
     *
     * @return from_id - 触发者id
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     * 设置触发者id
     *
     * @param fromId 触发者id
     */
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    /**
     * 获取所属用户id
     *
     * @return own_id - 所属用户id
     */
    public Integer getOwnId() {
        return ownId;
    }

    /**
     * 设置所属用户id
     *
     * @param ownId 所属用户id
     */
    public void setOwnId(Integer ownId) {
        this.ownId = ownId;
    }

    /**
     * 获取文章id
     *
     * @return article_id - 文章id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 设置文章id
     *
     * @param articleId 文章id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取状态（1：未读，1：已读）
     *
     * @return status - 状态（1：未读，1：已读）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（1：未读，1：已读）
     *
     * @param status 状态（1：未读，1：已读）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}