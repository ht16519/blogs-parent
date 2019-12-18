package com.xh.blogs.service.impl;

import com.xh.blogs.service.ITagsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.dao.mapper.TagsMapper;
import com.xh.blogs.domain.po.Tags;
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

import java.util.List;

/**
 * @Name TagsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-05-26
 */
@Service
@Slf4j
public class TagsServiceImpl implements ITagsService {

    @Autowired
    private TagsMapper tagsMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Tags> getAllBySort() {
        return tagsMapper.selectAllBySort(new Tags());
    }

    @Override
    public List<Tags> createBlogsTagsCache() {
        log.info("================= START创建博客标签缓存 =================");
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        List<Tags> tags = tagsMapper.selectAllBySort(new Tags(CommonConst.EFFECTIVE_STATUS));
        ops.set(KeyConst.BLOGS_TAGS_CACHE_KEY, JsonUtil.serialize(tags));
        log.info("================= END创建博客标签缓存成功 =================");
        return tags;
    }

    @Override
    public List<Tags> getBlogsTagsCache() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cacheResult = ops.get(KeyConst.BLOGS_TAGS_CACHE_KEY);
        if (StringUtils.isEmpty(cacheResult)) {
            return this.createBlogsTagsCache();
        } else {
            return JsonUtil.parseList(cacheResult, Tags.class);
        }
    }

    @Override
    public Tags getById(int id) {
        return tagsMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int save(TagsVo tagsVo, int id) throws BusinessException {
        //1.参数校验
        BeanValidator.check(tagsVo);
        int res;
        Tags tags = new Tags();
        BeanUtils.copyProperties(tagsVo, tags);
        if (tagsVo.getId() == null) {
            //新增
            //2.查重校验
            this.checkRepeat(tagsVo, null);
            res = tagsMapper.insertSelective(tags);
        } else {
            //修改
            Tags tags1 = tagsMapper.selectByPrimaryKey(tagsVo.getId());
            //2.查重校验
            this.checkRepeat(tagsVo, tags1);
            res = tagsMapper.updateByPrimaryKeySelective(tags);
        }
        return res;
    }

    @Override
    public int up(int id) throws BusinessException {
        return this.setTagsSort(id, true);
    }

    @Override
    public int down(int id) throws BusinessException {
        return this.setTagsSort(id, false);
    }

    /**
     * @Name checkRepeat
     * @Description 查重验证
     * @Author wen
     * @Date 2019/5/27
     * @param tagsVo
     * @return void
     */
    private void checkRepeat(TagsVo tagsVo, Tags dbTags) throws BusinessException {
        //1.判断名称是否已存在
        Tags tags = new Tags();
        tags.setName(tagsVo.getName());
        Tags dbTags1 = tagsMapper.selectOne(tags);
        if(dbTags == null){
            if(dbTags1 != null){
                throw new BusinessException(EmError.ARTICLE_TAGS_NAME_IS_EXIST);
            }
        }else {
            if(dbTags1 != null && !dbTags1.getName().equals(dbTags.getName())){
                throw new BusinessException(EmError.ARTICLE_TAGS_NAME_IS_EXIST);
            }
        }
        //2.判断排序是否已存在
        tags = new Tags();
        tags.setSort(tagsVo.getSort());
        Tags dbTags2 = tagsMapper.selectOne(tags);
        if(dbTags == null){
            if(dbTags2 != null){
                throw new BusinessException(EmError.ARTICLE_TAGS_SORT_IS_EXIST);
            }
        }else {
            if(dbTags2 != null && !dbTags2.getSort().equals(dbTags.getSort())){
                throw new BusinessException(EmError.ARTICLE_TAGS_SORT_IS_EXIST);
            }
        }
    }

    /**
    * @Name setTagsSort
    * @Description 设置标签排序
    * @Author wen
    * @Date 2019/5/26
    * @param id
    * @param flag true:前移一位， false：后移一位
    * @return int
    */
    @Transactional
    protected int setTagsSort(int id, boolean flag) throws BusinessException {
        //1.判断输入是否存在
        Tags targetTags = tagsMapper.selectByPrimaryKey(id);
        if(targetTags == null){
            throw new BusinessException(EmError.FAIL);
        }
        Tags beforeTags;
        if(flag){
            //2.查询出目标标签的前一位
            beforeTags = tagsMapper.selectBeforeBySort(targetTags.getSort());
            if(beforeTags == null){
                throw new BusinessException(EmError.ARTICLE_TAGS_SORT_IS_START);
            }
        }else {
            //2.查询出目标标签的后一位
            beforeTags = tagsMapper.selectAfterBySort(targetTags.getSort());
            if(beforeTags == null){
                throw new BusinessException(EmError.ARTICLE_TAGS_SORT_IS_END);
            }
        }
        //3.交换位子
        int sort = targetTags.getSort();
        targetTags.setSort(beforeTags.getSort());
        int res = tagsMapper.updateByPrimaryKeySelective(targetTags);
        if(res > 0){
            beforeTags.setSort(sort);
            tagsMapper.updateByPrimaryKeySelective(beforeTags);
        }
        return res;
    }
}
