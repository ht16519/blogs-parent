package com.xh.blogs.bean;

import com.xh.blogs.consts.ArticleConst;
import com.xh.blogs.consts.CommonConst;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;
import java.io.Serializable;
import java.util.Date;

/**
 * @Name Article
 * @Description
 * @Author wen
 * @Date 2019-12-04
 */
@Data
public class SolrArticle implements Serializable{

    private static final long serialVersionUID = 484654013767102076L;

    @Field
    private Integer id;

    /**
     * 摘要信息
     */
    @Field(ArticleConst.ARTICLE_SUMMARY)
    private String summary;

    /**
     * 标题
     */
    @Field(ArticleConst.ARTICLE_TITLE)
    private String title;

    /**
     * 所属标签
     */
    @Field("art_tags")
    private String tags;

    /** 是否推荐*/
    @Field("art_featured")
    private Integer featured;

    /** 文章类型是否原创（1，0：是，否）*/
    @Field("art_type")
    private Integer type;

    /** 分类*/
    @Field("art_belong_group")
    private Integer belongGroup;

    /** 评论量*/
    @Field("art_comments")
    private Integer comments;

    /** 收藏量*/
    @Field("art_favors")
    private Integer favors;

    /** 创建时间*/
    @Field("art_create_time")
    private Date createTime;

    public String[] getTagsArray() {
        if(StringUtils.isNotBlank(this.tags)){
            return this.tags.split(CommonConst.SEPARATOR);
        }
        return null;
    }

}
