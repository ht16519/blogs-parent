package com.xh.blogs.domain.es;

import com.xh.blogs.consts.CommonConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Name EsArticle
 * @Description
 * @Author wen
 * @Date 2019-05-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "article", type = "article", shards = 1)
public class EsArticle implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    /**
     * 摘要信息
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String summary;

    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String title;

    /**
     * 所属标签
     */
    @Field(type = FieldType.Keyword)
    private String tags;

    /** 是否推荐*/
    @Field(type = FieldType.Integer, index = false)
    private Integer featured;

    /** 文章类型是否原创（1，0：是，否）*/
    @Field(type = FieldType.Integer, index = false)
    private Integer type;

    /** 分类*/
    @Field(type = FieldType.Integer, index = false)
    private Integer belongGroup;

    /** 评论量*/
    @Field(type = FieldType.Integer, index = false)
    private Integer comments;

    /** 收藏量*/
    @Field(type = FieldType.Integer, index = false)
    private Integer favors;

    /** 创建时间*/
    @Field(type = FieldType.Date, index = false)
    private Date createTime;

    public String[] getTagsArray() {
        if(StringUtils.isNotBlank(this.tags)){
            return this.tags.split(CommonConst.SEPARATOR);
        }
        return null;
    }
}
