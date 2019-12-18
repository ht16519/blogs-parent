package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_follows")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follows implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 博主id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 粉丝id
     */
    @Column(name = "follow_id")
    private Integer followId;

    private User user;

}