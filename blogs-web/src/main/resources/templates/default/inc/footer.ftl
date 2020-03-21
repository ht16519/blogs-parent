<#--<style type="text/css">-->
    <#--html {-->
        <#--position: relative;-->
        <#--min-height: 100%;-->
    <#--}-->
    <#--body {-->
        <#--margin-bottom: 60px;-->
    <#--}-->
    <#--.footer1 {-->
        <#--position: absolute;-->
        <#--bottom: 0;  width: 100%;-->
        <#--/* Set the fixed height of the footer here */-->
        <#--height: 60px;-->
        <#--background-color: red;-->
    <#--}-->
<#--</style>-->
<footer class="footer1">
	<div class="footer-nav">
		<div class="container">
			<ul class="about-ul list-inline clearfix">
                <#list footerTops as tag>
                    <li><a href="${base}/affiche/${tag.id}" target="_blank" >${tag.name}</a></li>
                </#list>
			</ul>
		</div>
	</div>
	<div class="container mode-link bg-color">
        <h3 class="t-h3">友情链接</h3>
        <ul class="list-inline">
            <#--<li><a href="http://mtons.com/?copyright" target="_blank" title="Mtons社区">参考Mtons社区</a></li>-->
            <#--<li><a href="https://github.com/ht16519/blogs-parent" target="_blank" title="source code">Source Code</a></li>-->
            <#list friendLinksCache as row>
                <li><a href="${row.url}" target="_blank" title="${row.remark}">${row.siteName}</a></li>
            </#list>
        </ul>
    </div>

	<#--<div class="container copy-right">-->
        <#--<span class="pull-right">Powered By <a href="https://github.com/ht16519" target="_blank">烛火流风</a> V1.0</span>-->
        <#--<span class="pull-right"><a href="${base}/affiche/3">免责申明</a>&nbsp;&nbsp;|&nbsp;&nbsp;</span>-->
	<#--</div>-->

    <div class="container copy-right text-center">
        <span class="center-block">${site_powered_by}&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${base}/affiche/3">免责申明</a></span>
        <span class="center-block">${site_copyright}</span>
        <span class="center-block">${site_icp}</span>
    </div>

</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
    seajs.use('main', function (main) {
        main.init();
    });
</script>

<script src="${site_domain}/static/assets/vendors/widget/source/autoload.js"></script>