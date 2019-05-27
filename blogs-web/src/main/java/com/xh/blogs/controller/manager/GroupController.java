package com.xh.blogs.controller.manager;

import com.xh.blogs.api.IGroupService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.domain.po.Group;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Name CommentsController
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
@Controller
@RequestMapping("/admin/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("/list")
    @RequiresPermissions("sys:group:view")
    public String list(ModelMap model) {
        model.put(CommonConst.DATA_RESULT_KEY, groupService.getAll());
        return ViewUrl.ADMIN_GROUP_LIST;
    }

    @GetMapping("/view")
    @RequiresPermissions("sys:group:edit")
    public String view(Integer id, ModelMap model) {
        if(id != null && id > 0){
            model.put(CommonConst.DATA_RESULT_KEY, groupService.getById(id));
        }
        return ViewUrl.ADMIN_GROUP_VIEW;
    }

    @PostMapping("/update")
    @RequiresPermissions("sys:group:update")
    public String update(Group group){
        //修改操作
        groupService.save(group);
        //更新缓存
        groupService.updateShowCache();
        return RequestUrl.REDIRECT_ADMIN_GROUP_LIST;
    }

}
