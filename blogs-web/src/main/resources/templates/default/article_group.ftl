<#include "/default/utils/layout.ftl"/>
<#include "/default/utils/utils.ftl"/>

<@ui_ltr>
    <div class="shadow-box">
    	<!-- tab -->
    	<div class="filter">
    		<ul class="">
                <li><a class="active" href="#"><i class="fa fa-th-large"></i>
					<#list groupsCache as row>
					    <#if (row.id == groupId)>${row.groupValue}</#if>
					</#list>
				</a></li>
    		</ul>
    	</div>
    	<!-- tab end -->
    	<!-- tab panes -->
    	<div class="stream-list p-stream" id="article-page">
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
    	<#assign url = "/article/g/"+ groupId>
    	<@pager url page 3 />
    </div>
</@ui_ltr>