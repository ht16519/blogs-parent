<#include "/default/utils/layout.ftl"/>
<#include "/default/utils/utils.ftl"/>

<@ui_home "我的主页">
<div class="shadow-box">
    <div class="filter">
        <ul class="">
            <li><a class="active" href="${base}/home/notifies/1">通知</a></li>
        </ul>
    </div>
    <!-- tab panes -->
    <div class="stream-list">
        <#list page.items as row>
            <div class="stream-item" id="loop-${row.id}">
                <div class="blog-rank">
                <#if (row.user.id > 0)>
                    <div class="user" title="${row.user.nickName}">
                        <a href="${base}/ta/${row.user.id}/1">
                        <@showAva row.user.avatar "img-circle"/>
                        </a>
                    </div>
                <#else>
                    <div class="user" title="系统消息">
                        <img class="img-circle" src="/static/assets/images/logo/logo90.jpg"/>
                    </div>
                </#if>
                </div>
                <div class="summary">
                    <#if (row.user.id > 0)>
                    <h2 class="title">
                        <a href="${base}/ta/${row.user.id}/1">
                        ${row.user.nickName}
                        </a>
                    </h2>
                    <#else>
                    <h2 class="title">
                        系统消息
                    </h2>
                    </#if>
                    <div class="excerpt wordbreak">
                    <#if (row.event == 1)>
                        喜欢了你的文章 - <a href="${base}/article/details/${row.article.id}"><b>${row.article.title}</b></a>
                    <#elseif (row.event == 2)>
                        关注了你, 你的粉丝 +1
                    <#elseif (row.event == 3)>
                        评论了你的文章 - <a href="${base}/article/details/${row.article.id}"><b>点击查看详情</b></a>
                    <#elseif (row.event == 4)>
                        回复了你的评论 - <a href="${base}/article/details/${row.article.id}"><b>点击查看详情</b></a>
                    <#elseif (row.event == 5)>
                        恭喜您注册成功，您的邮箱尚未激活，若忘记密码需通过邮箱找回密码 - <a href="${base}/home/account/active/email"><b>去激活</b></a>
                    <#elseif (row.event == 6)>
                        您的邮箱已经认证成功!
                    <#elseif (row.event == 7)>
                        登录成功！您可以绑定账号信息，以后可以使用账号登录哦 - <a href="${base}/home/account/bind"><b>绑定账号</b></a>
                    <#elseif (row.event == 8)>
                        账号绑定成功！
                    </#if>
                    </div>
                    </h2>
                    <div class="foot-block clearfix">
                        <div class="author">
                            <time><@timeline_dt row.createTime/></time>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <#if page.items?size == 0>
        <div class="stream-item">
            <i class="fa fa-info-circle fa-lg"></i> 您还没有收到通知!
        </div>
        </#if>
    </div>
</div>
<div class="text-center clr">
<@pager "/home/notifies" page 3 />
</div>
</@ui_home>