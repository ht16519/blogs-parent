package com.xh.blogs.api;

import com.xh.blogs.domain.po.Group;

import java.util.List;

/**
 * @Name IGroupService
 * @Description
 * @Author wen
 * @Date 2019-04-24
 */
public interface IGroupService {

    /**
    * @Name getByShow
    * @Description 查看显示的
    * @Author wen
    * @Date 2019/5/6
    * @param 
    * @return java.util.List<com.xh.blogs.domain.po.Group> 
    */
    List<Group> getByShow();

    /**
    * @Name getAll
    * @Description 获取所有文章类型
    * @Author wen
    * @Date 2019/4/24
    * @param
    * @return java.util.List<com.xh.blogs.domain.po.Group>
    */
    List<Group> getAll();

    /**
    * @Name getById
    * @Description 查询by id
    * @Author wen
    * @Date 2019/5/6
    * @param id
    * @return com.xh.blogs.domain.po.Group 
    */
    Group getById(int id);

    /**
    * @Name save
    * @Description 保存
    * @Author wen
    * @Date 2019/5/6
    * @param group
    * @return int
    */
    int save(Group group);

    /**
    * @Name removeById
    * @Description 删除 by id
    * @Author wen
    * @Date 2019/5/6
    * @param id
    * @return int 
    */
    int deleteById(int id);
}
