package com.xh.blogs.controller.manager;

import com.xh.blogs.service.IRoleService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.po.Role;
import com.xh.blogs.domain.vo.RoleVo;
import com.xh.blogs.exception.BusinessException;
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
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/list")
    @RequiresPermissions("sys:roles:view")
    public String list(ModelMap model) {
        model.put(CommonConst.DATA_RESULT_KEY, roleService.getAll());
        return ViewUrl.ADMIN_ROLE_LIST;
    }

    @GetMapping("/view/{id}")
    @RequiresPermissions("sys:roles:edit")
    public String edit(@PathVariable("id") int id, ModelMap model) {
        if(id > 0){
            model.put(CommonConst.DATA_RESULT_KEY, roleService.getById(id));
        }else {
            Role role = new Role();
            role.setId(id);
            model.put(CommonConst.DATA_RESULT_KEY, role);
        }
        return ViewUrl.ADMIN_ROLE_VIEW;
    }

    @PostMapping("/save")
    @RequiresPermissions("sys:roles:edit")
    public String save(RoleVo roleVo, ModelMap model) {
        Integer roleVoId = roleVo.getId();
        try {
            if(roleVoId == null){
                roleVoId = roleService.add(roleVo);//新增
            }else {
                roleVoId = roleService.update(roleVo);//修改
            }
            super.getModelMap(model);
        } catch (BusinessException e) {
            super.getModelMap(e, model);
        }
        return RequestUrl.REDIRECT_ADMIN_ROLE_VIEW + roleVoId;
    }

    @GetMapping("/delete/{id}")
    @RequiresPermissions("sys:roles:edit")
    public String delete(@PathVariable("id") int id) {
        roleService.delete(id);
        return RequestUrl.REDIRECT_ADMIN_ROLE_LIST;
    }


}
