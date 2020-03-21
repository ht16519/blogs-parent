<#include "/default/utils/layout_login.ftl"/>

<@layout>
<div class="login">
    <a href="${base}/index">
        <img src="${site_domain}/static/assets/images/logo/logo90.jpg" height="72" width="72">
    </a>
    <h1>离成功还差一步哦!</h1>
    <hr>
    <form action="${base}/login/oauth/bind" method="post">
        <#--<input type="hidden" name="oauthType" value="$!{user.nickName}"/>-->
        <#--<input type="hidden" name="code" value="$!{user.oauthCode}"/>-->
        <#--<input type="hidden" name="avatar" value="$!{user.sex}"/>-->
        <input type="hidden" name="authToken" value="${authToken}"/>
        <div id="message">
            <#if msg??>
                <div class="alert alert-danger">${msg}</div>
            </#if>
        </div>
        <label for="id_email">登录名:</label>
        <div id="id_email">
            <input name="userName" class="form-control border" placeholder="用户名" type="text" data-required>
        </div>
        <label for="id_name">密码:</label>
        <div id="id_name">
            <input name="password" class="form-control border" placeholder="密码" type="password" data-required>
        </div>
        <input type="submit" class="btn btn-success border" value="关联账号">
    </form>
</div>

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
                username : function() {
                    return /^[a-z][a-z_0-9]{4,18}$/i.test($(this).val());
                }
                /*,
                email : function() {
                    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($(this).val());
                }*/
            },
            description : {
                username : {
                    conditional : '<div class="alert alert-danger">只能是字母/字母+数字的组合</div>'
                }
                /*,
                email : {
                    conditional : '<div class="alert alert-danger">邮箱格式不合法</div>'
                }
                */
            }
        });
    })
</script>
</@layout>