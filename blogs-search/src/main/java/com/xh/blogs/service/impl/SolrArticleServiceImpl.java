package com.xh.blogs.service.impl;

import com.xh.blogs.bean.SolrArticle;
import com.xh.blogs.consts.ArticleConst;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.StringConst;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.service.ISolrArticleService;
import com.xh.blogs.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

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
        log.info("============ 【solr服务】START批量新增文章信息 ===========");
        List<SolrArticle> solrArticles = JsonUtil.parseList(solrArticleListJson, SolrArticle.class);
        solrTemplate.saveBeans(ArticleConst.SOLR_COLLECTION1, solrArticles);
        solrTemplate.commit(ArticleConst.SOLR_COLLECTION1);
        log.info("============ 【solr服务】END批量新增文章信息成功 ===========");
    }

    @Override
    public void createEsLibrary(String solrArticleListJson) {
        this.deleteAll();
        log.info("============ 【solr服务】START初始化文章的全文检索信息 ===========");
        this.batchInsert(solrArticleListJson);
        log.info("============ 【solr服务】END初始化文章的全文检索信息成功 ===========");
    }

    @Override
    public void deleteById(int id) {
        log.info("============ 【solr服务】START删除文章信息By Id ===========");
        solrTemplate.deleteByIds(ArticleConst.SOLR_COLLECTION1, String.valueOf(id));
        solrTemplate.commit(ArticleConst.SOLR_COLLECTION1);
        log.info("============ 【solr服务】END删除文章信息成功By Id ===========");
    }

    @Override
    public SolrArticle save(String solrArticleJson) {
        log.info("============ 【solr服务】START保存文章信息 ===========");
        SolrArticle solrArticle = JsonUtil.parse(solrArticleJson, SolrArticle.class);
        solrTemplate.saveBean(ArticleConst.SOLR_COLLECTION1, solrArticle);
        solrTemplate.commit(ArticleConst.SOLR_COLLECTION1);
        log.info("============ 【solr服务】END保存文章信息成功 ===========");
        return solrArticle;
    }

    @Override
    public void deleteAll() {
        log.info("============ 【solr服务】START删除所有文章信息 ===========");
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(ArticleConst.SOLR_COLLECTION1, query);
        log.info("============ 【solr服务】END删除所有文章信息成功 ===========");
    }

    @Override
    public SolrArticle getById(int id) {
        Optional<SolrArticle> optionalSoArticle = solrTemplate.getById(ArticleConst.SOLR_COLLECTION1, id, SolrArticle.class);
        return optionalSoArticle.get();
    }

    @Override
    public PageResult<SolrArticle> search(String keywords, long number) {
        HighlightQuery query = new SimpleHighlightQuery();
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField(ArticleConst.ARTICLE_TITLE).addField(ArticleConst.ARTICLE_SUMMARY)
                .setSimplePrefix(CommonConst.HTML_HIGHLIGHT_PREFIX)
                .setSimplePostfix(CommonConst.HTML_HIGHLIGHT_SUFFIX);
        query.setHighlightOptions(highlightOptions);
        Criteria criteria = new Criteria(ArticleConst.ARTICLE_KEYWORDS).is(keywords);
        query.addCriteria(criteria);
        query.setOffset((number - 1) * pageSize).setRows(pageSize);
        HighlightPage<SolrArticle> highlightPage = solrTemplate.queryForHighlightPage(ArticleConst.SOLR_COLLECTION1, query, SolrArticle.class);
        List<HighlightEntry<SolrArticle>> list = highlightPage.getHighlighted();
        for (HighlightEntry<SolrArticle> entry : list) {
            SolrArticle solrArticle = entry.getEntity();
            List<HighlightEntry.Highlight> highlights = entry.getHighlights();
            if (highlights.size() == 1) {
                if (ArticleConst.ARTICLE_TITLE.equals(highlights.get(0).getField().getName())) {
                    solrArticle.setTitle(entry.getHighlights().get(0).getSnipplets().get(0));
                } else {
                    solrArticle.setSummary(entry.getHighlights().get(0).getSnipplets().get(0));
                }
            }
            if (highlights.size() == 2) {
                solrArticle.setTitle(entry.getHighlights().get(0).getSnipplets().get(0));
                solrArticle.setSummary(entry.getHighlights().get(1).getSnipplets().get(0));
            }
        }
        return new PageResult(highlightPage.getTotalElements(), (int) number, highlightPage.getTotalPages(), highlightPage.getContent());
    }

}
