package com.xh.blogs.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_article")
public class Article {
    /**
     * 文章id
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
     * 评论数量
     */
    private Integer comments;

    /**
     * 内容表达式
     */
    private String editor;

    /**
     * 粉丝数量
     */
    private Integer favors;

    private Integer featured;

    /**
     * 所属组
     */
    @Column(name = "belong_group")
    private Integer belongGroup;

    /**
     * 图片数量
     */
    private Integer images;

    /**
     * 最后一张图片的id
     */
    @Column(name = "last_image_id")
    private Integer lastImageId;

    private Integer status;

    /**
     * 摘要信息
     */
    private String summary;

    /**
     * 所属标签
     */
    private String tags;

    /**
     * 标题
     */
    private String title;

    /**
     * 浏览量
     */
    private Integer views;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取文章id
     *
     * @return id - 文章id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置文章id
     *
     * @param id 文章id
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
     * 获取评论数量
     *
     * @return comments - 评论数量
     */
    public Integer getComments() {
        return comments;
    }

    /**
     * 设置评论数量
     *
     * @param comments 评论数量
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     * 获取内容表达式
     *
     * @return editor - 内容表达式
     */
    public String getEditor() {
        return editor;
    }

    /**
     * 设置内容表达式
     *
     * @param editor 内容表达式
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * 获取粉丝数量
     *
     * @return favors - 粉丝数量
     */
    public Integer getFavors() {
        return favors;
    }

    /**
     * 设置粉丝数量
     *
     * @param favors 粉丝数量
     */
    public void setFavors(Integer favors) {
        this.favors = favors;
    }

    /**
     * @return featured
     */
    public Integer getFeatured() {
        return featured;
    }

    /**
     * @param featured
     */
    public void setFeatured(Integer featured) {
        this.featured = featured;
    }

    /**
     * 获取所属组
     *
     * @return belong_group - 所属组
     */
    public Integer getBelongGroup() {
        return belongGroup;
    }

    /**
     * 设置所属组
     *
     * @param belongGroup 所属组
     */
    public void setBelongGroup(Integer belongGroup) {
        this.belongGroup = belongGroup;
    }

    /**
     * 获取图片数量
     *
     * @return images - 图片数量
     */
    public Integer getImages() {
        return images;
    }

    /**
     * 设置图片数量
     *
     * @param images 图片数量
     */
    public void setImages(Integer images) {
        this.images = images;
    }

    /**
     * 获取最后一张图片的id
     *
     * @return last_image_id - 最后一张图片的id
     */
    public Integer getLastImageId() {
        return lastImageId;
    }

    /**
     * 设置最后一张图片的id
     *
     * @param lastImageId 最后一张图片的id
     */
    public void setLastImageId(Integer lastImageId) {
        this.lastImageId = lastImageId;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取摘要信息
     *
     * @return summary - 摘要信息
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要信息
     *
     * @param summary 摘要信息
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取所属标签
     *
     * @return tags - 所属标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置所属标签
     *
     * @param tags 所属标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取浏览量
     *
     * @return views - 浏览量
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 设置浏览量
     *
     * @param views 浏览量
     */
    public void setViews(Integer views) {
        this.views = views;
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}