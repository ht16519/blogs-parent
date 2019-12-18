package com.xh.blogs.domain.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_friend_link")
public class FriendLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logo;

    private String remark;

    @Column(name = "site_name")
    private String siteName;

    private Integer sort;

    private String url;

}