package com.xh.blogs.service.impl;

import com.xh.blogs.service.IGroupService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.dao.mapper.GroupMapper;
import com.xh.blogs.domain.po.Group;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @Name GroupServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-24
 */
@Service
@Slf4j
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ServletContext servletContext;


    @Override
    public List<Group> getByShow() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(KeyConst.BLOGS_GROUP_CACHE_KEY)){
            return JsonUtil.parseList(ops.get(KeyConst.BLOGS_GROUP_CACHE_KEY), Group.class);
        }else {
            List<Group> byShow4Db = this.getByShow4Db();
            ops.set(KeyConst.BLOGS_GROUP_CACHE_KEY, JsonUtil.serialize(byShow4Db));
            return byShow4Db;
        }
    }

    @Override
    public List<Group> getByShow4Db() {
        Group group = new Group();
        group.setStatus(CommonConst.EFFECTIVE_STATUS);
        return groupMapper.select(group);
    }

    @Override
    public List<Group> getShowCache(){
        try {
            return (List<Group>)servletContext.getAttribute(KeyConst.BLOGS_GROUP_KEY);
        } catch (Exception e) {
            log.error("==================== 未创建header分类 ====================");
            return new ArrayList<>();
        }

    }

    @Override
    public void createShowCache(){
        log.info("============ START创建博客header分类缓存 ===========");
        servletContext.setAttribute(KeyConst.BLOGS_GROUP_KEY, this.getByShow4Db());
        log.info("============ END创建博客header分类缓存初始化成功 ===========");
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
