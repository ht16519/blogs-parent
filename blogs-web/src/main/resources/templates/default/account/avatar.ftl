<#include "/default/utils/layout.ftl"/>
<@ui_simple "修改用户信息">

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<li><a href="${base}/home/account/basic">基本信息</a></li>
			<li class="active"><a href="${base}/home/account/avatar">修改头像</a></li>
			<li><a href="${base}/home/account/password">修改密码</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="message">
			<#include "/default/inc/action_message.ftl"/>
		</div>
		<form class="form-horizontal" action="avatar" method="post" enctype="multipart/form-data">
			<input type="hidden" id="x" name="x" value=""/>
			<input type="hidden" id="y" name="y" value=""/>
			<input type="hidden" id="width" name="width" value=""/>
			<input type="hidden" id="height" name="height" value=""/>

			<div class="upload-btn">
				<label>
					<span>点击选择一张图片</span>
					<input id="upload_btn" type="file" name="file" accept="image/*" title="点击添加图片">
				</label>
			</div>
			<div class="update_ava">
				<img src="${profile.avatar}" id="target" alt="[Change Avatar]" />
			</div>
            <div class="form-group">
                <div class="text-center">
                    <span style="color: red;">提示：截取图片宽高需大于100px</span>
                </div>
            </div>
			<div class="form-group">
				<div class="text-center">
					<button type="submit" class="btn btn-primary">提交</button>
				</div>
			</div>
		</form>
	</div><!-- /panel-content -->
</div><!-- /panel -->

<script type="text/javascript">
    seajs.use('avatar');
</script>
</@ui_simple>