package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.ArticleAccessoryMapper;
import com.xh.blogs.dao.mapper.ArticleContentMapper;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.po.ArticleAccessory;
import com.xh.blogs.domain.po.ArticleContent;
import com.xh.blogs.domain.vo.ArticleVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.service.IArticleService;
import com.xh.blogs.utils.CommonUtil;
import com.xh.blogs.utils.PreviewTextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleAccessoryMapper articleAccessoryMapper;

    @Override
    public PageResult<Article> getArticleInfoWithPage(String order) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(CommonConst.ORDER_BY_KEY, order);
        Page<Article> page = PageHelper.startPage(CommonConst.pageNum, CommonConst.pageSize);
        articleMapper.selectArticleInfoWithPage(parameters);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult<Article> getByIdWithPage(int id, int pageNum) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(CommonConst.USER_ID_KEY, id);
        parameters.put(CommonConst.STATUS_KEY, CommonConst.EFFECTIVE_STATUS);
        Page<Article> page = PageHelper.startPage(pageNum, CommonConst.pageSize);
        articleMapper.selectInfoWithPage(parameters);
        return new PageResult<>(page.getTotal(), this.getArticles(page));
    }

    @Override
    @Transactional
    public int addArticle(ArticleVo articleVo) {
        //1.TODO 参数校验

        //2.保存文章基本信息
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);
        String voContent = articleVo.getContent();
        article.setSummary(PreviewTextUtil.getText(voContent, 126));
        article.setCreateTime(new Date());
        articleMapper.insertSelective(article);
        //3.保存文章内容信息
        Document doc = Jsoup.parse(voContent);
        ArticleContent content = new ArticleContent();
        content.setContent(doc.html());
        content.setId(article.getId());
        articleContentMapper.insertSelective(content);
        //4.保存文章附件信息
        List<ArticleAccessory> articleAccessories = this.extractImages(doc, article.getId());
        if(articleAccessories.size() > 0){
            articleAccessoryMapper.insertList(articleAccessories);
        }
        //TODO 文章信息加入图片数和最后一张图片id
        return 0;
    }

    @Override
    public Article getById(int id) throws BusinessException {
        Article article = articleMapper.selectById(id);
        if(article == null){
            throw new BusinessException(EmError.ARTICLE_IS_NOT_EXIST);
        }
        return article;
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
            accessory.setToId(aricleId);
            accessory.setPreview(imageUrl);
            if (imageUrl.startsWith(CommonConst.THE_HTTP_PREFIX)) {
                accessory.setStore(CommonConst.ARTICLE_STORE_NETWORK);
            }else {
                accessory.setStore(CommonConst.ARTICLE_STORE_LOCAL);
            }
            rets.add(accessory);
        }
        return rets;
    }

    /**
    * @Name getArticles
    * @Description 获取文章附件信息
    * @Author wen
    * @Date 2019/4/23
    * @param page
    * @return java.util.List<com.xh.blogs.domain.po.Article>
    */
    private List<Article> getArticles(Page<Article> page) {
        List<Article> articleList = page.getResult();
        for (Article article : articleList) {
            String accessorys = article.getAccessorys();
            if(StringUtils.isNotEmpty(accessorys)){
                List<ArticleAccessory> albums = new ArrayList<>();
                article.setAlbums(albums);
                String[] accessory = accessorys.split(CommonConst.ARTICLE_ACCESSORY_SEPARATOR);
                for (String s : accessory) {
                    try {
                        String[] field = s.split(CommonConst.ACCESSORYS_SEPARATOR);
                        ArticleAccessory articleAccessory = new ArticleAccessory();
                        articleAccessory.setId(CommonUtil.null2Int(field[0]));
                        articleAccessory.setOriginal(field[1]);
                        articleAccessory.setPreview(field[2]);
                        articleAccessory.setStore(CommonUtil.null2Int(field[3]));
                        article.getAlbums().add(articleAccessory);
                    } catch (Exception e) {
                        log.error("cut out ArticleAccessory exception:{}", e);
                    }
                }
            }
            article.setAccessorys(null);
        }
        return articleList;
    }

}
