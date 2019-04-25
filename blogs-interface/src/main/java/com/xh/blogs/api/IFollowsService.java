package com.xh.blogs.api;

import com.xh.blogs.exception.BusinessException;

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
    * @param uid
    * @param ownId
    * @return int
    */
    int addFollow(Integer uid, Integer ownId);

    /**
    * @Name checkIsExistByUserId
    * @Description 检查是否已关注
    * @Author wen
    * @Date 2019/4/25
    * @param uid
    * @return int
    */
    int checkIsExistByUserId(Integer uid, Integer ownId);
}
