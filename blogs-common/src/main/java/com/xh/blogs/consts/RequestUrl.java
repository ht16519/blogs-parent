package com.xh.blogs.consts;

/**
 * @Name RequestUrl
 * @Description
 * @Author wen
 * @Date 2019-04-23
 */
public interface RequestUrl {

    String REDIRECT_ADMIN_GROUP_LIST = "redirect:/admin/group/list";

    String ANON_API = "/api/free/**";

    String ARTICLE_DETAILS = "/article/**";

    String LOGIN_URL = "/login";

    String INDEX_URL = "/index";

    String REG_URL = "/register";

    String BLOGGER_DETAILS = "/ta/**";

    String INDEX_URLS = "/index/**";

    String REDIRECT_INDEX = "redirect:/index";

    String REDIRECT_HOME = "redirect:/home/feeds/1";

    String SYSTEM_ROOT = "/";

    String REDIRECT_ADMIN_ROLE_VIEW = "redirect:/admin/role/view/";

    String REDIRECT_ADMIN_ROLE_LIST = "redirect:/admin/role/list";

}
