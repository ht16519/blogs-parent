package com.xh.blogs.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_user")
@Data
@NoArgsConstructor
public class User implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户最后一次登录时间
     */
    @Column(name = "last_login")
    private Date lastLogin;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态（-1：冻结，1：正常）
     */
    private Integer status;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户头像
     */
    private String avatar;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 性别（1，0：男：女）
     */
    private Integer sex;

    /**
     * 分数
     */
    private Integer source;

    @Column(name = "active_email")
    private Integer activeEmail;

    /**
     * 点赞数量
     */
    private Integer comments;

    /**
     * 粉丝数
     */
    private Integer fans;

    /**
     * 支持数量
     */
    private Integer favors;

    /**
     * 关注数量
     */
    private Integer follows;

    /**
     * 发文数量
     */
    private Integer posts;

    /**
     * 签名
     */
    private String signature;

}