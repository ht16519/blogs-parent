package com.xh.blogs.service.impl;

import com.xh.blogs.api.IGroupService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.GroupMapper;
import com.xh.blogs.domain.po.Group;
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
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Group> getByShow() {
        //TODO 全局缓存优化
        Group group = new Group();
        group.setStatus(CommonConst.EFFECTIVE_STATUS);
        return groupMapper.select(group);
    }

    @Override
    public List<Group> getAll() {
        return groupMapper.selectAll();
    }

    @Override
    public Group getById(int id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(Group group) {
        if(group.getId() == null){
            return groupMapper.insertSelective(group);
        }else {
            return groupMapper.updateByPrimaryKeySelective(group);
        }
    }

    @Override
    public int deleteById(int id) {
        return groupMapper.deleteByPrimaryKey(id);
    }
}
