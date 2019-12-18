package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.service.IFollowsService;
import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.dao.mapper.FollowsMapper;
import com.xh.blogs.dao.mapper.NotifyMapper;
import com.xh.blogs.dao.mapper.UserMapper;
import com.xh.blogs.domain.po.Follows;
import com.xh.blogs.domain.po.Notify;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Name FollowsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Service
public class FollowsServiceImpl extends BaseServiceImpl implements IFollowsService{

    @Autowired
    private FollowsMapper followsMapper;
    @Autowired
    private NotifyMapper notifyMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public int addFollow(int userId, int ownId){
        //1.添加关注
        Follows follows = new Follows();
        follows.setFollowId(ownId);
        follows.setUserId(userId);
        follows.setCreateTime(new Date());
        int res = followsMapper.customInsert(follows);
        if(res > 0){
            //2.用户粉丝+1
            userMapper.addFansByUserId(userId);
            //3.发送通知
            Notify notify = new Notify();
            notify.setCreateTime(new Date());
            notify.setEvent(NotifyConst.EVENT_FOLLOWS);
            notify.setFromId(ownId);
            notify.setToId(userId);
            res = notifyMapper.insertSelective(notify);
        }
        return res;
    }

    @Override
    public int checkIsExistByUserId(int userId, int ownId) {
        Follows follows = new Follows();
        follows.setFollowId(ownId);
        follows.setUserId(userId);
        return followsMapper.select(follows).size();
    }

    @Override
    public PageResult<Follows> getFansByUserIdWithPage(int userId, int number) {
        Page<Follows> page = PageHelper.startPage(number, pageSize);
        followsMapper.selectFansByUserId(userId);
        return PageUtil.create(page);
    }

    @Override
    public PageResult<Follows> getFollowsByUserIdWithPage(int userId, int number) {
        Page<Follows> page = PageHelper.startPage(number, pageSize);
        followsMapper.selectFollowsByUserId(userId);
        return PageUtil.create(page);
    }

    @Override
    @Transactional
    public int unfollow(int userId, int followId) {
        //1.取消关注
        Follows follows = new Follows();
        follows.setUserId(userId);
        follows.setFollowId(followId);
        int res = followsMapper.delete(follows);
        //2.用户粉丝-1
        if(res > 0){
            userMapper.minusFansByUserId(userId);
        }
        return res;
    }
}
