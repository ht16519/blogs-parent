package com.xh.blogs.service;

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
    * @Name getAll
    * @Description 获取所有文章类型
    * @Author wen
    * @Date 2019/4/24
    * @param
    * @return java.util.List<com.xh.blogs.domain.po.Group>
    */
    List<Group> getAll();
}
