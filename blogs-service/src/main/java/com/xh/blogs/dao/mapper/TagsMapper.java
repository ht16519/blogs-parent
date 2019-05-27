package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.Tags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagsMapper extends IBaseMapper<Tags> {
    
    /**
    * @Name getAllBySort
    * @Description 排序查询所有
    * @Author wen
    * @Date 2019/5/26
    * @param tags
    * @return List<Tags>
    */
    List<Tags> selectAllBySort(Tags tags);

    /**
    * @Name selectBeforeBySort
    * @Description 查询出标签的前一位
    * @Author wen
    * @Date 2019/5/27
    * @param sort
    * @return com.xh.blogs.domain.po.Tags
    */
    Tags selectBeforeBySort(@Param("sort") int sort);

    /**
    * @Name selectAfterBySort
    * @Description 查询出标签的后一位
    * @Author wen
    * @Date 2019/5/27
    * @param sort
    * @return com.xh.blogs.domain.po.Tags
    */
    Tags selectAfterBySort(@Param("sort") int sort);
}