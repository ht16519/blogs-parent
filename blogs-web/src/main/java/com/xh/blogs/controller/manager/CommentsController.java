package com.xh.blogs.controller.manager;

import com.xh.blogs.service.ICommentsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.consts.ViewUrl;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Name CommentsController
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
@Controller
@RequestMapping("/admin/comments")
public class CommentsController {

    @Autowired
    private ICommentsService commentsService;

    @GetMapping("/list")
    @RequiresRoles("admin")
    public String list(String cont, Integer pn,  ModelMap model) {
        model.put(CommonConst.RESULT_PAGE_INFO_KEY, commentsService.getWithPage(cont ,pn));
        model.put(KeyConst.COMMENTS_CONTENT_KEY, cont);
        model.put(CommonConst.PAGE_NUMBER_KEY, pn);
        return ViewUrl.ADMIN_COMMENTS_LIST;
    }


}
