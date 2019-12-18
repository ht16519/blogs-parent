package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_favors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favors implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 文章id
     */
    @Id
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 喜欢者id
     */
    @Column(name = "own_id")
    private Integer ownId;

}