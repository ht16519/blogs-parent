package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_user_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

}