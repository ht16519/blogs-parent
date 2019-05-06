<#include "/admin/utils/ui.ftl"/>
<@layout>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>评论管理</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <#--<@shiro.hasPermission name="comments:edit">-->
                        <li><a href="javascrit:void(0);" data-action="batch_del">批量删除</a></li>
                    <#--</@shiro.hasPermission>-->
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <form id="qForm" class="form-inline">
                    <input type="hidden" name="pn" value="${pn}"/>
                    <div class="form-group">
                        <input type="text" name="cont" class="form-control" value="${cont}" placeholder="请输入评论内容">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </div>
            <div class="x_content">
                <table id="dataGrid" class="table table-striped table-bordered b-t">
                    <thead>
                    <tr>
                        <th width="50"><input type="checkbox" class="checkall">
                        </th>
                        <th width="80">#</th>
                        <th>内容</th>
                        <th>作者</th>
                        <th>发表日期</th>
                        <th>上级ID</th>
                        <#--<@shiro.hasPermission name="comments:edit">-->
                            <th width="200"></th>
                        <#--</@shiro.hasPermission>-->
                    </tr>
                    </thead>
                    <tbody>
                        <#list page.items as row>
                        <tr>
                            <td>
                                <input type="checkbox" name="id" value="${row.id}">
                            </td>
                            <td class="text-center">${row.id}</td>
                            <td>${row.content}</td>
                            <td><a href="${base}/ta/${row.userId}">${row.nickName}</a></td>
                            <td>${row.createTime}</td>
                            <td>${row.pid}</td>
                            <#--<@shiro.hasPermission name="comments:edit">-->
                                <td class="text-center" align="left">
                                    <a href="javascript:void(0);" class="btn btn-xs btn-white" data-id="${row.id}"
                                       data-action="delete">
                                        <i class="fa fa-bitbucket"></i> 删除
                                    </a>
                                </td>
                            <#--</@shiro.hasPermission>-->
                        </tr>
                        </#list>
                    </tbody>
                </table>
                <@pager "list" page 3 />
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var J = jQuery;

    function ajaxReload(json) {
        if (json.code == 0) {
            layer.msg(json.msg, {icon: 1});
            setTimeout(function(){
                $('#qForm').submit();
            },1000);
        } else {
            layer.msg(json.msg, {icon: 2});
        }
    }

    function doDelete(ids) {
        J.getJSON('${base}/admin/comments/delete/' + ids, ajaxReload);
    }

    $(function () {
        // 删除
        $('#dataGrid a[data-action="delete"]').bind('click', function () {
            var that = $(this);
            layer.confirm('确定删除此项吗?', {
                btn: ['确定', '取消'], //按钮
                shade: false //不显示遮罩
            }, function () {
                doDelete(that.attr('data-id'));
            }, function () {
            });
            return false;
        });

        $('a[data-action="batch_del"]').click(function () {
            var check_length = $("input[type=checkbox][name=id]:checked").length;

            if (check_length == 0) {
                layer.msg("请至少选择一项", {icon: 2});
                return false;
            }

            var ids = [];
            $("input[type=checkbox][name=id]:checked").each(function () {
                ids.push($(this).val());
            });

            layer.confirm('确定删除此项吗?', {
                btn: ['确定', '取消'], //按钮
                shade: false //不显示遮罩
            }, function () {
                doDelete(ids);
            }, function () {
            });
        });
    })
</script>
</@layout>