package com.xh.blogs.utils;

import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.po.ArticleAccessory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name ArticleUtil
 * @Description
 * @Author wen
 * @Date 2019-04-28
 */
@Slf4j
public class ArticleUtil {

    /**
     * @Name getArticles
     * @Description 获取文章附件信息
     * @Author wen
     * @Date 2019/4/23
     * @param articleList
     * @return java.util.List<com.xh.blogs.domain.po.Article>
     */
    public static List<Article> getArticles(List<Article> articleList) {
        if(articleList != null && articleList.size() > 0) {
            for (Article article : articleList) {
                String accessorys = article.getAccessorys();
                if (StringUtils.isNotEmpty(accessorys)) {
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
                            log.error("cut out Article Accessory exception:{}", e);
                        }
                    }
                    if(albums.size() > ConfigConst.ARTICLE_COUNT){
                        continue;
                    }
                }
                article.setAccessorys(null);
            }
        }
        return articleList;
    }

}
