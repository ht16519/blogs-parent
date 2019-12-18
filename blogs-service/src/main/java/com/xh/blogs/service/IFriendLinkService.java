package com.xh.blogs.service;

import com.xh.blogs.domain.po.FriendLink;

import java.util.List;

/**
 * @Name IGroupService
 * @Description
 * @Author wen
 * @Date 2019-04-24
 */
public interface IFriendLinkService {

    /**
    * @Name getByShow
    * @Description 查看显示的博客分类组
    * @Author wen
    * @Date 2019/5/6
    * @param 
    * @return java.util.List<com.xh.blogs.domain.po.Group> 
    */
//    List<FriendLink> getByShow();

    /**
    * @Name getByShow4Db
    * @Description 从数据看获取博客分类组
    * @Author wen
    * @Date 2019/5/12
    * @param
    * @return java.util.List<com.xh.blogs.domain.po.Group>
    */
    List<FriendLink> getByShow4Db();

    /**
    * @Name getShowCache
    * @Description 获取缓存中的分类header
    * @Author wen
    * @Date 2019/5/30
    * @param 
    * @return java.util.List<com.xh.blogs.domain.po.FriendLink>
    */
    List<FriendLink> getShowCache();

    /**
    * @Name 创建博客类型分组缓存
    * @Description
    * @Author wen
    * @Date 2019/5/12
    * @param
    * @return void
    */
    void updateShowCache();

    /**
    * @Name getAll
    * @Description 获取所有文章类型
    * @Author wen
    * @Date 2019/4/24
    * @param
    * @return java.util.List<com.xh.blogs.domain.po.FriendLink>
    */
    List<FriendLink> getAll();

    /**
    * @Name getById
    * @Description 查询by id
    * @Author wen
    * @Date 2019/5/6
    * @param id
    * @return com.xh.blogs.domain.po.FriendLink
    */
    FriendLink getById(long id);

    /**
    * @Name save
    * @Description 保存
    * @Author wen
    * @Date 2019/5/6
    * @param friendLink
    * @return int
    */
    int save(FriendLink friendLink);

    /**
    * @Name removeById
    * @Description 删除 by id
    * @Author wen
    * @Date 2019/5/6
    * @param id
    * @return int 
    */
    int deleteById(long id);
}
