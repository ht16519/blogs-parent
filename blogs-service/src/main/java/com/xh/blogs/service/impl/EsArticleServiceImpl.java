package com.xh.blogs.service.impl;

import com.xh.blogs.api.IEsArticleService;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.dao.repository.EsArticleRepository;
import com.xh.blogs.domain.es.EsArticle;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Name EsArticleSerImpl
 * @Description
 * @Author wen
 * @Date 2019-05-24
 */
@Service
@Slf4j
public class EsArticleServiceImpl extends BaseServiceImpl implements IEsArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private EsArticleRepository esArticleRepository;

    @Override
    public void deleteById(int id) {
        esArticleRepository.deleteById(id);
    }

    @Override
    public void delete(EsArticle esArticle) {
        esArticleRepository.delete(esArticle);
    }

    @Override
    public EsArticle save(EsArticle esArticle) {
        return esArticleRepository.save(esArticle);
    }

    @Override
    public void save(Article article) {
        EsArticle esArticle = new EsArticle();
        BeanUtils.copyProperties(article, esArticle);
        this.save(esArticle);
    }

    @Override
    public EsArticle getById(int id) {
        return esArticleRepository.findById(id);
    }

    @Override
    public void batchInsert(List<EsArticle> esArticles) {
        esArticleRepository.saveAll(esArticles);
    }

    @Override
    public void createEsLibrary() {
        this.deleteAll();
        log.info("============ START初始化文章的全文检索信息 ===========");
        this.batchInsert(articleMapper.selectAll2EsArticle());
        log.info("============ END初始化文章的全文检索信息成功 ===========");
    }

    @Override
    public void deleteAll() {
        log.info("============ START删除es中所有文章缓存 ===========");
        esArticleRepository.deleteAll();
        log.info("============ END删除es中所有文章缓存成功 ===========");
    }

//    @Override
//    public PageResult<EsArticle> search(String keyword, int number) {
//        // 构建查询
//        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
//        // 多索引查询
//        searchQuery.withIndices("article").withTypes("article");
//        QueryBuilder boolQuery = QueryBuilders.boolQuery()
//                .must(QueryBuilders.multiMatchQuery(keyword, "title", "tags", "summary"));
////                .must(QueryBuilders.moreLikeThisQuery(new String[]{"title", "tags", "summary"}));
//        searchQuery.withQuery(boolQuery)
//            .withHighlightFields(new HighlightBuilder.Field("title"), new HighlightBuilder.Field("summary"))
//            .withPageable(PageRequest.of(number - 1, pageSize));
//        Page<EsArticle> page = esArticleRepository.search(searchQuery.build());
//        PageResult pageResult = new PageResult(page.getTotalElements(), page.getNumber() + 1, page.getTotalPages(), page.getContent());
//        System.err.println(pageResult);
//        return pageResult;
//    }

    @Override
    public PageResult<EsArticle> search(String keyword, int number) {
//        Sort orders = new Sort(Sort.Direction.DESC, //逆序
//                KeyConst.ARTICLE_FAVORS_KEY,        //收藏量
//                KeyConst.ARTICLE_COMMTENTS_KEY,     //评论量
//                KeyConst.DB_CREATE_TIME_KEY);       //创建时间
//        Pageable pageable =  PageRequest.of(number - 1, pageSize, orders);
        Pageable pageable =  PageRequest.of(number - 1, pageSize);
        Page<EsArticle> page = esArticleRepository.findDistinctEsArticleByTitleContainingOrSummaryContainingOrTagsContaining(keyword, keyword, keyword, pageable);
        return new PageResult(page.getTotalElements(), page.getNumber() + 1,page.getTotalPages() , page.getContent());
    }


}
