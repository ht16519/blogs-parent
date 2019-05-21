package com.xh.blogs.controller.manager;

import com.xh.blogs.api.IMenuService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.vo.WebApiResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Name AdminController
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/list")
    @RequiresPermissions("sys:menus:view")
    public String index(ModelMap model) {
        model.put(CommonConst.DATA_RESULT_KEY, menuService.getAllMenus());
        return ViewUrl.ADMIN_MENU_LIST;
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("sys:menus:edit")
    public String edit(@PathVariable("id") int id, ModelMap model) {
        model.put(CommonConst.DATA_RESULT_KEY, menuService.getById(id));
        return ViewUrl.ADMIN_MENU_VIEW;
    }

    @ResponseBody
    @GetMapping("/tree/{id}")
    @RequiresPermissions("sys:roles:edit")
    public WebApiResult tree(@PathVariable("id") int id) {
        if (id > 0) {
            return WebApiResult.success(menuService.getNodeByRoleId(id));
        }
        return WebApiResult.success(menuService.getNode());
    }


}
