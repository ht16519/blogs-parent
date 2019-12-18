package com.xh.blogs.api;

import com.xh.blogs.domain.po.Attachs;
import com.xh.blogs.domain.po.Tags;
import com.xh.blogs.domain.vo.TagsVo;
import com.xh.blogs.exception.BusinessException;

import java.util.List;

/**
 * @Name IAttachsService
 * @Description
 * @Author wen
 * @Date 2019-05-26
 */
public interface IAttachsService {

    /**
    * @Name getAllBySort
    * @Description 排序获取所有公告
    * @Author wen
    * @Date 2019/5/26
    * @param 
    * @return List<Attachs>
    */
    List<Attachs> getAllBySort();
    
    /**
    * @Name createAttachsCache
    * @Description 创建排序后的公告缓存（状态为显示的）
    * @Author wen
    * @Date 2019/5/26
    * @param 
    * @return List<Attachs>
    */
    List<Attachs> createAttachsCache();

    /**
    * @Name getById
    * @Description 通过id获取公告内容
    * @Author wen
    * @Date 2019/5/26
    * @param id
    * @return com.xh.blogs.domain.po.Tags
    */
    Attachs getById(int id) throws BusinessException;

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
