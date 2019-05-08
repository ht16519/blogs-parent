<#-- layout -->
<#macro layout>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${site_name} - 后台管理</title>

    <!-- Bootstrap -->
    <link href="/static/assets/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/static/assets/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/assets/vendors/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="/static/assets/admin/css/custom.min.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="/static/assets/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="/static/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src='/static/assets/vendors/validate/jquery-validate.js'></script>
    <!-- FastClick -->
    <script src="/static/assets/vendors/fastclick/lib/fastclick.js"></script>

    <script src="/static/assets/vendors/layer/layer.js"></script>
    <script src="/static/assets/vendors/treeTable/jquery.treeTable.min.js"></script>

    <script type="text/javascript">
        window.UEDITOR_HOME_URL = '/static/assets/vendors/ueditor/';
    </script>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="${base}/index" class="site_title"><span>Mtons</span></a>
                </div>

                <div class="clearfix"></div>

                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <h3>系统菜单</h3>
                        <ul class="nav side-menu">
                            <li><a href="${base}/admin"><i class="fa fa-home"></i> Home</a></li>
                            <#list userMenus as menu>
                                <li><a href="${base}/${menu.url}" nav="${menu.seq}"><i class="${menu.icon}"></i>${menu.name}</a></li>
                            </#list>
                        </ul>
                    </div>
                </div>
                <!-- /sidebar menu -->
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown"
                               aria-expanded="false">
                                <img src="${profile.avatar}" alt="">${profile.userName}
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><a href="${base}/home/feeds/1">Profile</a></li>
                                <li><a href="${base}/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div>
                <#nested/>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">
                Mblog - Powered By <a href="http://mtons.com/?copyright" target="_blank">Mtons</a>
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
    <!-- Custom Theme Scripts -->
    <script src="/static/assets/admin/js/custom.min.js"></script>
    <script src="/static/assets/admin/js/app.data.js"></script>
</body>
</html>
</#macro>

<#macro pager url p spans>
<#if (p.total > 0)>
    <#local pageNum = p.number/>
    <#local pageSize = p.pages/>
    <#if (url?index_of("?") != -1)>
        <#local cURL = (url + "&pn=") />
    <#else>
        <#local cURL = (url + "?pn=") />
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