<#include "/default/utils/layout.ftl"/>
<@ui_simple "修改用户信息">
<script type="text/javascript" src="/static/assets/vendors/validate/jquery-validate.js"></script>

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<li class="active"><a href="${base}/home/account/basic">基本信息</a></li>
			<#if (bindAccount > 0)>
			<li><a href="${base}/home/account/bind">绑定账号</a></li>
			<#else>
			<li><a href="${base}/home/account/password">修改密码</a></li>
			</#if>
            <li><a href="${base}/home/account/avatar">修改头像</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="message">
		<#include "/default/inc/action_message.ftl"/>
		</div>
		<div class="tab-pane active" id="profile">
			<form id="pf" action="basic" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-lg-3" for="nickname">昵称</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="nickName" value="${profile.nickName}" maxlength="9" data-required>
					</div>
				</div>
                <div class="form-group">
                    <label class="control-label col-lg-3" for="nickname">性别</label>
                    <div class="col-lg-4">
                        &nbsp;&nbsp;<input type="radio" name="sex" value="1" ${(profile.sex == 1) ? string('checked','')}> 男&nbsp;&nbsp;
                        <input type="radio" name="sex" value="0" ${(profile.sex == 0) ? string('checked','')}> 女
                    </div>
                </div>
				<div class="form-group">
					<label class="control-label col-lg-3" for="email">邮箱地址</label>
					<div class="col-lg-4">
						<span class="form-control">${profile.email}</span>
					</div>
					<div class="col-lg-3" style="padding-top: 6px;">
						<#if (profile.activeEmail == 10)>
							<span class="label label-success">已验证</span>
						<#else>
							<span class="label label-warning">未验证</span>
						</#if>
						<a href="${base}/home/account/email">修改邮箱</a>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-3" for="nickname">个性签名</label>
					<div class="col-lg-6">
						<textarea name="signature" class="form-control" rows="3" maxlength="128">${profile.signature}</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="text-center">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div><!-- /form-actions -->
			</form>
		</div>
	</div><!-- /panel-content -->
</div><!-- /panel -->

<script type="text/javascript">
$(function () {
	$('#pf').validate({
		onKeyup : true,
		onChange : true,
		eachValidField : function() {
			$(this).closest('div').removeClass('has-error').addClass('has-success');
		},
		eachInvalidField : function() {
			$(this).closest('div').removeClass('has-success').addClass('has-error');
		}
	});
});
</script>
</@ui_simple>