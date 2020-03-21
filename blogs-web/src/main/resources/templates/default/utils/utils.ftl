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
    <span class="label label-hot">推荐</span>
    </#if>
    <#list groupsCache as group>
       <span class="label label-cream"><#if (row.belongGroup == group.id)>${group.groupValue}</#if></span>
    </#list>
</#macro>

<#macro albShow2 row att>
<a title="${row.title}" href="${att.original}">
    <img src="${site_domain}/static/assets/images/spinner-overlay.png" data-original="${att.preview}"/>
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
        <#--<!--前端图片显示样式&ndash;&gt;-->
        <#--<#if row.albums??>-->
        <#--<!--Start&ndash;&gt;-->
        <#--<div class="thumbs clearfix">-->
            <#--<#list row.albums as alb>-->
                <#--<#if (alb_index < 4) >-->
                <#--<div class="media col-xs-3 col-sm-3 col-md-3">-->
                    <#--<@albShow2 row alb/>-->
                <#--</div>-->
                <#--</#if>-->
            <#--</#list>-->
        <#--</div>-->
        <#--<!--End&ndash;&gt;-->
        <#--</#if>-->
    </div>
    <div class="p-rank clearfix">
        <#--<#if row.user??>-->
        <#--<div class="users">-->
            <#--<a href="${base}/ta/${row.user.id}/1">-->
                <#--<div class="ava">-->
                    <#--<@showAva row.user.avatar "img-circle"/>-->
                <#--</div>-->
            <#--</a>-->
            <#--<div class="info">-->
                <#--<a href="${base}/ta/${row.user.id}/1">-->
                    <#--<strong> ${row.user.nickName}</strong>-->
                <#--</a>-->
                <#--<time> ${row.createTime?string('MM月dd日')}</time>-->
            <#--<time><@timeline_dt row.createTime/></time>-->
            <#--</div>-->
        <#--</div>-->
        <#--</#if>-->
        <div class="counts">
            <time>${row.createTime?string('yyyy-MM-dd HH:mm:ss')}</time>
            <#--<span class="act"><i class="praise_icon"></i>${row.favors}</span>-->
            <#--<span class="act"><i class="comment_icon"></i>${row.comments}</span>-->
        </div>
        <div class="foot-block clearfix">
            <ul class="tags">
                <#--<span class="act">浏览 (<i>${row.views}</i>)</span>-->
                <time><@timeline_dt row.createTime/></time>
                <#list row.tagsArray as tag>
                <li>
                    <a class="tag tag-sm" href="${base}/article/${tag}/1/">${tag}</a>
                </li>
                </#list>
            </ul>
        </div>
    </div>
</div>
</#macro>

<#-- 相关博文列表推荐3 -->
<#macro showBlog3 row>
<div class="stream-item" id="loop-${row.id}">
    <div class="summary">
        <a href="${base}/article/details/${row.id}">
            <div class="title"><h2><strong>${row.title}</strong></h2></div>
            <div class="excerpt wordbreak hidden-xs">${row.summary} </div>
        </a>
    </div>
    <#--<div class="p-rank clearfix">-->
        <#--<div class="counts">-->
            <#--&lt;#&ndash;<time>${row.createTime?string('yyyy-MM-dd HH:mm:ss')}</time>&ndash;&gt;-->
        <#--<span class="act"><i class="praise_icon"></i>${row.favors}</span>-->
        <#--<span class="act"><i class="comment_icon"></i>${row.comments}</span>-->
        <#--</div>-->
        <#--<div class="foot-block clearfix">-->
            <#--<ul class="tags">-->
            <#--&lt;#&ndash;<span class="act">浏览 (<i>${row.views}</i>)</span>&ndash;&gt;-->
                <#--<time><@timeline_dt row.createTime/></time>-->
                <#--<#list row.tagsArray as tag>-->
                <#--<li>-->
                    <#--<a class="tag tag-sm" href="${base}/article/${tag}/1/">${tag}</a>-->
                <#--</li>-->
                <#--</#list>-->
            <#--</ul>-->
        <#--</div>-->
    <#--</div>-->
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
                        <time><@timeline_dt row.createTime/></time>
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
                    <span class="act">浏览 (<i>${row.views}</i>)</span>
                    <#list row.tagsArray as tag>
                        <li>
                            <a class="tag tag-sm" href="${base}/article/${tag}/1">${tag}</a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </#if>
</div>
</#macro>

<#macro timeline_dt datetime>
    <#assign ct = (.now?long-datetime?long)/1000>
    <#if ct gte 31104000>${(ct/31104000)?int}年前
        <#t><#elseif ct gte 2592000>${(ct/2592000)?int}个月前
        <#t><#elseif ct gte 86400*2>${(ct/86400)?int}天前
        <#t><#elseif ct gte 86400>昨天
        <#t><#elseif ct gte 3600>${(ct/3600)?int}小时前
        <#t><#elseif ct gte 60>${(ct/60)?int}分钟前
        <#t><#elseif ct gt 0>${ct?int}秒前
        <#t><#else>刚刚
    </#if>
</#macro>


<#macro pager url p spans>
    <#if (p.total > 0)>
        <#if (url?index_of("?") == -1)>
            <#local cURL = (url + "/") />
        <#else>
            <#local cURL = (url + "&pn=") />
        </#if>

        <#local size = spans * 2 + 1/>
        <#local number = p.number/>
        <#local totalPages = p.pages/>
        <#local startPage = 1/>
        <#local endPage = size/>

        <#if (number <= 1)>
            <#local number = 1/>
        </#if>
        <#if (number >= totalPages)>
            <#local number = totalPages/>
        </#if>

        <#if (number - 1 >= spans)>
            <#local startPage = number - spans/>
        </#if>

        <#if (totalPages > size)>
            <#if (totalPages - number >= spans)>
                <#if (number > spans)>
                    <#local endPage = number + spans/>
                </#if>
            <#else>
                <#if (number - 1 >= spans)>
                    <#local startPage = totalPages - size + 1/>
                </#if>
                <#local endPage = totalPages/>
            </#if>
        <#else>
            <#local endPage = totalPages/>
        </#if>

        <ul class="pagination">
            <#if (number > 1)>
                <li><a class="prev" href="${cURL}${number - 1}">&nbsp;<i class="fa fa-angle-left"></i>&nbsp;</a></li>
            </#if>

            <#list startPage..endPage as i>
                <@pagelink number, i, cURL />
            </#list>

            <#if (number < totalPages)>
                <li><a href="${cURL}${number + 1}">&nbsp;<i class="fa fa-angle-right"></i>&nbsp;</a></li>
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