<#include "/default/utils/utils.ftl"/>

<!DOCTYPE html>
<html lang="en-US">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${ret.title?default(site_name)}</title>
    <meta name="keywords" content="mtons, ${ret.tags?default(site_keywords)}">
    <meta name="description" content="${ret.summary?default(site_description)}">
<#include "/default/inc/include.ftl"/>
</head>
<body>
<#include "/default/inc/header.ftl"/>
<!--.site-main -->
<div class="wrap" id="wrap">
    <div class="container">
        <div class="row">
            <div class="main clearfix">
                <!-- left -->
                <div class="col-xs-12 col-md-9 side-left">
                    <div class="shadow-box">
                        <h2 class="post-title"><@showGroup ret/><strong>${ret.title}</strong></h2>
                        <div class="clearfix post-other">
                            <time class="pull-left time">${ret.createTime?string('yyyy-MM-dd HH:mm:ss')}</time>
                            <span class="pull-left author">
                                <a class="author-name" href="${base}/ta/${ret.user.id}/1" target="_blank"><span style="color: #78a5f1;">${ret.user.nickName}</span></a>
                            </span>
                            <span class="pull-left time">浏览: ${ret.views}</span>
                            <ul class="tags">
                            <#list ret.tagsArray as tag>
                                <li>
                                    <a class="tag tag-sm" href="${base}/article/${tag}/1">${tag}</a>
                                </li>
                            </#list>
                            </ul>
                            <span class="pull-right action-box"></span>
                        </div>
                        <div class="post-frame">
                            <div class="post-content">
                            ${ret.editor}
                            </div>
                            <div class="post-footer">
                                <div class="tip">分享到：</div>
                                <div class="shares">
                                    <!-- Share Button BEGIN -->
                                    <div class="bdsharebuttonbox bdshare-button-24">
                                        <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                                        <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
                                        <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                                        <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                                    </div>
                                    <script>
                                        window._bd_share_config = {
                                            "common": {
                                                "bdSnsKey": {"tsina": "3554307689"},
                                                "bdText": "",
                                                "bdMini": "2",
                                                "bdMiniList": false,
                                                "bdPic": "",
                                                "bdStyle": "1",
                                                "bdSize": "24"
                                            }, "share": {}
                                        };
                                        with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
                                    </script>
                                    <style>
                                        .bdshare-button-24 a, .bdshare-button-24 .bds_more {
                                            background-image: url("/static/assets/images/btn/icons_0_24.png");
                                            border-radius: 4px;
                                        }

                                        .bdshare-button-style1-24 a, .bdshare-button-style1-24 .bds_more {
                                            padding-left: 24px;
                                        }
                                    </style>
                                    <!-- Share Button END -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- post/end -->

                    <div id="chat" class="chats shadow-box">
                        <div class="chat_other">
                            <h4>全部评论: <i id="chat_count">0</i> 条</h4>
                        </div>
                        <ul id="chat_container" class="its"></ul>
                        <div id="pager" class="text-center"></div>
                        <div class="cbox-wrap">
                            <div class="cbox-title">我有话说: <span id="chat_reply" style="display:none;">@<i
                                    id="chat_to"></i></span>
                            </div>
                            <div class="cbox-post">
                                <div class="cbox-input">
                                    <textarea id="chat_text" rows="3" placeholder="请输入评论内容" onfocus="onChanger()"></textarea>
                                    <input type="hidden" value="0" name="chat_pid" id="chat_pid"/>
                                </div>
                                <div class="cbox-ats clearfix">
                                    <div class="ats-func">
                                        <ul class="func-list">
                                            <li class="list-b">
                                                <a href="javascript:void(0);" class="join" id="c-btn"><i
                                                        class="fa fa-smile-o fa-2"></i></a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="ats-issue">
                                        <button id="btn-chat" class="btn btn-success btn-sm bt">发送</button>
                                    </div>
                                </div>
                            </div>
                            <div class="phiz-box" id="c-phiz" style="display:none">
                                <div class="phiz-list" view="c-phizs"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <script>
                    function onChanger() {
                        $('#btn-chat').removeAttr('disabled');
                    }
                </script>

                <!-- right sidebar-->
                <div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
                    <ul class="list-group about-user">
                        <li class="list-group-item user-card" >
                            <div class="ava">
                                <a href="${base}/ta/${ret.user.id}/1">
                                    <@showAva ret.user.avatar "img-circle"/>
                                </a>
                            </div>
                            <div class="user-info">
                                <div class="nk mb10">${ret.user.nickName}</div>
                                <div class="mb6">
                                    <a class="btn btn-success btn-xs" href="javascript:void(0);" data-id="${ret.user.id}" rel="follow">+ 关注</a>
                                </div>
                            </div>
                        </li>

                        <li class="list-group-item">
                            <div class="user-datas">
                                <ul>
                                    <li><strong>${ret.user.posts}</strong><span>文章</span></li>
                                    <li><strong>${ret.user.fans}</strong><span>粉丝</span></li>
                                    <li><strong>${ret.user.favors}</strong><span>喜欢</span></li>
                                    <li class="noborder"><strong>${ret.user.comments}</strong><span>评论</span></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a class="btn btn-success btn-sm" href="javascript:void(0);" data-user-id="${ret.user.id}" data-id="${ret.id}" rel="favor">喜欢</a>
                            <strong id="favors">${ret.favors}</strong> 喜欢
                        </li>
                    </ul>
                <#include "/default/inc/right.ftl"/>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/default/inc/footer.ftl"/>

<script type="text/plain" id="chat_template">
    <li id="chat{5}">
        <a class="avt fl" target="_blank" href="${base}/ta/{0}/1">
            <img src="{1}">
        </a>
        <div class="chat_body">
            <h5>
                <div class="fl"><a class="chat_name" href="${base}/ta/{0}/1">{2}</a><span>{3}</span></div>
                <div class="fr reply_this"><a href="javascript:void(0);" onclick="goto('{5}', '{2}')">[回复]</a></div>
                <div class="clear"></div>
            </h5>
            <div class="chat_p">
                <div class="chat_pct">{4}</div> {6}
            </div>
        </div>
        <div class="clear"></div>
        <div class="chat_reply"></div>
    </li>
</script>

<script type="text/javascript">
    function goto(pid, user) {
        document.getElementById('chat_text').scrollIntoView();
        $('#chat_text').focus();
        $('#chat_text').val('');
        $('#chat_to').text(user);
        $('#chat_pid').val(pid);

        $('#chat_reply').show();
    }

    var container = $("#chat_container");
    var template = $('#chat_template')[0].text;

    seajs.use('comment', function (comment) {
        comment.init({
            load_url: '${base}/api/free/comment/list.json/'+ ${ret.id} + '/',
            post_url: '${base}/api/free/comment/submit.json',
            toId: '${ret.id}',
            onLoad: function (i, data) {
                var content = ContentRender.wrapItem(data.content);
                var quoto = '';
                if (!(data.childs === null) && data.childs.length > 0) {
                    var pat = data.childs;
                    for(var i = 0; i < pat.length; i++){
                        if(i < 3) {
                            var pcontent = ContentRender.wrapItem(pat[i].content);
                            quoto += '<div class="quote"><a href="${base}/ta/' + pat[i].userId + '/1' + '">' + pat[i].nickName + '</a>： ' + pcontent + '</div>';
                        }else{
                            quoto += '<div class="quote"><a href="#">共' + pat.length + '条回复 ></a></div>';
                            break;
                        }
                    }
                }
                var item = jQuery.format(template,
                        data.userId,
                        data.avatar,
                        data.nickName,
                        data.createTime,
                        content,
                        data.id, quoto);
                return item;
            }
        });
    });

    seajs.use('phiz', function (phiz) {
        $("#c-btn").jphiz({
            base: '/static/assets',
            textId: 'chat_text',
            lnkBoxId: 'c-lnk',
            phizBoxId: 'c-phiz'
        });
    });

</script>
</body>
</html>
