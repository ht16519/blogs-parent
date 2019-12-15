package com.xh.blogs.controller.manager;

import com.xh.blogs.service.IFriendLinkService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.domain.po.FriendLink;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Name AdminController
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Controller
@RequestMapping("/admin/link")
public class FriendLinkController {

    @Autowired
    private IFriendLinkService friendLinkService;

    @GetMapping("/list")
    @RequiresPermissions("sys:link:view")
    public String list(ModelMap model) {
        model.put(CommonConst.DATA_RESULT_KEY, friendLinkService.getAll());
        return ViewUrl.ADMIN_LINK_LIST;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("sys:link:edit")
    public String view(@PathVariable("id") long id, ModelMap model) {
        if(id > 0){
            model.put(CommonConst.DATA_RESULT_KEY, friendLinkService.getById(id));
        }
        return ViewUrl.ADMIN_LINK_EDIT;
    }

    @GetMapping("/delete/{id}")
    @RequiresPermissions("sys:link:edit")
    public String view(@PathVariable("id") long id) {
        friendLinkService.deleteById(id);
        friendLinkService.updateShowCache();
        return RequestUrl.REDIRECT_ADMIN_LINK_LIST;
    }

    @PostMapping("/save")
    @RequiresPermissions("sys:link:edit")
    public String update(FriendLink friendLink){
        //修改操作
        friendLinkService.save(friendLink);
        //更新缓存
        friendLinkService.updateShowCache();
        return RequestUrl.REDIRECT_ADMIN_LINK_LIST;
    }

}
