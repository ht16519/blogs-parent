package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.Favors;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavorsMapper extends IBaseMapper<Favors> {

    /**
    * @Name selectByUserIdWithPage
    * @Description 查询收藏的文章by userId'
    * @Author wen
    * @Date 2019/4/28
    * @param userId
    * @return java.util.List<com.xh.blogs.domain.po.Favors>
    */
    List<Favors> selectByUserIdWithPage(@Param("userId") int userId);
}