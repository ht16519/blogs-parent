package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.entity.EMenuNode;
import com.xh.blogs.domain.entity.ERoleMenu;
import com.xh.blogs.domain.po.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface MenuMapper extends IBaseMapper<Menu> {

    /**
     * @Name checkIds
     * @Description 校验ids
     * @Author wen
     * @Date 2019/5/20
     * @param list
     * @return java.util.List<java.lang.Integer>
     */
    List<Integer> checkIds(List<Integer> list);

    /**
    * @Name selectMenuMap
    * @Description 查询所有菜单4Set
    * @Author wen
    * @Date 2019/5/4
    * @param
    * @return Set
    */
    Set<ERoleMenu> selectAllMenu();

    /**
     * @Name selectMenuMap
     * @Description 查询所有菜单4List
     * @Author wen
     * @Date 2019/5/4
     * @param
     * @return List
     */
    List<ERoleMenu> selectAllMenus();

    /**
    * @Name selectByRoleId
    * @Description 通过角色id查询
    * @Author wen
    * @Date 2019/5/18
    * @param roleId
    * @return java.util.List<com.xh.blogs.domain.po.Menu>
    */
    List<Menu> selectByRoleId(@Param("roleId") int roleId);

    /**
    * @Name selectNodeByRoleId
    * @Description 查询菜单节点通过角色id
    * @Author wen
    * @Date 2019/5/19
    * @param roleId
    * @return java.util.List<com.xh.blogs.domain.entity.EMenuNode>
    */
    List<EMenuNode> selectNodeByRoleId(@Param("roleId") int roleId);

    /**
    * @Name selectNode
    * @Description 查询菜单节点
    * @Author wen
    * @Date 2019/5/21
    * @param
    * @return java.util.List<com.xh.blogs.domain.entity.EMenuNode>
    */
    List<EMenuNode> selectNode();
}