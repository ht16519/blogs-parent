<#include "/default/utils/layout.ftl"/>
<#include "/default/utils/utils.ftl"/>

<@ui_home "我的主页">

<div class="shadow-box">
    <div class="filter">
        <ul class="">
            <li><a class="active" href="${base}/home/feeds/1">动态</a></li>
        </ul>
    </div>
    <!-- tab panes -->
    <div class="stream-list p-stream">
        <#list page.items as row>
            <#if row??>
                <@showBlog row />
            </#if>
        </#list>

        <#if page.items?size == 0>
            <div class="stream-item">
                <i class="fa fa-info-circle fa-lg"></i> 没有动态,赶紧去关注几个大神吧!
            </div>
        </#if>
    </div>
</div>
<div class="text-center clr">
    <@pager "/home/feeds" page 3 />
    <#--<div id="example" style="text-align: center"> <ul id="pageLimit"></ul> </div>-->
</div>
</@ui_home>
