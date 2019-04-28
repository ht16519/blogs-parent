package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.Follows;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
    * @Name selectFansByUserId
    * @Description 根据用户获取粉丝
    * @Author wen
    * @Date 2019/4/28
    * @param userId
    * @return java.util.List<com.xh.blogs.domain.po.Follows>
    */
    List<Follows> selectFansByUserId(@Param("userId") int userId);

    /**
    * @Name selectFollowsByUserId
    * @Description 根据用户获取关注
    * @Author wen
    * @Date 2019/4/28
    * @param userId
    * @return java.util.List<com.xh.blogs.domain.po.Follows>
    */
    List<Follows> selectFollowsByUserId(@Param("userId") int userId);
}