package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.service.INotifyService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.NotifyMapper;
import com.xh.blogs.domain.po.Notify;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name NotifyServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Service
public class NotifyServiceImpl extends BaseServiceImpl implements INotifyService {

    @Autowired
    private NotifyMapper notifyMapper;

    @Override
    public int sendMsg(Integer fromId, Integer toId, Integer event, Integer articleId) {
        Notify notify = new Notify();
        notify.setArticleId(articleId);
        notify.setCreateTime(new Date());
        notify.setEvent(event);
        notify.setToId(toId);
        notify.setFromId(fromId);
        return notifyMapper.insertSelective(notify);
    }

    @Override
    public int getUnreadCountByUserId(Integer userId) {
        return notifyMapper.selectUnreadCountByUserId(userId);
    }

    @Override
    public PageResult<Notify> getByUserIdWithPage(Integer userId, int number) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(CommonConst.USER_ID_KEY, userId);
        Page<Notify> page = PageHelper.startPage(number, pageSize);
        notifyMapper.selectByCondition(parameters);
        return PageUtil.create(page);
    }

    @Override
    public int setStatusByUserId(int userId) {
        return notifyMapper.updateStatusByUserId(userId);
    }
}
