package com.xh.blogs.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_role_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "permission_id")
    private Integer permissionId;

}