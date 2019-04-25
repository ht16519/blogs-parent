package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.Notify;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NotifyMapper extends IBaseMapper<Notify> {

    /**
    * @Name selectUnreadMessageCountByUserId
    * @Description 获取用户的未读通知数量
    * @Author wen
    * @Date 2019/4/25
    * @param userId
    * @return int
    */
    int selectUnreadCountByUserId(@Param("userId") int userId);

    /**
    * @Name selectByCondition
    * @Description 条件检索
    * @Author wen
    * @Date 2019/4/25
    * @param parameters
    * @return java.util.List<com.xh.blogs.domain.po.Notify>
    */
    List<Notify> selectByCondition(Map<String, Object> parameters);

    /**
    * @Name updateStatusByUserId
    * @Description 设置消息为已读
    * @Author wen
    * @Date 2019/4/25
    * @param
    * @param userId
     * @return int
    */
    int updateStatusByUserId(@Param("userId") int userId);
}