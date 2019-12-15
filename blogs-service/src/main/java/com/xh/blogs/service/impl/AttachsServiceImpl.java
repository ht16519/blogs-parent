package com.xh.blogs.service.impl;

import com.xh.blogs.service.IAttachsService;
import com.xh.blogs.service.ITagsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.dao.mapper.AttachsMapper;
import com.xh.blogs.dao.mapper.TagsMapper;
import com.xh.blogs.domain.po.Attachs;
import com.xh.blogs.domain.po.Tags;
import com.xh.blogs.domain.vo.AttachsVo;
import com.xh.blogs.domain.vo.TagsVo;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name AttachsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-05-26
 */
@Service
@Slf4j
public class AttachsServiceImpl implements IAttachsService {

    @Autowired
    private AttachsMapper attachsMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ServletContext servletContext;

    @Override
    public List<Attachs> getAllBySort() {
        return attachsMapper.selectBySort(new Attachs());
    }

    @Override
    public List<Attachs> createAttachsCache() {
        log.info("================= START创建系统公告缓存 =================");
        //排序获取显示的公告
        List<Attachs> attachsList = attachsMapper.selectBySort(new Attachs(CommonConst.EFFECTIVE_STATUS));
        if(attachsList.size() > 0){
            //1.存入servletContext
            servletContext.setAttribute(KeyConst.SITE_FOOTER_TOPS_KEY, this.getFooterTops(attachsList));
            //2.存入redis
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            ops.set(KeyConst.SITE_ATTACHS_COLLECTION_KEY, JsonUtil.serialize(this.getShowAttachsMap(attachsList)));
        }
        log.info("================= END创建系统公告缓存成功 =================");
        return attachsList;
    }

    @Override
    public Attachs getById(int id) throws BusinessException {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cacheResult = ops.get(KeyConst.SITE_ATTACHS_COLLECTION_KEY);
        Attachs attachs;
        if (StringUtils.isEmpty(cacheResult)) {
            attachs = attachsMapper.selectByPrimaryKey(id);
        } else {
            attachs = JsonUtil.parseMap(cacheResult, Integer.class, Attachs.class).get(id);
        }
        if(attachs == null){
            throw new BusinessException(EmError.SITE_ATTACHS_IS_NOT_EXIST);
        }
        return attachs;
    }

    @Override
    public int up(int id) throws BusinessException {
        return this.setAttachsSort(id, true);
    }

    @Override
    public int down(int id) throws BusinessException {
        return this.setAttachsSort(id, false);
    }

    /**
    * @Name setTagsSort
    * @Description 设置公告排序
    * @Author wen
    * @Date 2019/5/26
    * @param id
    * @param flag true:前移一位， false：后移一位
    * @return int
    */
    @Transactional
    protected int setAttachsSort(int id, boolean flag) throws BusinessException {
        //1.判断输入是否存在
        Attachs targetAttachs = attachsMapper.selectByPrimaryKey(id);
        if(targetAttachs == null){
            throw new BusinessException(EmError.FAIL);
        }
        Attachs beforeAttachs;
        if(flag){
            //2.查询出目标标签的前一位
            beforeAttachs = attachsMapper.selectBeforeBySort(targetAttachs.getSort());
            if(beforeAttachs == null){
                throw new BusinessException(EmError.SITE_ATTACHS_SORT_IS_START);
            }
        }else {
            //2.查询出目标标签的后一位
            beforeAttachs = attachsMapper.selectAfterBySort(targetAttachs.getSort());
            if(beforeAttachs == null){
                throw new BusinessException(EmError.SITE_ATTACHS_SORT_IS_END);
            }
        }
        //3.交换位子
        int sort = targetAttachs.getSort();
        targetAttachs.setSort(beforeAttachs.getSort());
        int res = attachsMapper.updateByPrimaryKeySelective(targetAttachs);
        if(res > 0){
            beforeAttachs.setSort(sort);
            attachsMapper.updateByPrimaryKeySelective(beforeAttachs);
        }
        return res;
    }

    /**
     * @Name getShowAttachsMap
     * @Description 获取显示的公告map
     * @Author wen
     * @Date 2019/6/6
     * @param attachs
     * @return java.util.Map<java.lang.Integer,com.xh.blogs.domain.po.Attachs>
     */
    private Map<Integer, Attachs> getShowAttachsMap(List<Attachs> attachs) {
        Map<Integer, Attachs> attachMap = new HashMap<>();
        for (Attachs attach : attachs) {
            attachMap.put(attach.getId(), attach);
        }
        return attachMap;
    }

    /**
     * @Name getFooterTops
     * @Description 获取底部顶链接
     * @Author wen
     * @Date 2019/6/6
     * @param attachsList
     * @return java.util.List<com.xh.blogs.domain.vo.AttachsVo>
     */
    private List<AttachsVo> getFooterTops(List<Attachs> attachsList) {
        List<AttachsVo> list = new ArrayList<>();
        for (Attachs attachs : attachsList) {
            if(attachs.getIsFooterTop().equals(CommonConst.EFFECTIVE_STATUS)){
                list.add(new AttachsVo(attachs));
            }
        }
        return list;
    }
}
