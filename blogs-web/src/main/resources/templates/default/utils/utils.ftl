<#-- 用户头像显示 -->
<#macro showAva avatar clazz>
    <img class="${clazz}" src="${avatar}"/>
</#macro>

<#macro showGroup row>
    <#if (row.type == 1 )>
    <span class="label label-source">原创</span>
    <#else>
    <span class="label label-not-source">转载</span>
    </#if>
    <#if (row.featured > 0 )>
    <span class="label label-hot">热门</span>
    </#if>
    <#if (row.featured > 0 )>
    <span class="label label-cream">精帖</span>
    </#if>
</#macro>

<#macro albShow2 row att>
<a title="${row.title}" href="${att.original}">
    <img src="/static/assets/images/spinner-overlay.png" data-original="${att.preview}"/>
</a>
</#macro>

<#-- 博文列表显示 -->
<#macro showBlog2 row>
<div class="stream-item" id="loop-${row.id}">
    <div class="summary">
        <a href="${base}/article/details/${row.id}">
            <div class="title"><@showGroup row/><h2>${row.title}</h2></div>
            <div class="excerpt wordbreak hidden-xs">${row.summary} </div>
        </a>
        <!--前端图片显示样式-->
        <#if row.albums??>
        <!--Start-->
        <div class="thumbs clearfix">
            <#list row.albums as alb>
                <#if (alb_index < 4) >
                <div class="media col-xs-3 col-sm-3 col-md-3">
                    <@albShow2 row alb/>
                </div>
                </#if>
            </#list>
        </div>
        <!--End-->
        </#if>
    </div>

    <div class="p-rank clearfix">
        <#if row.user??>
        <div class="users">
            <a href="${base}/ta/${row.user.id}/1">
                <div class="ava">
                    <@showAva row.user.avatar "img-circle"/>
                </div>
            </a>
            <div class="info">
                <a href="${base}/ta/${row.user.id}/1">
                    <strong> ${row.user.nickName}</strong>
                </a>
                <time> ${row.createTime?string('MM月dd日')}</time>
                <#--TODO加上时间 <time> ${timeAgo(row.createTime)}</time>-->
            </div>
        </div>
        </#if>
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
</#macro>

<#-- 博文列表显示2 -->
<#macro showBlog row>
<div class="stream-item" id="loop-${row.id}">
    <#if row??>
        <div class="p-rank clearfix">
            <#if row.user??>
            <div class="users">
                <a href="${base}/ta/${row.user.id}/1">
                    <div class="ava">
                        <@showAva row.user.avatar "img-circle"/>
                    </div>
                    <div class="info">
                        <strong>${row.user.nickName}</strong><br/>
                        <time> ${row.createTime?string('yyyy-MM-dd')}</time>
                    <#--<time>${timeAgo(row.createTime)}</time>-->
                    </div>
                </a>
            </div>
            </#if>
            <div class="counts">
                <span class="act"><i class="praise_icon"></i>${row.favors}</span>
                <span class="act"><i class="comment_icon"></i>${row.comments}</span>
            </div>
        </div>
        <div class="summary">
            <a href="${base}/article/details/${row.id}">
                <div class="title"><@showGroup row/><h2><strong>${row.title}</strong></h2></div>
                <div class="excerpt wordbreak hidden-xs">${row.summary}</div>
            </a>
            <!--前端图片显示样式-->
            <#if row.albums??>
                <!--Start-->
                <div class="thumbs clearfix">
                    <#list row.albums as alb>
                        <#if (alb_index <= 3)>
                            <div class="media col-xs-4 col-sm-4 col-md-4">
                                <@albShow2 row alb/>
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
            </div>
        </div>
    </#if>
</div>
</#macro>

<#macro pager url p spans>
<#if (p.total > 0)>
    <#local pageNum = p.number/>
    <#local pageSize = p.pages/>
    <#if (url?index_of("?") == -1)>
        <#local cURL = (url + "/") />
    <#else>
        <#local cURL = (url + "&pn=") />
    </#if>


<ul class="pagination">
    <#if (pageNum > 1)>
        <#local prev = pageNum - 1 />
        <li><a class="prev" href="${cURL}${prev}">&nbsp;<i class="fa fa-angle-left"></i>&nbsp;</a></li>
    </#if>

    <#if (pageNum > spans)>
        <#local startPage = pageNum - spans/>
    <#else>
        <#local startPage = 1 />
    </#if>

    <#if (pageSize - pageNum > spans)>
        <#local size = pageNum + spans/>
        <#if (startPage == 1)>
            <#local size = pageNum + spans * 2/>
        </#if>
    <#else>
        <#local size = pageSize />
    </#if>

    <#list startPage..size as i>
        <@pagelink pageNum, i, cURL />
    </#list>

    <#if (pageNum lt pageSize)>
        <#local next = pageNum + 1/>
        <li><a href="${cURL}${next}">&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</a></li>
    </#if>
</ul>
</#if>
</#macro>

<#macro pagelink pageNo idx url>
    <#if (pageNo == idx)>
    <li class="active"><a href="javascript:void(0);"><span>${idx}</span></a></li>
    <#else>
    <li><a href="${url}${idx}">${idx}</a></li>
    </#if>
</#macro>