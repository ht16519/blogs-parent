package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.entity.ERoleMenu;
import com.xh.blogs.domain.po.Menu;

import java.util.List;
import java.util.Set;

public interface MenuMapper extends IBaseMapper<Menu> {

    /**
    * @Name selectMenuMap
    * @Description 查询所有菜单
    * @Author wen
    * @Date 2019/5/4
    * @param
    * @return List
    */
    Set<ERoleMenu> selectAllMenu();
}