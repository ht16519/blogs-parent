package com.xh.blogs.controller.manager;

import com.xh.blogs.api.ICommentsService;
import com.xh.blogs.api.IGroupService;
import com.xh.blogs.domain.vo.WebApiResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @Name ManagerAjaxController
 * @Description
 * @Author wen
 * @Date 2019-05-06
 */
@RestController
@RequestMapping("/admin")
public class ManagerController {

    @Autowired
    private IGroupService groupService;
    @Autowired
    private ICommentsService commentsService;

    @GetMapping("/group/delete/{id}")
    @RequiresPermissions("sys:comments:delete")
    public WebApiResult commentDelete(@PathVariable("id") int id){
        return WebApiResult.getResult(groupService.deleteById(id));
    }

    /**
    * @Name commentDelete
    * @Description 删除评论
    * @Author wen
    * @Date 2019/5/6
    * @param ids
    * @return com.xh.blogs.domain.vo.WebApiResult
    */
    @GetMapping("/comments/delete/{ids}")
    @RequiresPermissions("sys:comments:deletes")
    public WebApiResult commentDelete(@PathVariable("ids") Set<Integer> ids){
        int res = commentsService.batchRemoveByIds(ids);
        return WebApiResult.getResult(res, "操作成功，共删除了" + res + "条数据！");
    }

}
