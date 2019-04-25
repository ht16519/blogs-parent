package com.xh.blogs.api;

import com.xh.blogs.domain.po.Notify;
import com.xh.blogs.domain.vo.PageResult;

/**
 * @Name INotifyService
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
public interface INotifyService {

    /**
    * @Name sendMsg
    * @Description 发送通知消息
    * @Author wen
    * @Date 2019/4/25
    * @param fromId 发送人id（可空）
    * @param toId 接收人id（必填）
    * @param event  事件id（必填）
    * @param articleId 所属文章id（可空）
    * @return int
    */
    int sendMsg(Integer fromId, Integer toId, Integer event, Integer articleId);

    /**
    * @Name getUnreadMessageCount
    * @Description 获取用户的未读通知数量
    * @Author wen
    * @Date 2019/4/25
    * @param userId
    * @return int 
    */
    int getUnreadCountByUserId(Integer userId);

    /**
    * @Name getByUserIdWithPage
    * @Description 分页查询通知by userId
    * @Author wen
    * @Date 2019/4/25
    * @param userId
    * @param number
    * @return com.xh.blogs.domain.vo.PageResult<com.xh.blogs.domain.po.Notify>
    */
    PageResult<Notify> getByUserIdWithPage(Integer userId, int number);

    /**
    * @Name updateStatusByUserId
    * @Description 设置消息为已读
    * @Author wen
    * @Date 2019/4/25
    * @param userId
    * @return int
    */
    int setStatusByUserId(int userId);
}
