<#include "/default/utils/layout.ftl"/>
<#include "/default/utils/utils.ftl"/>

<@ui_ltr site_name>
<!--推荐展示-->
<div class="content">
    <div class="recommend hidden-xs hidden-sm"">
        <ul>
			<#--<@banner>-->
				<#--<#list page as row>-->
                    <#--<li <#if row_index == 0> class="large" </#if>>-->
                        <#--<a href="${base}/view/${row.id}">-->
							<#--<@albShow row.albums[0]/>-->
                            <#--<h4>${row.title}</h4>-->
                        <#--</a>-->
                    <#--</li>-->
				<#--</#list>-->
			<#--</@banner>-->
        </ul>
    </div>
</div>

<#--<@contents group=0>-->
    <div class="shadow-box">
    	<!-- tab -->
    	<div class="filter">
    		<ul class="">
    			<li><a <#if ord == '40'> class="active" </#if> href="${base}/index/40/1">最新的</a></li>
    			<li><a <#if ord == '20'> class="active" </#if> href="${base}/index/20/1">热门的</a></li>
    		</ul>
    	</div>
    	<!-- tab end -->
    	<!-- tab panes -->
    	<div class="stream-list p-stream">
			<#list page.items as row>
    			<@showBlog row/>
			</#list>

    		<#if  page.items?size == 0>
    		<div class="stream-item">
    			该目录下还没有内容!
    		</div>
			</#if>
    	</div>
    </div>
    <div class="text-center clr">
    	<#assign url = "index/"+ ord>
    	<@pager url page 5 />
    </div>
<#--</@contents>-->

</@ui_ltr>