package com.xh.blogs.controller.manager;

import com.xh.blogs.api.IMenuService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.KeyConst;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.vo.MenuVo;
import com.xh.blogs.domain.vo.WebApiResult;
import com.xh.blogs.exception.BusinessException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/view/{id}")
    @RequiresPermissions("sys:menus:edit")
    public String save(@PathVariable("id") int id, ModelMap model) {
        if(id >= 0){
            model.put(CommonConst.DATA_RESULT_KEY, menuService.getById(id));
        }
        return ViewUrl.ADMIN_MENU_VIEW;
    }

    /**
    * @Name create
    * @Description 创建下级菜单
    * @Author wen
    * @Date 2019/5/26
    * @param pid
    * @param model
    * @return java.lang.String
    */
    @GetMapping("/create/{pid}")
    @RequiresPermissions("sys:menus:edit")
    public String create(@PathVariable("pid") int pid, ModelMap model) {
        model.put(KeyConst.SYSTEM_PARENT_ID_KEY, pid);
        return ViewUrl.ADMIN_MENU_VIEW;
    }

    @PostMapping("/save")
    @RequiresPermissions("sys:menus:edit")
    public String save(MenuVo menuVo, ModelMap model) {
        try {
            //1.菜单操作
            menuService.save(menuVo, super.getProfile().getId());
            //2.更新缓存
            menuService.updateRoleMenuCache();
            super.getModelMap(model);
        } catch (BusinessException e) {
            super.getModelMap(e, model);
        }
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
