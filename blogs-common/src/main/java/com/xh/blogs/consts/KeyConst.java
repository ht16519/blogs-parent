package com.xh.blogs.consts;

/**
 * @Name KeyConst
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
public interface KeyConst {

    String SITE_FOOTER_TOPS_KEY = "footerTops";

    String SITE_ATTACHS_DETAILS_KEY = "attachsDetails";

    String ARTICLE_TAG_PARAMETER_KEY = "tag";

    String EMAIL_TEMPLATE_TITLE_KEY = "email_template_title";

    String SITE_DOMAIN = "site_domain";

    String SITE_NAME = "site_name";

    String ARTICLE_GROUP_PARAMETER_KEY = "groupId";

    String ARTICLE_TAG_KEY = "tagName";

    String ARTICLE_SEARCH_PARAMETER_KEY = "q";

    String SYSTEM_PARENT_ID_KEY = "pid";

    String DB_CREATE_TIME_KEY = "createTime";

    String ARTICLE_COMMTENTS_KEY = "comments";

    String ARTICLE_FAVORS_KEY = "favors";

    String COMMENTS_CONTENT_KEY = "cont";

    String BLOGS_GROUP_KEY = "groupsCache";

    String EMAIL_CODE_KEY = "emailCodeKey";

    String RESTUL_EMAIL_CODE_KEY = "verifyCode";

    String USER_INPUT_EMAIL_KEY = "inputEmail";

    String USER_NICK_NAME_KEY = "nickName";

    String ARTICLE_VIEWS_REDIS_KEY_PREFIX = "artcileViewsRedis:";

    String BLOGS_GROUP_CACHE_KEY = "redis:blogsGroupKey";

    /** 后台角色菜单关系树缓存key*/
    String ADMIN_ROLE_MENU_TREE_CACHE_KEY = "redis:roleMenuCacheTree";

    String BLOGS_TAGS_CACHE_KEY = "redis:blogsTagsKey";

    String SITE_ATTACHS_COLLECTION_KEY = "redis:attachsCollection";

}
