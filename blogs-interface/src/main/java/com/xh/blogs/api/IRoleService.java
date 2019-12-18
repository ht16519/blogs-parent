package com.xh.blogs.api;

import com.xh.blogs.domain.po.Role;
import com.xh.blogs.domain.vo.RoleVo;
import com.xh.blogs.exception.BusinessException;

import java.util.List;

/**
 * @Name IRoleService
 * @Description
 * @Author wen
 * @Date 2019-05-18
 */
public interface IRoleService {

    /**
    * @Name getAll
    * @Description 获取所有角色
    * @Author wen
    * @Date 2019/5/18
    * @param 
    * @return java.util.List<com.xh.blogs.domain.po.Role> 
    */
    List<Role> getAll();

    /**
    * @Name getById
    * @Description 查询by id
    * @Author wen
    * @Date 2019/5/19
    * @param id
    * @return com.xh.blogs.domain.po.Role 
    */
    Role getById(int id);

    /**
    * @Name save
    * @Description 修改角色及权限
    * @Author wen
    * @Date 2019/5/20
    * @param roleVo
    */
    int update(RoleVo roleVo) throws BusinessException;

    /**
    * @Name add
    * @Description 新增角色及权限
    * @Author wen
    * @Date 2019/5/21
    * @param roleVo
    * @return int
    */
    int add(RoleVo roleVo) throws BusinessException;

    /**
    * @Name delete
    * @Description 删除角色菜单权限关系
    * @Author wen
    * @Date 2019/5/21
    * @param id
    * @return int 
    */
    int delete(int id);
}
