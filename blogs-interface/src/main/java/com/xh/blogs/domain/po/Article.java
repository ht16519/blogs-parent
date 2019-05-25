package com.xh.blogs.domain.po;

import com.xh.blogs.consts.CommonConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "t_article")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 作者id
     */
    @Column(name = "author_id")
    private Integer authorId;

    /**
     * 评论数量
     */
    private Integer comments;

    /**
     * 内容表达式
     */
    private String editor;

    /**
     * 收藏数量
     */
    private Integer favors;

    private Integer featured;

    /**
     * 所属组
     */
    @Column(name = "belong_group")
    private Integer belongGroup;

    /**
     * 图片数量
     */
    private Integer images;

    /**
     * 最后一张图片的id
     */
    @Column(name = "last_image_id")
    private Integer lastImageId;

    private Integer status;

    /**
     * 摘要信息
     */
    private String summary;

    /**
     * 所属标签
     */
    private String tags;

    /**
     * 标题
     */
    private String title;

    /**
     * 浏览量
     */
    private Integer views;

    /** 文章类型（1，0：是，否）*/
    private Integer type;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /** 文章附件*/
    private List<ArticleAccessory> albums;

    private User user;

    @Transient
    private String accessorys;

    public String[] getTagsArray() {
        if(StringUtils.isNotBlank(this.tags)){
            return this.tags.split(CommonConst.SEPARATOR);
        }
        return null;
    }

}