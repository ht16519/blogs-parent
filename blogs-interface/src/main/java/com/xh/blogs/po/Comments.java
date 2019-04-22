package com.xh.blogs.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_comments")
public class Comments {
    /**
     * 评论id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 作者id
     */
    @Column(name = "author_id")
    private Integer authorId;

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
     * 状态
     */
    private Integer status;

    /**
     * 评论对象id
     */
    @Column(name = "to_id")
    private Integer toId;

    /**
     * 获取评论id
     *
     * @return id - 评论id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置评论id
     *
     * @param id 评论id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取作者id
     *
     * @return author_id - 作者id
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置作者id
     *
     * @param authorId 作者id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
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
     * 获取上级id
     *
     * @return pid - 上级id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置上级id
     *
     * @param pid 上级id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取评论对象id
     *
     * @return to_id - 评论对象id
     */
    public Integer getToId() {
        return toId;
    }

    /**
     * 设置评论对象id
     *
     * @param toId 评论对象id
     */
    public void setToId(Integer toId) {
        this.toId = toId;
    }
}