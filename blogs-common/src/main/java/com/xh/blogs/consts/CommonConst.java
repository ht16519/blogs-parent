package com.xh.blogs.consts;

/**
 * @Name ApplicationName
 * @Description
 * @Author wen
 * @Date 2019-04-03
 */
public interface CommonConst {

    String ARTICLE_TITLE_KEY = "title";

    String DATA_RESULT_KEY = "data";

    String COUNT_KEY = "count";

    String USER_INFO_KEY = "user";

    String COMMON_RETURN_RESULT_KEY = "ret";

    String THE_HTTP_PREFIX = "http";

    String CSS_QUERY_IMG = "img";

    String ATTRIBUTE_KEY_SRC = "src";

    String ARTICLE_GROUP = "groups";

    String RESULT_PAGE_INFO_KEY = "page";

    int ARTICLE_STORE_NETWORK = 1;

    int ARTICLE_STORE_LOCAL = 2;

    int PAGE_NUMBER = 1;

    String PAGE_NUMBER_KEY = "pn";

    String USER_ID_KEY = "userId";

    String STATUS_KEY = "status";

    int EFFECTIVE_STATUS = 10;

    int INVALID_STATUS = -10;

    String COMMON_REDIS_KEY_PREFIX = "redisKey:";

    String SYSTEM_PROFILE = "profile";

    String ORDER_BY_KEY = "sort";

    /** 文章最新排序*/
    int ARTICLE_ORDER_NEWSET = 40;

    /** 文章推荐排序*/
    int ARTICLE_ORDER_FEATURED = 10;

    /** 文章热门排序*/
    int ARTICLE_ORDER_HOTTEST = 20;

    String ARTICLE_ACCESSORY_SEPARATOR = "===";

    String ACCESSORYS_SEPARATOR = "---";

    String SEPARATOR = ",";

    String SEPARATOR_SEMICOLON = ";";

    String SEPARATOR_POINT = ".";

}
