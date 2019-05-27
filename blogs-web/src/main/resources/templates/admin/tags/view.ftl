<#include "/admin/utils/ui.ftl"/>
<@layout>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2><#if data?? && data.id != 0>修改<#else>新增</#if>标签</h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <br>
				<#include "/admin/message.ftl">
                <form id="qForm" class="form-horizontal form-label-left" method="post" action="${base}/admin/tags/save">
                    <#if data??>
                    <input type="hidden" name="id" value="${data.id}" />
                    </#if>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">名称：</label>
                        <div class="col-lg-4">
                            <input type="text" name="name" placeholder="请输入标签名称，如：java，html，大数据等" class="form-control" value="${data.name}">
                        </div>
                    </div>
                    <#--<div class="form-group">-->
                        <#--<label class="col-lg-3 control-label">标记：</label>-->
                        <#--<div class="col-lg-4">-->
                            <#--<input type="text" name="value" placeholder="暂时好像没有什么用" class="form-control" value="${data.vaule}">-->
                        <#--</div>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="col-lg-3 control-label">排序：</label>
                        <div class="col-lg-4">
                            <input type="text" name="sort" placeholder="请输入展示顺序" class="form-control" value="${data.sort}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">状态：</label>
                        <div class="col-lg-4">
                            <select name="status" class="form-control" data-select="${data.status}">
                                <option value="10">显示</option>
                                <option value="-10">隐藏</option>
                            </select>
                        </div>
                    </div>

                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                            <button type="submit" class="btn btn-success">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
var J = jQuery;

$(function() {
})
</script>
</@layout>