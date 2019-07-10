package com.xh.blogs.utils;

import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.dao.mapper.NotifyMapper;
import com.xh.blogs.domain.po.Notify;

import java.util.Date;

/**
 * @Name NotifyUtil
 * @Description
 * @Author wen
 * @Date 2019-04-30
 */
public class NotifyUtil {

    /**
    * @Name build
    * @Description 构建Notify实体
    * @Author wen
    * @Date 2019/7/10
    * @param event
    * @param articleId
    * @param fromId
    * @param toId
    * @return com.xh.blogs.domain.po.Notify
    */
    public static Notify build(int event, int articleId, int fromId, int toId){
        Notify notify = new Notify();
        notify.setCreateTime(new Date());
        notify.setEvent(event);
        notify.setArticleId(articleId);
        notify.setFromId(fromId);
        notify.setToId(toId);
        return notify;
    }

    /**
     * @Name sendNotify
     * @Description 发送站内信
     * @Author wen
     * @Date 2019/7/10
     * @param notifyMapper
     * @param event
     * @param fromId
     * @param toId
     * @return void
     */
    public static void sendNotify(NotifyMapper notifyMapper, int event, int fromId, int toId){
        Notify notify = new Notify();
        notify.setCreateTime(new Date());
        notify.setEvent(event);
        notify.setFromId(fromId);
        notify.setToId(toId);
        notifyMapper.insertSelective(notify);
    }
}
