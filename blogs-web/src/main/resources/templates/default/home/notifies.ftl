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
                    <div class="user" title="${row.user.nickName}">
                        <a href="${base}/ta/${row.user.id}/1">
                            <@showAva row.user.avatar "img-circle"/>
                        </a>
                    </div>
                </div>
                <div class="summary">
                    <h2 class="title">
                        <a href="${base}/ta/${row.user.id}/1">
                            ${row.user.nickName}
                        </a>
                    </h2>
                    <div class="excerpt wordbreak">
                        <#if (row.event == 1)>
                            喜欢了你的文章 - <a href="${base}/article/${row.article.id}"><b>${row.article.title}</b></a>
                        <#elseif (row.event == 2)>
                            关注了你, 你的粉丝+1
                        <#elseif (row.event == 3)>
                            评论了你的文章 - <a href="${base}/article/${row.article.id}"><b>点击查看详情</b></a>
                        <#elseif (row.event == 4)>
                            回复了你的评论 - <a href="${base}/article/${row.article.id}"><b>点击查看详情</b></a>
                        </#if>
                    </div>
                    </h2>
                    <div class="foot-block clearfix">
                        <div class="author">
                            <#--<time>${timeAgo(row.created)}</time>-->
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
    <@pager "home/notifies" page 5 />
</div>
</@ui_home>