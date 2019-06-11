package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.Attachs;
import com.xh.blogs.domain.po.Tags;

import java.util.List;

public interface AttachsMapper extends IBaseMapper<Attachs> {
    
    /**
    * @Name selectBeforeBySort
    * @Description 查询当前排序的前一位
    * @Author wen
    * @Date 2019/6/6
    * @param sort
    * @return com.xh.blogs.domain.po.Attachs
    */
    Attachs selectBeforeBySort(int sort);

    /**
    * @Name selectAfterBySort
    * @Description 查询当前排序的后一位
    * @Author wen
    * @Date 2019/6/6
    * @param sort
    * @return com.xh.blogs.domain.po.Attachs
    */
    Attachs selectAfterBySort(int sort);

    /**
    * @Name selectShowBySort
    * @Description 查询显示的by sort
    * @Author wen
    * @Date 2019/6/6
    * @param attachs
    * @return java.util.List<com.xh.blogs.domain.po.Attachs>
    */
    List<Attachs> selectBySort(Attachs attachs);
}