package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 状态（-1，1：失效，启用）
     */
    private Integer status;

}