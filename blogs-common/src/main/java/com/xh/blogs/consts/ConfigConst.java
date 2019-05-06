package com.xh.blogs.consts;

/**
 * @Name ConfigConst
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
public interface ConfigConst {

    /** 子评论条数*/
    int COMMENTS_SUBLIST_SIZE = 3;

    /** 加载统计数据条数*/
    int STATISTICAL_COUNT = 9;

    /** 加载文章图片张数*/
    int ARTICLE_COUNT = 3;

    /** 截取文章简介的字符数*/
    int CUT_OUT_ARTICLE_SUMMARY_INDEX = 126;

    /** 后台角色菜单缓存key*/
    String ADMIN_ROLE_MENU_CACHE_KEY = "redis:roleMenuCache";

    String ADMIN_USER_MENU_CACHE_KEY = "userMenus";

}
