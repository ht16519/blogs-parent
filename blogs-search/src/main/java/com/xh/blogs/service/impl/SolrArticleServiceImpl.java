package com.xh.blogs.service.impl;

import com.xh.blogs.bean.SolrArticle;
import com.xh.blogs.consts.*;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.service.ISolrArticleService;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * @Name SearchServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-12-04
 */
@Slf4j
@Service
public class SolrArticleServiceImpl implements ISolrArticleService {

    @Value("${blogs.pageSize}")
    private int pageSize;
    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public void batchInsert(String solrArticleListJson) {
        log.info("============ 【初始化】START批量新增文章信息 ===========");
        List<SolrArticle> solrArticles = JsonUtil.parseList(solrArticleListJson, SolrArticle.class);
        solrTemplate.saveBeans(ArticleConst.SOLR_COLLECTION_1, solrArticles);
        solrTemplate.commit(ArticleConst.SOLR_COLLECTION_1);
        log.info("============ 【Solr服务】END批量新增文章信息成功 ===========");
    }

    @Override
    public void createEsLibrary(String solrArticleListJson) {
        this.deleteAll();
        log.info("============ 【Solr服务】START初始化文章的全文检索信息 ===========");
        this.batchInsert(solrArticleListJson);
        log.info("============ 【Solr服务】END初始化文章的全文检索信息成功 ===========");
    }

    @Override
    public void deleteById(int id) {
        log.info("============ 【Solr服务】START删除文章信息By Id ===========");
        solrTemplate.deleteByIds(ArticleConst.SOLR_COLLECTION_1, String.valueOf(id));
        solrTemplate.commit(ArticleConst.SOLR_COLLECTION_1);
        log.info("============ 【Solr服务】END删除文章信息成功By Id ===========");
    }

    @Override
    public SolrArticle save(String solrArticleJson) {
        log.info("============ 【Solr服务】START保存文章信息 ===========");
        SolrArticle solrArticle = JsonUtil.parse(solrArticleJson, SolrArticle.class);
        solrTemplate.saveBean(ArticleConst.SOLR_COLLECTION_1, solrArticle);
        solrTemplate.commit(ArticleConst.SOLR_COLLECTION_1);
        log.info("============ 【Solr服务】END保存文章信息成功 ===========");
        return solrArticle;
    }

    @Override
    public void deleteAll() {
        log.info("============ 【Solr服务】START删除所有文章信息 ===========");
        Query query = new SimpleQuery(SolrQueryConst.QUERY_ALL);
        solrTemplate.delete(ArticleConst.SOLR_COLLECTION_1, query);
        log.info("============ 【Solr服务】END删除所有文章信息成功 ===========");
    }

    @Override
    public SolrArticle getById(int id) {
        Optional<SolrArticle> optionalSoArticle = solrTemplate.getById(ArticleConst.SOLR_COLLECTION_1, id, SolrArticle.class);
        return optionalSoArticle.get();
    }

    @Override
    public PageResult<SolrArticle> search(String keywords, long number) {
        if(StringUtils.isBlank(keywords)){
            return PageResult.createEmpty();
        }
        HighlightPage<SolrArticle> highlightPage = getHighlightPage(keywords, number);
        return PageResult.create(highlightPage.getTotalElements(), (int) number, highlightPage.getTotalPages(), highlightPage.getContent());
    }

    @Override
    public List<SolrArticle> recommend(String keywords) {
        if(StringUtils.isBlank(keywords)){
            return Collections.emptyList();
        }
        HighlightPage<SolrArticle> highlightPage = getHighlightPage(keywords, NumberConst.INT_2);
        return highlightPage.getContent();
    }

    private HighlightPage<SolrArticle> getHighlightPage(String keywords, long number) {
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField(ArticleConst.ARTICLE_TITLE).addField(ArticleConst.ARTICLE_SUMMARY)
                .setSimplePrefix(CommonConst.HTML_HIGHLIGHT_PREFIX)
                .setSimplePostfix(CommonConst.HTML_HIGHLIGHT_SUFFIX);
        HighlightQuery query = new SimpleHighlightQuery();
        query.setHighlightOptions(highlightOptions);
        Criteria criteria = new Criteria(ArticleConst.ARTICLE_KEYWORDS).is(keywords);
        query.addCriteria(criteria);
        query.setOffset((number - NumberConst.INT_1) * pageSize).setRows(pageSize);
        HighlightPage<SolrArticle> highlightPage;
        try {
            highlightPage = solrTemplate.queryForHighlightPage(ArticleConst.SOLR_COLLECTION_1, query, SolrArticle.class);
        }catch (Exception e){
            log.error("============ 【Solr服务】查询文章失败：{} ===========", e.getMessage());
            return new SolrResultPage<>(Collections.emptyList());
        }
        List<HighlightEntry<SolrArticle>> list = highlightPage.getHighlighted();
        for (HighlightEntry<SolrArticle> entry : list) {
            SolrArticle solrArticle = entry.getEntity();
            List<HighlightEntry.Highlight> highlights = entry.getHighlights();
            for (HighlightEntry.Highlight highlight : highlights) {
                if (ArticleConst.ARTICLE_TITLE.equals(highlight.getField().getName())) {
                    solrArticle.setTitle(highlight.getSnipplets().get(IndexConst.COLLECTION_0));
                } else {
                    solrArticle.setSummary(highlight.getSnipplets().get(IndexConst.COLLECTION_0));
                }
            }
        }
        return highlightPage;
    }
}
