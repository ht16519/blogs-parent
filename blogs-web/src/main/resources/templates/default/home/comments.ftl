<#include "/default/utils/layout.ftl"/>
<#include "/default/utils/utils.ftl"/>

<@ui_home "我的主页">

<div class="shadow-box no-bg">
	<div class="filter">
		<ul class="">
			<li><a class="active" href="${base}/home/comments/1">我的评论</a></li>
		</ul>
	</div>
	<!-- tab panes -->
	<div class="stream-list p-stream no-bg">
		<#list page.items as row>
			<div class="stream-item comment-item" el="loop-${row.id}">
				<h2 class="title" style="color:#F36C52">我说：${row.content}</h2>
				<div class="foot-block clearfix">
					<div class="author">
						<#--<time>${timeAgo(row.createTime)}</time>-->
					</div>
					<div class="pull-right hidden-xs">
						<a class="act" href="javascript:void(0);" data-evt="trash" data-id="${row.id}">删除</a>
					</div>
				</div>
				<div class="arrow"></div>
			</div>
			<#if row.articleId??>
				<div class="stream-item" id="loop-${row.articleId}">
					<div class="summary">
						<a href="${base}/article/details/${row.articleId}">
							<div class="title"><#if (row.featured > 0 )>
								<span class="label label-danger">推荐</span>
							</#if><h2>${row.title}</h2></div>
							<div class="excerpt wordbreak hidden-xs">${row.summary} </div>
						</a>
					</div>

                    <div class="p-rank clearfix">
                        <div class="users">
                            <a href="${base}/ta/${row.userId}/1">
                                <div class="ava">
                    				<@showAva row.avatar "img-circle"/>
                                </div>
                            </a>
                            <div class="info">
                                <a href="${base}/ta/${row.userId}/1">
                                    <strong> ${row.nickName}</strong>
                                </a>
                                <time> ${row.articleTime?string('MM月dd日')}</time>
							<#--TODO加上时间 <time> ${timeAgo(row.createTime)}</time>-->
                            </div>

                        </div>
                        <div class="counts">
                            <span class="act"><i class="praise_icon"></i>${row.favors}</span>
                            <span class="act"><i class="comment_icon"></i>${row.comments}</span>
                        </div>

                        <div class="foot-block clearfix">
                            <ul class="tags">
							<#list row.tagsArray as tag>
								<li>
									<a class="tag tag-sm" href="${base}/tag/${tag}/">${tag}</a>
								</li>
							</#list>
                            </ul>
                        </div>
                    </div>
                </div>
			<#else>
				<div class="stream-item">文章已删除</div>
			</#if>

			<#if row_index < page.items?size>
                <div class="stream-blank"></div>
			</#if>
		</#list>

		<#if page.items?size == 0>
		<div class="stream-item">
			<i class="fa fa-info-circle fa-lg"></i> 您还没发表过评论!
		</div>
		</#if>

	</div>
</div>
<div class="text-center clr">
	<@pager "/home/comments" page 3 />
</div>

<script type="text/javascript">
$(function() {
	$('a[data-evt=trash]').click(function () {
		var id = $(this).attr('data-id');
		layer.confirm('确定删除此项吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
			jQuery.getJSON('${base}/api/comment/delete.json/' + id, function (ret) {
				layer.msg(ret.msg, {icon: 1});
				if (ret.code == 0) {
					var el = $('div[el=loop-' + id + ']');
                    el.next().remove();
                    el.next().remove();
                    el.remove();
				}
			});
        }, function(){

        });
	});
})
</script>
</@ui_home>