<div class="widget-box shadow-box">
    <div class="title">
        <h3>
            <i class="fa fa-area-chart"></i> 热门文章
            <a href="${base}/index/20/1" class="rrh-refresh"><i class=""></i>更多</a>
        </h3>

    </div>
    <ul class="box-list" id="hots">
        <li class="text-center"><img src="/static/assets/images/spinner.gif"></li>
    </ul>
</div>

<div class="widget-box shadow-box">
    <div class="title">
        <h3><i class="fa fa-users "></i> 热门用户</h3>
    </div>
    <ul id="hotuser" class="hotusers">

        <img src="/static/assets/images/spinner.gif">

    </ul>
</div>

<div class="widget-box shadow-box">
    <div class="title">
        <h3><i class="fa fa-bars"></i> 最新发布
            <a href="${base}/index/40/1" class="rrh-refresh">更多</a></h3>
    </div>
    <ul class="box-list" id="latests">
        <li class="text-center"><img src="/static/assets/images/spinner.gif"></li>
    </ul>
</div>


<script type="text/javascript">
    var hot_li_template = '<li><div class="li-out"><span class="last"><i>{0}</i></span><span class="name"><a  href="{1}">{2}</a></span><span class="nums">{3}</span></div></li>'
    var latest_li_template = '<li><div class="li-out"><span class="name"><a  href="{1}">{2}</a></span><span class="nums">{3}</span></div></li>'
    var hotUser_li_template = '<li class=""><a  href="{1}"><img src="/static/{0}" class="img-circle" title="{2}"/></a></li>'

    seajs.use('sidebox', function (sidebox) {
        sidebox.init({
            latestUrl: '${base}/api/free/latests.json',
            hotUrl: '${base}/api/free/hottests.json',
            hotTagUrl: '${base}/api/free/hottags.json',
            hotUserUrl: '${base}/api/free/hotusers.json',

            // callback
            onLoadHot: function (i, data) {
                var url = '${base}/article/' + data.id;
                var item = jQuery.format(hot_li_template, i + 1, url, data.title, numberScale(data.views));
                return item;
            },
            onLoadLatest: function (i, data) {
                var url = '${base}/article/' + data.id;
                var item = jQuery.format(latest_li_template, i + 1, url, data.title, numberScale(data.views));
                return item;
            },
            onLoadHotUser: function (i, data) {
                var url = '${base}/ta/' + data.id + '/1';
                var item = jQuery.format(hotUser_li_template, data.avatar, url, data.nickName, data.fans);
                return item;
            }
        });
    });
</script>