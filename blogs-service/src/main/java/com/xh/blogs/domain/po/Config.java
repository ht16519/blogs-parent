package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 系统配置id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配置key
     */
    private String key;

    /**
     * 配置type
     */
    private Integer type;

    /**
     * 配置value
     */
    private String value;

    /** 状态*/
    private Integer status;

}