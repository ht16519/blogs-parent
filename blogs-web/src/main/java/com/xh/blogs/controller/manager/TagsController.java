package com.xh.blogs.controller.manager;

import com.xh.blogs.api.ITagsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.RequestUrl;
import com.xh.blogs.consts.ViewUrl;
import com.xh.blogs.controller.base.BaseController;
import com.xh.blogs.domain.vo.TagsVo;
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
 * @Name TagsController
 * @Description
 * @Author wen
 * @Date 2019-05-26
 */
@Controller
@RequestMapping("/admin/tags")
public class TagsController extends BaseController{

    @Autowired
    private ITagsService tagsService;

    @GetMapping("/list")
    @RequiresPermissions("sys:tags:view")
    public String list(ModelMap model) {
        model.put(CommonConst.DATA_RESULT_KEY, tagsService.getAllBySort());
        return ViewUrl.ADMIN_TAGS_LIST;
    }

    @GetMapping("/view")
    @RequiresPermissions("sys:tags:edit")
    public String view(Integer id, ModelMap model) {
        if(id != null && id > 0){
            model.put(CommonConst.DATA_RESULT_KEY, tagsService.getById(id));
        }
        return ViewUrl.ADMIN_TAGS_VIEW;
    }

    @PostMapping("/save")
    @RequiresPermissions("sys:tags:edit")
    public String save(TagsVo tagsVo, ModelMap model){
        try {
            //保存标签
            tagsService.save(tagsVo, super.getProfile().getId());
            //更新缓存
            tagsService.createBlogsTagsCache();
            super.getModelMap(model);
        } catch (BusinessException e) {
            super.getModelMap(e, model);
        }
        model.put(CommonConst.DATA_RESULT_KEY, tagsVo);
        return ViewUrl.ADMIN_TAGS_VIEW;
    }

    @GetMapping("/up/{id}")
    @RequiresPermissions("sys:tags:edit")
    public String up(@PathVariable("id") int id) throws BusinessException {
        //排序上移
        tagsService.up(id);
        //更新缓存
        tagsService.createBlogsTagsCache();
        return RequestUrl.REDIRECT_ADMIN_TAGS_LIST;
    }

    @GetMapping("/down/{id}")
    @RequiresPermissions("sys:tags:edit")
    public String down(@PathVariable("id") int id) throws BusinessException {
        //排序下移
        tagsService.down(id);
        //更新缓存
        tagsService.createBlogsTagsCache();
        return RequestUrl.REDIRECT_ADMIN_TAGS_LIST;
    }

}
