<#include "/default/utils/layout.ftl"/>
<@ui_simple "修改用户信息">

<script type="text/javascript" src="/static/assets/vendors/validate/jquery-validate.js"></script>

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<li class="active"><a href="${base}/home/account/basic">基本信息</a></li>
			<li><a href="${base}/home/account/avatar">修改头像</a></li>
			<li><a href="${base}/home/account/password">修改密码</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="message">
			<#include "/default/inc/action_message.ftl"/>
		</div>
		<div class="tab-pane active" id="profile">
			<form id="pf" action="${base}/home/account/email" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-lg-3" for="email">邮箱地址</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" id="email" name="email" value="${profile.email}" maxlength="20" data-required data-conditional="email" data-description="email" data-describedby="message">
						<#--<p class="help-block">修改后将会重新发送验证邮件.</p>-->
					</div>
                    <div class="col-lg-2">
                        <button type="button" class="btn btn-primary" onclick="getCode()">获取验证码</button>
					</div>
				</div>
                <div class="form-group">
                    <label class="control-label col-lg-3" for="email">验证码</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" name="verifyCode" value="${verifyCode}" maxlength="6" data-required data-description="number">
                    </div>
                    <div class="text-center">
                    	<p class="help-block">点击"获取验证码"按钮，填入验证码，提交完成邮箱验证</p>
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
function getCode() {
    var email = $("#email").val();
    jQuery.getJSON('${base}/home/account/email/send.json/' + email, function (ret) {
        if (ret.code == 0) {
            layer.msg('邮件已发送至您的邮箱，请注意查收！', {icon: 1});
        }else {
            layer.msg(ret.msg, {icon: 5});
        }
    });
}

$(function () {
	$('#pf').validate({
        onKeyup : true,
        onChange : true,
        eachValidField : function() {
            $(this).closest('div').removeClass('has-error').addClass('has-success');
        },
        eachInvalidField : function() {
            $(this).closest('div').removeClass('has-success').addClass('has-error');
        },
        conditional : {
            email : function() {
                return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($(this).val());
            }
        },
        description : {
            email : {
                conditional : '<div class="alert alert-danger">邮箱格式不合法</div>'
            }
        }
	});
});
</script>
</@ui_simple>