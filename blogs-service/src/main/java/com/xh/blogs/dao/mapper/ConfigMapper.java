package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.Config;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigMapper extends IBaseMapper<Config> {

    /**
    * @Name selectByStatus
    * @Description 状态查询
    * @Author wen
    * @Date 2019/6/1
    * @param status
    * @return java.util.List<com.xh.blogs.domain.po.Config>
    */
    List<Config> selectByStatus(@Param("status") int status);
}