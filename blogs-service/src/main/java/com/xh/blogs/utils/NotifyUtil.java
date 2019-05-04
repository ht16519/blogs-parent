package com.xh.blogs.utils;

import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.domain.po.Notify;

import java.util.Date;

/**
 * @Name NotifyUtil
 * @Description
 * @Author wen
 * @Date 2019-04-30
 */
public class NotifyUtil {

    public static Notify build(int event, int articleId, int fromId, int toId){
        Notify notify = new Notify();
        notify.setCreateTime(new Date());
        notify.setEvent(event);
        notify.setArticleId(articleId);
        notify.setFromId(fromId);
        notify.setToId(toId);
        return notify;
    }
}
