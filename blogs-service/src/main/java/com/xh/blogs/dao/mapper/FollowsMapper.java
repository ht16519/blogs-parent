package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.Follows;

public interface FollowsMapper extends IBaseMapper<Follows> {

    /**
    * @Name customInsert
    * @Description 自定义新增操作
    * @Author wen
    * @Date 2019/4/25
    * @param follows
    * @return int
    */
    int customInsert(Follows follows);
}