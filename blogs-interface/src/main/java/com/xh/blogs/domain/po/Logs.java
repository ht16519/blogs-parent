package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logs implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

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

}