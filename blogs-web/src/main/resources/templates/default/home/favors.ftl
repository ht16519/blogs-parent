<#include "/default/utils/layout.ftl"/>
<#include "/default/utils/utils.ftl"/>

<@ui_home "我的主页">

<div class="shadow-box">
	<div class="filter">
		<ul class="">
			<li><a class="active" href="${base}/home/favors/1">我的喜欢</a></li>
		</ul>
	</div>
	<!-- tab panes -->
	<div class="stream-list">
		<#list page.items as row>
		<div class="stream-item" id="loop-${row.id}">
			<#if row??>
				<div class="p-rank clearfix">
					<div class="users">
						<a href="${base}/ta/${row.user.id}/1">
							<div class="ava">
								<@showAva row.user.avatar "img-circle"/>
							</div>
							<div class="info">
								<strong>${row.user.nickName}</strong>
								<#--<time>${timeAgo(row.createTime)}</time>-->
							</div>
						</a>
					</div>
					<div class="counts">
						<span class="act"><i class="praise_icon"></i>${row.favors}</span>
						<span class="act"><i class="comment_icon"></i>${row.comments}</span>
					</div>
				</div>
				<div class="summary">
					<a href="${base}/article/${row.id}">
						<div class="title"><@showGroup row/><h2>${row.title}</h2></div>
						<div class="excerpt wordbreak hidden-xs">${row.summary} </div>
					</a>
					<!--前端图片显示样式-->
					<#if row.article.albums??>
						<!--Start-->
						<div class="thumbs clearfix">
							<#list row.albums as alb>
								<#if (alb_index <= 3)>
									<div class="media col-xs-4 col-sm-4 col-md-4">
										<#--<a title="${row.article.title}" href="<@resource src=alb.original/>">-->
										<a title="${row.title}" href="${"/static" + alb.original}">
											<@albShow2 row alb/>
										</a>
									</div>
								</#if>
							</#list>
						</div>
						<!--End-->
					</#if>

					<div class="foot-block clearfix">
						<ul class="tags">
							<#list row.tagsArray as tag>
								<li>
									<a class="tag tag-sm" href="${base}/tag/${tag}/">${tag}</a>
								</li>
							</#list>
						</ul>
                        <div class="pull-right hidden-xs">
                            <a class="act" href="javascript:void(0);" data-evt="unfavor" data-id="${row.id}">取消喜欢</a>
                        </div>
					</div>
				</div>
			<#else>
                <div class="stream-item">文章已删除</div>
			</#if>
		</div>
		</#list>

		<#if page.items?size == 0>
            <div class="stream-item">
                <i class="fa fa-info-circle fa-lg"></i> 您还没收藏过文章!
            </div>
		</#if>

	</div>
</div>
<div class="text-center clr">
	<@pager "favors" page 5 />
</div>

<script type="text/javascript">
$(function() {
	$('a[data-evt=unfavor]').click(function () {
		var id = $(this).attr('data-id');
		layer.confirm('确定删除此项吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
			jQuery.getJSON('${base}/api/unfavor.json/' + id, function (ret) {
				layer.msg(ret.msg, {icon: 1});
				if (ret.code == 0) {
					$('#loop-' + id).fadeOut();
					$('#loop-' + id).remove();
				}
			});

        }, function(){

        });
	});
})
</script>
</@ui_home>