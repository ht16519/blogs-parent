package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends IBaseMapper<Role> {

    /**
    * @Name selectMenusById
    * @Description 通过id获取角色菜单信息
    * @Author wen
    * @Date 2019/5/18
    * @param id
    * @return com.xh.blogs.domain.po.Role
    */
    Role selectMenusById(@Param("id") int id);
}