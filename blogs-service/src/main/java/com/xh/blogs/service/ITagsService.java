package com.xh.blogs.service;

import com.xh.blogs.domain.po.Tags;
import com.xh.blogs.domain.vo.TagsVo;
import com.xh.blogs.exception.BusinessException;

import java.util.List;

/**
 * @Name ITagsService
 * @Description
 * @Author wen
 * @Date 2019-05-26
 */
public interface ITagsService {

    /**
    * @Name getAllBySort
    * @Description 排序获取所有标签
    * @Author wen
    * @Date 2019/5/26
    * @param 
    * @return com.xh.blogs.domain.po.Tags 
    */
    List<Tags> getAllBySort();
    
    /**
    * @Name createAllTagsCache
    * @Description 创建排序后的标签缓存（状态为显示的）
    * @Author wen
    * @Date 2019/5/26
    * @param 
    * @return void 
    */
    List<Tags> createBlogsTagsCache();

    /**
    * @Name getBlogsTagsCache
    * @Description 获取博客的标签
    * @Author wen
    * @Date 2019/5/26
    * @param
    * @return java.util.List<com.xh.blogs.domain.po.Tags>
    */
    List<Tags> getBlogsTagsCache();

    /**
    * @Name getById
    * @Description 通过id获取标签
    * @Author wen
    * @Date 2019/5/26
    * @param id
    * @return com.xh.blogs.domain.po.Tags
    */
    Tags getById(int id);

    /**
    * @Name save
    * @Description 保存标签
    * @Author wen
    * @Date 2019/5/26
    * @param tagsVo
    * @param id
    * @return int
    */
    int save(TagsVo tagsVo, int id) throws BusinessException;

    /**
    * @Name up
    * @Description 排序向前移动一位
    * @Author wen
    * @Date 2019/5/26
    * @param id
    * @return int
    */
    int up(int id) throws BusinessException;

    /**
    * @Name down
    * @Description 排序向后移动一位
    * @Author wen
    * @Date 2019/5/26
    * @param id
    * @return int
    */
    int down(int id) throws BusinessException;
}
