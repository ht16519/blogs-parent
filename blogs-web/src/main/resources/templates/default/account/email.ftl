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
                        <#--<button type="button" class="btn btn-primary" onclick="getCode()">获取激活码</button>-->
                        <button type="button" class="btn btn-primary"  data-toggle="modal"
                                data-target="#editModal">获取激活码</button>
					</div>
				</div>
                <div class="form-group">
                    <label class="control-label col-lg-3" for="email">邮箱激活码</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" name="verifyCode" value="${verifyCode}" maxlength="6" data-required data-description="number">
                    </div>
                    <div >
                    	<p class="help-block">获取激活码，提交完成邮箱验证</p>
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

<!-- 编辑窗口 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">获取激活码</h3>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-striped" width="400px">
                    <tr>
                        <img id="codeImage" alt="验证码" title="点击刷新" onclick="refreshCode()" src="${base}/article/codeImage.jpg" />
                    </tr>
                    <tr>
                        <input id="emailSecurityCode" class="form-control" placeholder="请输入图中验证码">
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" onclick="getCode()">获取</button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function refreshCode(){
            $('#codeImage').attr('src', '${base}/article/codeImage.jpg');
        }
        function getCode() {
            var email = $("#email").val();
            var code = $("#emailSecurityCode").val();
            jQuery.getJSON('${base}/home/account/email/send.json/' + email '/' + code, function (ret) {
                if (ret.code == 0) {
                    layer.msg('邮件已发送至您的邮箱，请注意查收！', {icon: 1});
                }else {
                    layer.msg(ret.msg, {icon: 5});
                }
            });
        }
    </script>
</div>

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