<#include "/default/utils/layout.ftl"/>
<@ui_simple "修改用户信息">

<script type="text/javascript" src="/static/assets/vendors/validate/jquery-validate.js"></script>

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<#if (profile.bingType > 0)>
			<li class="active"><a href="${base}/home/account/bind">绑定账号</a></li>
			<#else>
			<li><a href="${base}/home/account/basic">基本信息</a></li>
			<li><a href="${base}/home/account/password">修改密码</a></li>
			</#if>
			<li><a href="${base}/home/account/avatar">修改头像</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="message">
		<#include "/default/inc/action_message.ftl"/>
		</div>
		<div class="tab-pane active" id="passwd">
			<<form action="register" method="post">
				<label for="id_email">用户名:</label>
				<div id="id_email">
					<input maxlength="16" class="form-control border" name="userName" value="${post.userName}" placeholder="用户名" type="text" data-required data-conditional="username" data-description="username" data-describedby="message">
				</div>
				<label for="id_name">昵称:</label>
				<div id="id_name">
					<input maxlength="9" class="form-control border" name="nickName" value="${post.nickName}" placeholder="昵称" type="text" data-required>
				</div>
				<label for="id_name">邮箱:</label>
				<div id="id_name">
					<input maxlength="20" class="form-control border" name="email" value="${post.email}" placeholder="邮箱地址" type="text" data-required data-conditional="email" data-description="email" data-describedby="message">
				</div>
				<label for="id_password">密码:</label>
				<div id="id_password">
					<input id="password" class="form-control border" maxlength="18" name="password" placeholder="密码" type="password" data-required>
				</div>
				<label for="id_password2">确认密码:</label>
				<div id="id_password2">
					<input maxlength="18" class="form-control border" name="rePassword" placeholder="请再输入一次密码" type="password" data-required data-conditional="confirm" data-describedby="message" data-description="confirm">
				</div>
				<input type="submit" class="btn btn-success border" value="注 册">
			</form>
		</div>
	</div><!-- /panel-content -->
</div><!-- /panel -->

<script type="text/javascript">
$(function () {
	$('#pw').validate({
		onKeyup : true,
		onChange : true,
		eachValidField : function() {
			$(this).closest('div').removeClass('has-error').addClass('has-success');
		},
		eachInvalidField : function() {
			$(this).closest('div').removeClass('has-success').addClass('has-error');
		},
		conditional : {
			confirm : function() {
				return $(this).val() == $('#password').val();
			}
		},
		description : {
			passwd : {
				conditional : '<div class="alert alert-danger">两次输入的密码不一致</div>'
			}
		}
	});
});
</script>
</@ui_simple>