package com.xh.blogs.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_group")
public class Group {
    /**
     * 分类标签id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分类key
     */
    private String key;

    /**
     * 分类value
     */
    private String value;

    /**
     * 状态（-1，失效，1：正常）
     */
    private Integer status;

    /**
     * 获取分类标签id
     *
     * @return id - 分类标签id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置分类标签id
     *
     * @param id 分类标签id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分类key
     *
     * @return key - 分类key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置分类key
     *
     * @param key 分类key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取分类value
     *
     * @return value - 分类value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置分类value
     *
     * @param value 分类value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取状态（-1，失效，1：正常）
     *
     * @return status - 状态（-1，失效，1：正常）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（-1，失效，1：正常）
     *
     * @param status 状态（-1，失效，1：正常）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}