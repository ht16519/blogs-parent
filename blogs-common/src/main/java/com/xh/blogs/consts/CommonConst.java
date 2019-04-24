package com.xh.blogs.consts;

/**
 * @Name ApplicationName
 * @Description
 * @Author wen
 * @Date 2019-04-03
 */
public interface CommonConst {

    String COMMON_RETURN_RESULT_KEY = "ret";

    String THE_HTTP_PREFIX = "http";

    String CSS_QUERY_IMG = "img";

    String ATTRIBUTE_KEY_SRC = "src";

    String ARTICLE_GROUP = "groups";

    String RESULT_PAGE_INFO_KEY = "page";

    int ARTICLE_STORE_NETWORK = 1;

    int ARTICLE_STORE_LOCAL = 2;

    int pageNum = 1;

    int pageSize = 1;

    String USER_ID_KEY = "userId";

    String STATUS_KEY = "status";

    int EFFECTIVE_STATUS = 10;

    String COMMON_REDIS_KEY_PREFIX = "redisKey:";

    /**
     * 默认头像
     */
    String AVATAR = "/assets/images/ava/default.png";

    String SYSTEM_PROFILE = "profile";

    String ORDER_BY_KEY = "orderBy";

    /** 文章最新排序*/
    String ARTICLE_ORDER_NEWSET = "newest";

    /** 文章推荐排序*/
    String ARTICLE_ORDER_FEATURED = "featured";

    /** 文章热门排序*/
    String ARTICLE_ORDER_HOTTEST= "hottest";

    String ARTICLE_ACCESSORY_SEPARATOR = "===";

    String ACCESSORYS_SEPARATOR = "---";

    String SEPARATOR = ",";

}
