package com.xh.blogs.consts;

/**
 * @Name KeyConst
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
public interface KeyConst {

    String COMMENTS_CONTENT_KEY = "cont";

    String BLOGS_GROUP_CACHE_KEY = "redis:blogsGroupKey";

    String BLOGS_GROUP_KEY = "groupsCache";

    String EMAIL_CODE_KEY = "emailCodeKey";

    String RESTUL_EMAIL_CODE_KEY = "verifyCode";

    String USER_NICK_NAME_KEY = "nickName";

    /** 后台角色菜单关系树缓存key*/
    String ADMIN_ROLE_MENU_TREE_CACHE_KEY = "redis:roleMenuCacheTree";
}
