package com.xh.blogs.controller.manager;

import com.xh.blogs.consts.ViewUrl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Name AdminController
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"", "/index"})
    @RequiresPermissions("sys:admin")
    public String index(ModelMap model) {
        this.pushSystemStatus(model);
        return ViewUrl.ADMIN_INDEX;
    }

    /**
    * @Name pushSystemStatus
    * @Description 获取计算机运行数据
    * @Author wen
    * @Date 2019/5/4
    * @param model
    * @return void
    */
    private void pushSystemStatus(ModelMap model) {
        float freeMemory = (float) Runtime.getRuntime().freeMemory();
        float totalMemory = (float) Runtime.getRuntime().totalMemory();
        float usedMemory = (totalMemory - freeMemory);
        float memPercent = Math.round(freeMemory / totalMemory * 100) ;
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");

        model.addAttribute("freeMemory", freeMemory);
        model.addAttribute("totalMemory", totalMemory / 1024 / 1024);
        model.addAttribute("usedMemory", usedMemory / 1024 / 1024);
        model.addAttribute("memPercent", memPercent);
        model.addAttribute("os", os);
        model.addAttribute("javaVersion", javaVersion);
    }


}
