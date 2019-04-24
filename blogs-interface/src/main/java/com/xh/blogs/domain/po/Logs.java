package com.xh.blogs.domain.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_logs")
public class Logs {
    /**
     * 日志记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 获取日志记录id
     *
     * @return id - 日志记录id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置日志记录id
     *
     * @param id 日志记录id
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
     * 获取登录ip
     *
     * @return ip - 登录ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置登录ip
     *
     * @param ip 登录ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}