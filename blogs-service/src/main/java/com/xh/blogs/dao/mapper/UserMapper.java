package com.xh.blogs.dao.mapper;

import com.xh.blogs.dao.base.IBaseMapper;
import com.xh.blogs.domain.po.User;
import org.apache.ibatis.annotations.Param;

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
}