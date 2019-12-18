package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_article_accessory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAccessory implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 源路径
     */
    private String original;

    /**
     * 预览路径
     */
    private String preview;

    private String screenshot;

    /**
     * 图片状态（1-，1：失效，正常）
     */
    private Integer status;

    /**
     * 来源（1，2：本地，网络）
     */
    private Integer store;

    /**
     * 所属文章id
     */
    @Column(name = "to_id")
    private Integer toId;

}