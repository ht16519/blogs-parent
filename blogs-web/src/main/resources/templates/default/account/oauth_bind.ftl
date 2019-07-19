<#include "/default/utils/layout.ftl"/>
<@ui_simple "修改用户信息">

<script type="text/javascript" src="/static/assets/vendors/validate/jquery-validate.js"></script>

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<li><a href="${base}/home/account/basic">基本信息</a></li>
			<#if (bindAccount > 0)>
			<li class="active"><a href="${base}/home/account/bind">绑定账号</a></li>
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
		<div class="tab-pane active" id="passwd">
            <form id="pw" action="${base}/home/account/bind" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-lg-3" for="password">用户名</label>
                    <div class="col-lg-4">
                        <input maxlength="16" class="form-control border" name="userName" value="" placeholder="用户名" type="text" data-required data-conditional="username" data-description="username" data-describedby="message">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3" for="password">昵称</label>
                    <div class="col-lg-4">
                        <input maxlength="9" class="form-control border" name="nickName" value="${profile.nickName}" placeholder="昵称" type="text" data-required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3" for="password">邮箱</label>
                    <div class="col-lg-4">
                        <input maxlength="20" class="form-control border" name="email" value="${profile.email}" placeholder="邮箱地址" type="text" data-required data-conditional="email" data-description="email" data-describedby="message">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3" for="password2">密码</label>
                    <div class="col-lg-4">
                        <input id="password" class="form-control border" maxlength="18" name="password" placeholder="密码" type="password" data-required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3" for="password2">确认密码</label>
                    <div class="col-lg-4">
                        <input type="password" class="form-control" name="rePassword" data-required placeholder="请再输入一遍新密码" maxlength="16" data-conditional="confirm" data-describedby="message" data-description="passwd">
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
    $(function(){
        $('form').validate({
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
                },
                email : function() {
                    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($(this).val());
                },
                username : function() {
                    return /^[a-z][a-z_0-9]{4,18}$/i.test($(this).val());
                }
            },
            description : {
                confirm : {
                    conditional : '<div class="alert alert-danger">两次输入的密码不一致</div>'
                },
                email : {
                    conditional : '<div class="alert alert-danger">邮箱格式不合法</div>'
                },
                username : {
                    conditional : '<div class="alert alert-danger">只能是字母/字母+数字的组合,不少于5位</div>'
                }
            }
        });
    })
</script>
</@ui_simple>