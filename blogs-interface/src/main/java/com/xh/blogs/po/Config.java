package com.xh.blogs.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_config")
public class Config {
    /**
     * 系统配置id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配置key
     */
    private String key;

    /**
     * 配置type
     */
    private Integer type;

    /**
     * 配置value
     */
    private String value;

    /**
     * 获取系统配置id
     *
     * @return id - 系统配置id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置系统配置id
     *
     * @param id 系统配置id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取配置key
     *
     * @return key - 配置key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置配置key
     *
     * @param key 配置key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取配置type
     *
     * @return type - 配置type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置配置type
     *
     * @param type 配置type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取配置value
     *
     * @return value - 配置value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置配置value
     *
     * @param value 配置value
     */
    public void setValue(String value) {
        this.value = value;
    }
}