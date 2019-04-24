package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 分类标签id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分类key
     */
    private String groupKey;

    /**
     * 分类value
     */
    private String groupValue;

    /**
     * 状态（-1，失效，1：正常）
     */
    private Integer status;

}