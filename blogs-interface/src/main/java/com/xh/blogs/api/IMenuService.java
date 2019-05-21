package com.xh.blogs.api;

import com.xh.blogs.domain.entity.EMenuNode;
import com.xh.blogs.domain.entity.ERoleMenu;
import com.xh.blogs.domain.po.Menu;
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
    * @Description 获取所有菜单4Set
    * @Author wen
    * @Date 2019/5/4
    * @param 
    * @return List
    */
    Set<ERoleMenu> getAllMenu();

    /**
    * @Name getAllMenus
    * @Description 获取所有菜单4List
    * @Author wen
    * @Date 2019/5/18
    * @param
    * @return java.util.List<com.xh.blogs.domain.entity.ERoleMenu>
    */
    List<ERoleMenu> getAllMenus();

    /**
    * @Name getRoleMenuTreeCache
    * @Description 获取基于角色的菜单关系树缓存
    * @Author wen
    * @Date 2019/5/16
    * @param 
    * @return java.util.Map<java.lang.Integer,com.xh.blogs.domain.entity.ERoleMenu> 
    */
    Map<Integer, ERoleMenu> getRoleMenuTreeCache();

    /**
    * @Name createRoleMenuTreeCache
    * @Description 创建基于角色的菜单关系树缓存
    * @Author wen
    * @Date 2019/5/16
    * @param 
    * @return java.util.Map<java.lang.Integer,com.xh.blogs.domain.entity.ERoleMenu> 
    */
    Map<Integer, ERoleMenu> createRoleMenuTreeCache();

    /**
    * @Name buildRoleMenuTree
    * @Description 组建基于角色的菜单的关系树
    * @Author wen
    * @Date 2019/5/16
    * @param roleMenus
    * @return com.xh.blogs.domain.entity.ERoleMenu
    */
    ERoleMenu buildRoleMenuTree(Set<ERoleMenu> roleMenus);

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

    /**
    * @Name getById
    * @Description 通过id查询
    * @Author wen
    * @Date 2019/5/18
    * @param id
    * @return com.xh.blogs.domain.po.Menu
    */
    Menu getById(int id);

    /**
    * @Name getByRoleId
    * @Description 通过角色id查询
    * @Author wen
    * @Date 2019/5/18
    * @param id
    * @return java.util.List<com.xh.blogs.domain.po.Menu>
    */
    List<Menu> getByRoleId(int id);

    /**
    * @Name getNodeByRoleId
    * @Description 获取菜单节点 by 角色id
    * @Author wen
    * @Date 2019/5/19
    * @param roleId
    * @return java.util.List<com.xh.blogs.domain.entity.EMenuNode>
    */
    List<EMenuNode> getNodeByRoleId(int roleId);

    /**
    * @Name getNode
    * @Description 获取菜单节点
    * @Author wen
    * @Date 2019/5/21
    * @param 
    * @return java.util.List<com.xh.blogs.domain.entity.EMenuNode> 
    */
    List<EMenuNode> getNode();
}
