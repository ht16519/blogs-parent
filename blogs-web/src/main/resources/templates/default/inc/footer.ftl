<footer>
	<div class="footer-nav">
		<div class="container">
			<ul class="about-ul list-inline clearfix">
				<li><a href="${base}/about">关于我们</a></li>
				<li><a href="${base}/joinus">联系我们</a></li>
				<li><a href="${base}/faqs">常见问题</a></li>
                <#--<li>-->
                    <#--<script>-->
                        <#--var _hmt = _hmt || [];-->
                        <#--(function() {-->
                            <#--var hm = document.createElement("script");-->
                            <#--hm.src = "//hm.baidu.com/hm.js?a029e6c6dddf427f4cbfb2b00d7d5311";-->
                            <#--var s = document.getElementsByTagName("script")[0];-->
                            <#--s.parentNode.insertBefore(hm, s);-->
                        <#--})();-->
                    <#--</script>-->
				<#--</li>-->
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

    <#--<div class="container copy-right">-->
        <#--<span class="pull-right">Powered By <a href="${base}/declare/info" target="_blank">免责申明</a></span>-->
    <#--</div>-->

	<div class="container copy-right">
		<span class="pull-right">Powered By <a href="https://github.com/ht16519" target="_blank">烛火流风</a> V1.0</span>
        <span class="pull-right"><a href="${base}/index/declare/info">免责申明</a>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
	</div>

</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
    seajs.use('main', function (main) {
        main.init();
    });
</script>