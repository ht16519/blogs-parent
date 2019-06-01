package com.xh.blogs.consts;

/**
 * @Name ConfigConst
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
public interface ConfigConst {

    String DEFAULT_SYSTEM_EMAIL_TITLE = "IT云博客邮箱验证";

    /** 加载文章图片张数+1*/
    int ARTICLE_ACCESSORY_COUNT = 3;

    /** 后台角色菜单缓存key*/
    String ADMIN_ROLE_MENU_CACHE_KEY = "redis:roleMenuCache";

    /** 用户菜单缓存key*/
    String ADMIN_USER_MENU_CACHE_KEY = "userMenus";

    /**
     * 默认头像路径
     */
    String AVATAR_PATH = "/static/assets/images/ava/default.png";

    /** 头像图片前缀*/
    String AVATAR_PREFIX = "/avatar_";

    /** 文章图片前缀*/
    String ARTICLE_IMAGE_PREFIX = "/article_";

}
