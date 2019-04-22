package com.xh.blogs.po;

import javax.persistence.*;

@Table(name = "t_article_accessory")
public class ArticleAccessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 源路径
     */
    private String original;

    /**
     * 预览路径
     */
    private String preview;

    private String screenshot;

    /**
     * 图片状态（1-，1：失效，正常）
     */
    private Integer status;

    /**
     * 来源（1，2：本地，网络）
     */
    private Integer store;

    /**
     * 所属文章id
     */
    @Column(name = "to_id")
    private Integer toId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取宽
     *
     * @return width - 宽
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置宽
     *
     * @param width 宽
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 获取高
     *
     * @return height - 高
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 设置高
     *
     * @param height 高
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 获取源路径
     *
     * @return original - 源路径
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 设置源路径
     *
     * @param original 源路径
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * 获取预览路径
     *
     * @return preview - 预览路径
     */
    public String getPreview() {
        return preview;
    }

    /**
     * 设置预览路径
     *
     * @param preview 预览路径
     */
    public void setPreview(String preview) {
        this.preview = preview;
    }

    /**
     * @return screenshot
     */
    public String getScreenshot() {
        return screenshot;
    }

    /**
     * @param screenshot
     */
    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    /**
     * 获取图片状态（1-，1：失效，正常）
     *
     * @return status - 图片状态（1-，1：失效，正常）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置图片状态（1-，1：失效，正常）
     *
     * @param status 图片状态（1-，1：失效，正常）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取来源（1，2：本地，网络）
     *
     * @return store - 来源（1，2：本地，网络）
     */
    public Integer getStore() {
        return store;
    }

    /**
     * 设置来源（1，2：本地，网络）
     *
     * @param store 来源（1，2：本地，网络）
     */
    public void setStore(Integer store) {
        this.store = store;
    }

    /**
     * 获取所属文章id
     *
     * @return to_id - 所属文章id
     */
    public Integer getToId() {
        return toId;
    }

    /**
     * 设置所属文章id
     *
     * @param toId 所属文章id
     */
    public void setToId(Integer toId) {
        this.toId = toId;
    }
}