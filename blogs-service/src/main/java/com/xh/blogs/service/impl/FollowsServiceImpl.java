package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.api.IFollowsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.FollowsMapper;
import com.xh.blogs.dao.mapper.UserMapper;
import com.xh.blogs.domain.po.Follows;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Name FollowsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Service
public class FollowsServiceImpl implements IFollowsService{

    @Autowired
    private FollowsMapper followsMapper;
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
        //2.用户粉丝+1
        if(res > 0){
            userMapper.addFansByUserId(userId);
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
        Page<Follows> page = PageHelper.startPage(number, CommonConst.PAGE_SIZE);
        followsMapper.selectFansByUserId(userId);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult<Follows> getFollowsByUserIdWithPage(int userId, int number) {
        Page<Follows> page = PageHelper.startPage(number, CommonConst.PAGE_SIZE);
        followsMapper.selectFollowsByUserId(userId);
        return new PageResult(page.getTotal(), page.getResult());
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
