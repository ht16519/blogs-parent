package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.api.IArticleService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.dao.mapper.ArticleAccessoryMapper;
import com.xh.blogs.dao.mapper.ArticleContentMapper;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.dao.mapper.UserMapper;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.po.ArticleAccessory;
import com.xh.blogs.domain.po.ArticleContent;
import com.xh.blogs.domain.vo.ArticleVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @Name UserServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@Service
@Slf4j
public class ArticleServiceImpl extends BaseServiceImpl implements IArticleService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleAccessoryMapper articleAccessoryMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${blogs.cutOutArticleSummaryIndex}")
    private int cutOutArticleSummaryIndex;

    @Override
    public PageResult<Article> getInfoWithPage(int sort, int number) {
        Map<String, Object> parameters = new HashMap<>();
        return this.getCommonWithPage(sort, number, parameters);
    }

    @Override
    public PageResult<Article> getByIdWithPage(int userId, int number) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(CommonConst.USER_ID_KEY, userId);
        return this.getCommonWithPage(CommonConst.ARTICLE_ORDER_NEWSET, number, parameters);
    }

    @Override
    @Transactional
    public Article addArticle(ArticleVo articleVo) throws BusinessException {
        //1.参数校验
        BeanValidator.check(articleVo);
        //2.保存文章基本信息
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);
        String voContent = articleVo.getContent();
        article.setSummary(PreviewTextUtil.getText(voContent, cutOutArticleSummaryIndex));
        article.setCreateTime(new Date());
        int res = articleMapper.insertSelective(article);
        //3.保存文章内容信息
        Document doc = Jsoup.parse(voContent);
        ArticleContent content = new ArticleContent();
        content.setContent(doc.html());
        content.setId(article.getId());
        if(res > 0){
            res = articleContentMapper.insertSelective(content);
            if(res > 0){
                //4.用户发布文章数 +1
                userMapper.updatePostsById(article.getAuthorId());
                //5.保存文章附件信息
                List<ArticleAccessory> articleAccessories = this.extractImages(doc, article.getId());
                if(articleAccessories.size() > 0){
                    res = articleAccessoryMapper.insertList(articleAccessories);
                }
            }
        }
        //TODO 文章信息加入图片数和最后一张图片id
        return article;
    }

    @Override
    public Article getByUserId(int id, int userId) throws BusinessException {
        //1.校验文章属性
        this.checkArticleInfo(id, userId);
        //2.获取文章信息
        return articleMapper.selectByUserId(id, userId);
    }

    @Override
    public Article getById(int id){
        return articleMapper.selectById(id);
    }

    @Override
    public int updateFavors(int articleId) {
        return articleMapper.addFavors(articleId);
    }

    @Override
    public PageResult<Article> getByConditionWithPage(String title, Integer number) {
        if(number == null){
            number = 1;
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(CommonConst.ARTICLE_TITLE_KEY, title);
        parameters.put(CommonConst.STATUS_KEY, CommonConst.EFFECTIVE_STATUS);
        parameters.put(CommonConst.ORDER_BY_KEY, CommonConst.ARTICLE_ORDER_NEWSET);
        Page<Article> page = PageHelper.startPage(number, pageSize);
        articleMapper.selectInfoWithPage(parameters);
        return PageUtil.create(page);
    }

    @Override
    public int removeById(int id, int userId) throws BusinessException {
        //1.校验文章属性
        this.checkArticleInfo(id, userId);
        //2.修改文章状态
        Article article = new Article();
        article.setStatus(CommonConst.INVALID_STATUS);
        article.setId(id);
        int res = articleMapper.updateByPrimaryKeySelective(article);
        if(res > 0){
            //3.逻辑删除文章所属图片
            res = articleAccessoryMapper.removeByArticleId(id);
        }
        return res;
    }

    @Override
    @Transactional
    public Article updateArticleById(ArticleVo articleVo) throws BusinessException {
        //1.校验数据
        BeanValidator.check(articleVo);
        //2.校验文章属性
        this.checkArticleInfo(articleVo.getId(), articleVo.getAuthorId());
        //3.修改文章基本信息
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);
        String voContent = articleVo.getContent();
        article.setSummary(PreviewTextUtil.getText(voContent, cutOutArticleSummaryIndex));
        article.setUpdateTime(new Date());
        int res = articleMapper.updateByPrimaryKeySelective(article);
        if(res > 0){
            //4.保存文章内容信息
            Document doc = Jsoup.parse(voContent);
            ArticleContent content = new ArticleContent();
            content.setContent(doc.html());
            content.setId(article.getId());
            res = articleContentMapper.updateByPrimaryKeySelective(content);
            if(res > 0){
                //6.物理删除修改前的文章图片
                articleAccessoryMapper.deleteOldAccessorysByArticleId(article.getId());
                //5.获取文章附件信息
                List<ArticleAccessory> articleAccessories = this.extractImages(doc, article.getId());
                if(articleAccessories.size() > 0){
                    //7.新增当前图片
                    res = articleAccessoryMapper.insertList(articleAccessories);
                }
            }
        }
        return article;
    }

    @Override
    public PageResult<Article> getInfoByTagWithPage(String tagName, int number) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(KeyConst.ARTICLE_TAG_KEY, tagName);
        return this.getCommonWithPage(CommonConst.ARTICLE_ORDER_NEWSET, number, parameters);
    }

    @Override
    public PageResult<Article> getInfoByGroupWithPage(int groupId, int number) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(KeyConst.ARTICLE_GROUP_PARAMETER_KEY, groupId);
        return this.getCommonWithPage(CommonConst.ARTICLE_ORDER_NEWSET, number, parameters);
    }

    @Override
    public void addViews(int id, String ip) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        //文章缓存id
        String key = KeyConst.ARTICLE_VIEWS_REDIS_KEY_PREFIX + id;
        Map<String, Integer> cacheMap;
        String cache = ops.get(key);
        if(cache == null){
            cacheMap = new HashMap<>();
            //浏览量 +1
            cacheMap.put(ip, 1);
            ops.set(key, JsonUtil.serialize(cacheMap));
            articleMapper.addViews(id);
        } else {
            //判断用户是否浏览过文章
            cacheMap = JsonUtil.parseMap(cache, String.class, Integer.class);
            if(cacheMap.get(ip) == null){
                //浏览量 +1
                cacheMap.put(ip, 1);
                ops.set(key, JsonUtil.serialize(cacheMap));
                articleMapper.addViews(id);
            }
        }
    }

    /**
     * @Name getCommonWithPage
     * @Description 获取公共的
     * @Author wen
     * @Date 2019/4/27
     * @param sort
     * @param number
     * @param parameters
     * @return com.xh.blogs.domain.vo.PageResult<com.xh.blogs.domain.po.Article>
     */
    private PageResult<Article> getCommonWithPage(int sort, int number, Map<String, Object> parameters) {
        parameters.put(CommonConst.STATUS_KEY, CommonConst.EFFECTIVE_STATUS);
        parameters.put(CommonConst.ORDER_BY_KEY, sort);
        Page<Article> page = PageHelper.startPage(number, pageSize);
        articleMapper.selectInfoWithPage(parameters);
        return PageUtil.create(page, ArticleUtil.getArticles(page.getResult()));
    }

    /**
    * @Name checkArticleInfo
    * @Description 1.校验文章是否存在 2.判断是否是自己的文章
    * @Author wen
    * @Date 2019/5/7
    * @param id
    * @param userId
    * @return void
    */
    private void checkArticleInfo(int id, int userId) throws BusinessException {
        //1.判断文章是否存在
        Article dbArticle = articleMapper.selectByPrimaryKey(id);
        if(dbArticle == null){
            throw new BusinessException(EmError.ARTICLE_IS_NOT_EXIST);
        }
        //2.判断是否是自己的文章
        if(!dbArticle.getAuthorId().equals(userId)){
            throw new BusinessException(EmError.CANT_HANDLE_OTHER);
        }
    }


    /**
    * @Name extractImages
    * @Description 提取图片信息
    * @Author wen
    * @Date 2019/4/24
    * @param html
    * @return List<ArticleAccessory>
    */
    private List<ArticleAccessory> extractImages(Document html, Integer aricleId) {
        List<ArticleAccessory> rets = new ArrayList<>();
        Elements elements = html.select(CommonConst.CSS_QUERY_IMG);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (Element el : elements) {
            String imageUrl = el.attr(CommonConst.ATTRIBUTE_KEY_SRC);
            if (request.getContextPath().length() > 1 && imageUrl.startsWith(request.getContextPath())) {
                imageUrl = imageUrl.replace(request.getContextPath(), "");
            }
            ArticleAccessory accessory = new ArticleAccessory();
            accessory.setStatus(CommonConst.EFFECTIVE_STATUS);
            accessory.setOriginal(imageUrl);
            accessory.setPreview(imageUrl);
            accessory.setToId(aricleId);
            //TODO 暂时写
            accessory.setStore(CommonConst.ARTICLE_STORE_NETWORK);
            rets.add(accessory);
        }
        return rets;
    }

}
