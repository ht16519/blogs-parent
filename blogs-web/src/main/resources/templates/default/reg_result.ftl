<#include "/default/utils/layout_login.ftl"/>

<@layout "注册成功">

<div class="login">
    <div class="vegas-overlay"
         style="opacity: 0.2; margin: 0px; padding: 0px; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; background-image: url(${site_domain}/static/assets/images/overlay.png); z-index: -1;"></div>

    <a href="${base}/index">
        <img src="${site_domain}/static/assets/images/logo/logo90.jpg" height="72" width="72">
    </a>
    <hr>
    <form action="login" method="post">
        <div id="message"><#include "/default/inc/action_message.ftl"/></div>
        <div class="text-center">
            <a href="${base}${data.link}" class="btn btn-success">${data.text}</a>
        </div>
    </form>
</div>

</@layout>