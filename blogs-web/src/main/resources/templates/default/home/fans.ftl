<#include "/default/utils/layout.ftl"/>
<#include "/default/utils/utils.ftl"/>

<@ui_home "我的主页">
<div class="shadow-box">
    <div class="filter">
        <ul class="">
            <li><a href="${base}/home/follows/1">我的关注</a></li>
            <li><a class="active" href="${base}/home/fans/1">我的粉丝</a></li>
        </ul>
    </div>
    <!-- tab panes -->
    <div class="stream-list">
        <#list page.items as row>
            <div class="stream-item" id="loop-${row.user.id}">
                <div class="blog-rank">
                    <div class="user" title="${row.user.nickName}">
                        <a href="${base}/ta/${row.user.id}/1">
                            <@showAva row.user.avatar "img-circle"/>
                        </a>
                    </div>
                </div>
                <div class="summary">
                    <h2 class="title">${row.user.nickName}</h2>
                    <div class="foot-block clearfix">
                        <div class="author">
                            <span>文章数 ${row.user.posts}</span>
                            <span>评论数 ${row.user.comments}</span>
                        </div>
                    </div>
                </div>
            </div>
        </#list>

        <#if page.items?size == 0>
        <div class="stream-item">
            <i class="fa fa-info-circle fa-lg"></i> 还没有人关注您!
        </div>
        </#if>

    </div>
</div>
<div class="text-center clr">
    <@pager "/home/fans" page 3 />
</div>
</@ui_home>