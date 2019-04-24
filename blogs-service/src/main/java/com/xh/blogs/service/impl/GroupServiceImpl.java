package com.xh.blogs.service.impl;

import com.xh.blogs.dao.mapper.GroupMapper;
import com.xh.blogs.domain.po.Group;
import com.xh.blogs.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name GroupServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-24
 */
@Service
public class GroupServiceImpl implements IGroupService{

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Group> getAll() {
        //TODO 全局缓存优化
        return groupMapper.selectAll();
    }
}
