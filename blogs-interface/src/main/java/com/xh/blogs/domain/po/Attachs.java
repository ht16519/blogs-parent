package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_attachs")
@Data
@AllArgsConstructor
public class Attachs implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 链接
     */
    private String link;

    private Integer status;

    /** 是否底部顶*/
    @Column(name = "is_footer_top")
    private Integer isFooterTop;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_by")
    private Integer createBy;

    private Integer sort;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_by")
    private Integer updateBy;

    /**
     * 内容
     */
    private String content;

    public Attachs(Integer status) {
        this.status = status;
    }

    public Attachs() {
    }
}