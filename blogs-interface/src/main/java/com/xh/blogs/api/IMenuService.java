package com.xh.blogs.api;

import com.xh.blogs.domain.entity.ERoleMenu;
import com.xh.blogs.domain.po.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Name IMenuService
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
public interface IMenuService {

    /**
    * @Name getMenuMap
    * @Description 获取所有菜单
    * @Author wen
    * @Date 2019/5/4
    * @param 
    * @return List
    */
    Set<ERoleMenu> getAllMenu();

    /**
    * @Name createMenuCache
    * @Description 创建菜单缓存
    * @Author wen
    * @Date 2019/5/4
    * @param
    */
    Map<Integer, Set<ERoleMenu>> createRoleMenuCache();

    /**
    * @Name getRoleMenu
    * @Description 获取角色菜单
    * @Author wen
    * @Date 2019/5/6
    * @param
    * @return java.util.Map<java.lang.Integer,java.util.List<com.xh.blogs.domain.entity.ERoleMenu>>
    */
    Map<Integer, Set<ERoleMenu>> getRoleMenu();

    /**
    * @Name createUserMenuCache
    * @Description 创建用户菜单缓存
    * @Author wen
    * @Date 2019/5/6
    * @param user
    * @return void 
    */
    Set<ERoleMenu> getUserMenuCache(User user);
}
