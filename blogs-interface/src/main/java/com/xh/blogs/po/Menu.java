package com.xh.blogs.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_menu")
public class Menu {
    /**
     * 菜单id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单状态（-1：弃用，1：启用）
     */
    private Integer status;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人id
     */
    @Column(name = "create_by")
    private Integer createBy;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 对应请求地址
     */
    private String url;

    /**
     * 父级菜单id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 所有上级id，格式：(1)(2)(3)
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 菜单等级(1，2，3）
     */
    private Integer level;

    /**
     * 图标
     */
    private String icon;

    /**
     * 获取菜单id
     *
     * @return id - 菜单id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置菜单id
     *
     * @param id 菜单id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单状态（-1：弃用，1：启用）
     *
     * @return status - 菜单状态（-1：弃用，1：启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置菜单状态（-1：弃用，1：启用）
     *
     * @param status 菜单状态（-1：弃用，1：启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     * 获取创建人id
     *
     * @return create_by - 创建人id
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人id
     *
     * @param createBy 创建人id
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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
     * 获取对应请求地址
     *
     * @return url - 对应请求地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置对应请求地址
     *
     * @param url 对应请求地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取父级菜单id
     *
     * @return parent_id - 父级菜单id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级菜单id
     *
     * @param parentId 父级菜单id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取所有上级id，格式：(1)(2)(3)
     *
     * @return parent_ids - 所有上级id，格式：(1)(2)(3)
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置所有上级id，格式：(1)(2)(3)
     *
     * @param parentIds 所有上级id，格式：(1)(2)(3)
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 获取菜单等级(1，2，3）
     *
     * @return level - 菜单等级(1，2，3）
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置菜单等级(1，2，3）
     *
     * @param level 菜单等级(1，2，3）
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}