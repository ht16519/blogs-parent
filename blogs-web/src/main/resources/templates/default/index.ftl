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
                        <#--<a href="${base}/article/${row.id}">-->
							<#--<@albShow row.albums[0]/>-->
                            <#--<h4>${row.title}</h4>-->
                        <#--</a>-->
                    <#--</li>-->
				<#--</#list>-->
			<#--</@banner>-->
        </ul>
    </div>
</div>

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
    	<#--<#assign url = "/index/"+ ord>-->
    	<#--<@pager url page 3 />-->
		<div id="example" style="text-align: center"> <ul id="pageLimit"></ul> </div>
    </div>

<script type="text/plain" id="article_template">
<div class="stream-item" id="loop-{0}">
	<div class="p-rank clearfix">
		<div class="users">
			<a href="${base}/ta/{6}/1">
				<div class="ava">
					<img class="img-circle" src="{7}"/>
				</div>
				<div class="info">
					<strong>{8}</strong><br/>
					<time> {3}</time>
				<#--<time>${timeAgo(row.createTime)}</time>-->
				</div>
			</a>
		</div>
		<div class="counts">
			<span class="act"><i class="praise_icon"></i>{4}</span>
			<span class="act"><i class="comment_icon"></i>{5}</span>
		</div>
	</div>
	<div class="summary">
		<a href="${base}/article/details/{0}">
			<div class="title">{11}<h2><strong>{1}</strong></h2></div>
			<div class="excerpt wordbreak hidden-xs">{2}</div>
		</a>
		<!--前端图片显示样式-->
		{9}
		<div class="foot-block clearfix">
			<ul class="tags">{10}</ul>
		</div>
	</div>
</div>
</script>

<script type="text/plain" id="albums_template">
<div class="media col-xs-4 col-sm-4 col-md-4">
    <a title="{0}" href="{1}">
        <img src="{2}" data-original="{2}"/>
    </a>
</div>
</script>

<script type="text/plain" id="tags_template">
<li>
    <a class="tag tag-sm" href="${base}/tag/{0}/">{0}</a>
</li>
</script>

<script type="text/javascript">
    function searchBtn() {
        var condition = $('#searchText').val();
        jQuery.ajax({
            url: '${base}/api/free/index',
            data: {'condition': condition},
            type :  "POST",
            cache : false,
            async: false,
            dataType:'json',
            error : function(i, g, h) {
                layer.msg('发生错误', {icon: 2});
            },
            success: function(ret){
                if (ret.code == 0) {
                    var data = ret.data;
                    var $list = $("#article-page");
                    var template = $('#article_template')[0].text;
                    var tagsTemplate = $('#tags_template')[0].text;
                    var albumsTemplate = $('#albums_template')[0].text;

                    var items = data.items;
                    for(var i = 0; i < items.length; i++){
                        var row = items[i];
                        var user = row.user;
                        var albums = row.albums;
                        var tags = row.tagsArray;
                        var albumsHtml = '';
                        if (!(albums === null) && albums.length > 0) {
                            albumsHtml = '<div class="thumbs clearfix">';
                            for (var k = 0; k < albums.length; k++) {
                                var att = albums[k];
                                var albumsTemp = jQuery.format(albumsTemplate, row.title, att.original, att.preview);
                                albumsHtml += albumsTemp;
                            }
                            albumsHtml += '</div>';
                        }
                        var tagsHtml = '';
                        if (!(tags === null) && tags.length > 0) {
                            for(var j = 0; j < tags.length; j++){
                                var tagsTemp = jQuery.format(tagsTemplate, tags[j]);
                                tagsHtml += tagsTemp;
                            }
                        }
                        var labelHtml = '';
                        if (row.type == 1){
                            labelHtml = '<span class="label label-source">原创</span>&nbsp;';
                        }else{
                            labelHtml = '<span class="label label-not-source">转载</span>&nbsp;';
                        }
                        if (row.featured > 0){
                            labelHtml += '<span class="label label-hot">热门</span>&nbsp;';
                        }
                        if (row.featured > 0){
                            labelHtml += '<span class="label label-cream">精帖</span>&nbsp;';
                        }
                        var html = jQuery.format(template,
                                row.id,
                                row.title,
                                row.summary,
                                row.createTime,
                                row.favors,
                                row.comments,
                                user.id,
                                user.avatar,
                                user.nickName,
                                albumsHtml,
                                tagsHtml,
                                labelHtml
							);
                        $list.append(html);
					}
                } else {
                    layer.msg(ret.msg, {icon: 5});
                }
            }
        });
    }
</script>

<script>

    $('#pageLimit').bootstrapPaginator({
        currentPage: 1,//当前的请求页面。
        totalPages: 20,//一共多少页。
        size:"normal",//应该是页眉的大小。
        bootstrapMajorVersion: 3,//bootstrap的版本要求。
        alignment:"right",
        numberOfPages:5//一页列出多少数据。
    });
</script>
</@ui_ltr>