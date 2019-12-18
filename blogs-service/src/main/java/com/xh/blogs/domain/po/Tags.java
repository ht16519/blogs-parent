package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_tags")
@Data
@AllArgsConstructor
public class Tags implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态（-10，10：隐藏，显示）
     */
    private Integer status;

    /** 顺序*/
    private Integer sort;

    public Tags() {
    }

    public Tags(Integer status) {
        this.status = status;
    }

}