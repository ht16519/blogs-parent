package com.xh.blogs.service.impl;

import com.xh.blogs.service.IFriendLinkService;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.dao.mapper.FriendLinkMapper;
import com.xh.blogs.domain.po.FriendLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class FriendLinkServiceImpl implements IFriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ServletContext servletContext;


//    @Override
//    public List<FriendLink> getByShow() {
//        ValueOperations<String, String> ops = redisTemplate.opsForValue();
//        if(redisTemplate.hasKey(KeyConst.BLOGS_GROUP_CACHE_KEY)){
//            return JsonUtil.parseList(ops.get(KeyConst.BLOGS_GROUP_CACHE_KEY), Group.class);
//        }else {
//            List<FriendLink> byShow4Db = this.getByShow4Db();
//            ops.set(KeyConst.BLOGS_GROUP_CACHE_KEY, JsonUtil.serialize(byShow4Db));
//            return byShow4Db;
//        }
//    }

    @Override
    public List<FriendLink> getByShow4Db() {
        return friendLinkMapper.selectAll();
    }

    @Override
    public List<FriendLink> getShowCache(){
        try {
            return (List<FriendLink>)servletContext.getAttribute(KeyConst.BLOGS_LINK_KEY);
        } catch (Exception e) {
            log.error("==================== 未创建LINK友情链接分类 ====================");
            return new ArrayList<>();
        }

    }

    @Override
    public void updateShowCache(){
        log.info("============ START创建博客友情链接缓存 ===========");
        servletContext.setAttribute(KeyConst.BLOGS_LINK_KEY, this.getByShow4Db());
        log.info("============ END创建博客友情链接缓存初始化成功 ===========");
    }

    @Override
    public List<FriendLink> getAll() {
        return friendLinkMapper.selectAll();
    }

    @Override
    public FriendLink getById(long id) {
        return friendLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(FriendLink group) {
        if(group.getId() == null){
            return friendLinkMapper.insertSelective(group);
        }else {
            return friendLinkMapper.updateByPrimaryKeySelective(group);
        }
    }

    @Override
    public int deleteById(long id) {
        return friendLinkMapper.deleteByPrimaryKey(id);
    }
}
