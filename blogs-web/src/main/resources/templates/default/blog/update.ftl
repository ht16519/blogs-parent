<#include "/default/utils/layout.ftl"/>
<@ui_simple "编辑文章">

<div class="panel panel-default">
	<div class="panel-heading">
        <i class="fa fa-pencil-square"></i> 编辑文章&nbsp;&nbsp;&nbsp;&nbsp;发帖规范，利于seo，必看&nbsp;&nbsp;<a href="${base}/affiche/2"><span style="color: red;">发帖规范</span></a>
	</div>
	<div class="panel-body">
        <div id="message">
            <#include "/default/inc/action_message.ftl"/>
        </div>
		<form class="form-horizontal" action="${base}/home/article/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${data.id}"/>
            <#--<input type="hidden" name="authorId" value="${data.user.id}"/>-->
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right">标题</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="title" maxlength="32" data-required value="${data.title}">
				</div>
			</div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">类型</label>
                <div class="col-sm-3">
                    <select class="form-control" name="type">
                        <option <#if (data.type == 1)> selected </#if> value="1">原创</option>
                        <option <#if (data.type == 0)> selected </#if> value="0">转载</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right">发布到</label>
                <div class="col-sm-3">
                    <select class="form-control" name="belongGroup">
						<#list groupsCache as row>
                            <option value="${row.id}" <#if (data.belongGroup == row.id)> selected </#if>>${row.groupValue}</option>
						</#list>
                    </select>
                </div>
            </div>
			<div class="form-group">
				<label for="desc" class="col-sm-2 control-label no-padding-right">内容</label>
				<#--<input type="hidden" name="editor" value="${data.editor}"/>-->
                <div class="col-sm-10" style="width: 77%;">
					<#include "/default/blog/editor/ueditor.ftl"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right">标签</label>
				<div class="col-sm-8">
					<input type="hidden" name="tags" id="fieldTags" value="${data.tags}">
					<ul id="tags"></ul>
					<p class="help-block" style="font-size: 12px;">添加相关标签，用逗号或空格分隔 (最多4个).</p>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="text-center">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div>
			</div>
		</form>
		<!-- /form-actions -->
	</div>
</div>

<script type="text/javascript">
seajs.use('post', function (post) {
	post.init();
});
</script>
</@ui_simple>