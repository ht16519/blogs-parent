<#include "/default/utils/layout.ftl"/>
<@ui_simple "邮箱验证">

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
					</div>
                    <div class="col-lg-2">
                        <button type="button" class="btn btn-primary" onclick="getCodeDialog();">获取激活码</button>
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

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" id="emailCodeModalDialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">获取激活码</h3>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-striped" id="emailCodeTable">
                    <tr>
                        <td><img id="emailCodeImage" alt="验证码" title="点击刷新" onclick="refreshCode()" src="${base}/article/codeImage.jpg" /></td>
                        <td><input id="emailSecurityCode" style="height: 40px;" class="form-control border" placeholder="请输入验证码" type="text" data-required></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" onclick="getEmailCode()">获取</button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    function getCodeDialog() {
        $('#myModal').modal();
    }

    function refreshCode() {
        $('#emailCodeImage').attr('src', '${base}/article/codeImage.jpg');
    }

    function getEmailCode() {
        var email = $('#email').val();
        var code = $('#emailSecurityCode').val();
        jQuery.ajax({
            url: '${base}/home/account/email/send.json',
            data: {'email': email, 'code': code},
            type :  "POST",
            cache : false,
            async: false,
            dataType:'json',
            error : function() {
                layer.msg('发送邮件失败，请检查邮箱是否正确！', {icon: 2});
            },
            success: function(ret){
                if(ret){
                    if (ret.code == 0) {
                        layer.msg(ret.msg, {icon: 1});
                    } else {
                        layer.msg(ret.msg, {icon: 5});
                    }
                }
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