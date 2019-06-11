<footer>
	<div class="footer-nav">
		<div class="container">
			<ul class="about-ul list-inline clearfix">
                <#list footerTops as top>
                    <li><a href="${base}/affiche/${top.id}">${top.name}</a></li>
                </#list>
			</ul>
		</div>
	</div>
	<div class="container mode-link">
        <h3 class="t-h3">友情链接</h3>
        <ul class="list-inline">
            <li><a href="http://mtons.com/?copyright" target="_blank" title="Mtons社区">参考Mtons社区</a></li>
            <li><a href="https://github.com/ht16519/blogs-parent" target="_blank" title="source code">Source Code</a></li>
            <#list friendLinks as row>
                <li>
                    <a href="${link.url}" target="_blank" title="${link.remark}">${link.siteName}</a>
                </li>
            </#list>
        </ul>
    </div>

	<div class="container copy-right">
		<span class="pull-right">Powered By <a href="https://github.com/ht16519" target="_blank">烛火流风</a> V1.0</span>
        <span class="pull-right"><a href="${base}/affiche/3">免责申明</a>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
	</div>

</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
    seajs.use('main', function (main) {
        main.init();
    });
</script>