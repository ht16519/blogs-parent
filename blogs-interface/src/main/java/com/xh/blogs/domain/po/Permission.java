package com.xh.blogs.domain.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_permission")
public class Permission {
    /**
     * 权限id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 状态（-1，1：失效，启用）
     */
    private Integer status;

    /**
     * 获取权限id
     *
     * @return id - 权限id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置权限id
     *
     * @param id 权限id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取路径
     *
     * @return url - 路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置路径
     *
     * @param url 路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取状态（-1，1：失效，启用）
     *
     * @return status - 状态（-1，1：失效，启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态（-1，1：失效，启用）
     *
     * @param status 状态（-1，1：失效，启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}