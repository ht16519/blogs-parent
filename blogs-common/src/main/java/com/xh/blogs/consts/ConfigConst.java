package com.xh.blogs.consts;

/**
 * @Name ConfigConst
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
public interface ConfigConst {

    /** 附件映射路径*/
    String CONFIG_ACCESSORY_PATH = "/accessory/**";

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

    /** 单个文件上传最大size*/
    int MAX_FILE_SIZE = 1024 * 1024;

}
