<#include "/default/utils/layout.ftl"/>
<@ui_ltr>

    <div class="shadow-box">
        <!-- tab -->
        <div class="filter">
            <div class="alert" style="margin-bottom:0">
                <li><span><i class="fa fa-search"></i> 搜索关键字 "<strong><span style="color: red;"><span style="color: red;">${q}</span></span></strong>", 共找到 <span style="color: red;">${page.total}</span> 个结果.</span></li>
            </div>
        </div>
        <!-- tab end -->
        <!-- tab panes -->
        <div class="stream-list p-stream">
			<#list page.items as row>
				<@showBlog2 row/>
			</#list>

			<#if page.items?size == 0>
                <div class="stream-item">
                    该目录下还没有内容!
                </div>
			</#if>
        </div>
    </div>
    <div class="text-center clr">
		<#assign url = "/article/search?q=" + q />
		<@pager url page 3 />
    </div>

	<script>
		$(function () {
			$('input[name=q]', $('#_search_box')).val('${q}');
		});
	</script>

</@ui_ltr>
