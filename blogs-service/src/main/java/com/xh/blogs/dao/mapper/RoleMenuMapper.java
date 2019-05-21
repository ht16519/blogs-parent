package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.RoleMenu;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper extends IBaseMapper<RoleMenu> {

    /**
    * @Name batchDeleteByRoleId
    * @Description 批量物理删除菜单权限 by 角色id
    * @Author wen
    * @Date 2019/5/20
    * @param id
    * @return int 
    */
    int batchDeleteByRoleId(@Param("roleId") int id);
}