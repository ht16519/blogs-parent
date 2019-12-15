package com.xh.blogs.domain.entity;

import com.xh.blogs.consts.CommonConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @Name EArticleComments
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EArticleComments {

    private Integer id;

    private Integer articleId;

    private Integer featured;

    private String summary;

    private Date articleTime;

    private Integer comments;

    private Integer favors;

    private String tags;

    private String title;

    private String content;

    private Date createTime;

    private String nickName;

    private Integer userId;

    private String avatar;

    public String[] getTagsArray() {
        if(StringUtils.isNotBlank(this.tags)){
            return this.tags.split(CommonConst.SEPARATOR);
        }
        return null;
    }
}
