package com.xh.blogs.consts;

/**
 * @Name KeyConst
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
public interface KeyConst {


    String ARTICLE_SEARCH_PARAMETER_KEY = "q";

    String SYSTEM_PARENT_ID_KEY = "pid";

    String DB_CREATE_TIME_KEY = "createTime";

    String ARTICLE_COMMTENTS_KEY = "comments";

    String ARTICLE_FAVORS_KEY = "favors";

    String COMMENTS_CONTENT_KEY = "cont";

    String BLOGS_GROUP_CACHE_KEY = "redis:blogsGroupKey";

    String BLOGS_GROUP_KEY = "groupsCache";

    String EMAIL_CODE_KEY = "emailCodeKey";

    String RESTUL_EMAIL_CODE_KEY = "verifyCode";

    String USER_NICK_NAME_KEY = "nickName";

    /** 后台角色菜单关系树缓存key*/
    String ADMIN_ROLE_MENU_TREE_CACHE_KEY = "redis:roleMenuCacheTree";

    String BLOGS_TAGS_CACHE_KEY = "redis:blogsTagsKey";
}
