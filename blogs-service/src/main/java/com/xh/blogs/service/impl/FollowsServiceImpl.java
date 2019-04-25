package com.xh.blogs.service.impl;

import com.xh.blogs.api.IFollowsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.FollowsMapper;
import com.xh.blogs.domain.po.Follows;
import com.xh.blogs.exception.BusinessException;
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
public class FollowsServiceImpl implements IFollowsService{

    @Autowired
    private FollowsMapper followsMapper;

    @Override
    @Transactional
    public int addFollow(Integer uid, Integer ownId){
        Follows follows = new Follows();
        follows.setFollowId(ownId);
        follows.setUserId(uid);
        return followsMapper.customInsert(follows);
    }

    @Override
    public int checkIsExistByUserId(Integer uid, Integer ownId) {
        Follows follows = new Follows();
        follows.setFollowId(ownId);
        follows.setUserId(uid);
        return followsMapper.select(follows).size();
    }
}
