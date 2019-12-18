package com.xh.blogs.service;

import com.xh.blogs.domain.po.Follows;
import com.xh.blogs.domain.vo.PageResult;

import java.util.List;

/**
 * @Name IFollowsService
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
public interface IFollowsService {

    /**
    * @Name add
    * @Description 添加关注
    * @Author wen
    * @Date 2019/4/25
    * @param userId
    * @param ownId
    * @return int
    */
    int addFollow(int userId, int ownId);

    /**
    * @Name checkIsExistByUserId
    * @Description 检查是否已关注
    * @Author wen
    * @Date 2019/4/25
    * @param userId
    * @return int
    */
    int checkIsExistByUserId(int userId, int ownId);

    /**
    * @Name getFansByUserIdWithPage
    * @Description 获取粉丝
    * @Author wen
    * @Date 2019/4/28
    * @param userId
    * @param number
    * @return PageResult
    */
    PageResult<Follows> getFansByUserIdWithPage(int userId, int number);

    /**
     * @Name getFansByUserIdWithPage
     * @Description 获取关注
     * @Author wen
     * @Date 2019/4/28
     * @param userId
     * @param number
     * @return PageResult
     */
    PageResult<Follows> getFollowsByUserIdWithPage(int userId, int number);

    /**
    * @Name unfollow
    * @Description 取消关注
    * @Author wen
    * @Date 2019/4/28
    * @param followId
    * @param userId
    * @return int
    */
    int unfollow(int userId, int followId);
}
