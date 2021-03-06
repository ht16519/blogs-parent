package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.entity.EHotUser;
import com.xh.blogs.domain.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends IBaseMapper<User> {

    /**
    * @Name selectUserInfoByName
    * @Description 连表检索用户+角色+权限by userName
    * @Author wen
    * @Date 2019/4/23
    * @param userName
    * @return com.xh.blogs.domain.po.User
    */
    User selectUserInfoByName(@Param("userName") String userName);

    /**
    * @Name selectByHottest
    * @Description 查询最热的博主
    * @Author wen
    * @Date 2019/4/25
    * @param count
    * @return java.util.List<com.xh.blogs.domain.entity.EHotUser>
    */
    List<EHotUser> selectByHottest(@Param("count") Integer count);

    /**
    * @Name addFollow
    * @Description 用户粉丝+1
    * @Author wen
    * @Date 2019/4/27
    * @param
    * @return int
    */
    int addFansByUserId(@Param("userId") int userId);

    /**
    * @Name minusFansByUserId
    * @Description 用户粉丝-1
    * @Author wen
    * @Date 2019/4/28
    * @param userId
    * @return int
    */
    int minusFansByUserId(@Param("userId") int userId);

    /**
    * @Name updatePostsById
    * @Description 用户发布文章数 +1
    * @Author wen
    * @Date 2019/5/10
    * @param authorId
    * @return int
    */
    int updatePostsById(@Param("authorId") int authorId);

    /**
    * @Name addCommentsById
    * @Description 用户评论数 +1
    * @Author wen
    * @Date 2019/5/10
    * @param authorId
    * @return int
    */
    int addCommentsById(@Param("authorId") int authorId);

    /**
    * @Name reducePosts
    * @Description 用户文章发布数量 -1
    * @Author wen
    * @Date 2019/6/5
    * @param userId
    * @return int
    */
    int reducePosts(@Param("userId") int userId);

    /**
    * @Name selectUserInfoByOpenId
    * @Description 条件查询用户登录信息
    * @Author wen
    * @Date 2019/7/10
    * @param condition
    * @return com.xh.blogs.domain.po.User
    */
    User selectUserInfoByOpenId(Map<String, String> condition);

    /**
    * @Name updateByQQOpenId
    * @Description 通过QQopenId修改
    * @Author wen
    * @Date 2019/7/19
    * @param user
    * @return int
    */
    int updateByQQOpenId(User user);

    /**
    * @Name selectPasswordByUsername
    * @Description 通过用户名查询密码
    * @Author wen
    * @Date 2019/7/19
    * @param userName
    * @return java.lang.String
    */
    String selectPasswordByUsername(@Param("userName") String userName);

    /**
    * @Name selectByUserName
    * @Description 查询所有信息用户名
    * @Author wen
    * @Date 2019/7/19
    * @param userName
    * @return com.xh.blogs.domain.po.User
    */
    User selectByUserName(@Param("userName") String userName);
}