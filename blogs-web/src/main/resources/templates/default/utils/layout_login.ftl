<#macro layout title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${title}</title>
    <meta name="keywords" content="${site_keywords}">
    <meta name="description" content="${site_description}">
    <!-- v3 -->
    <link rel="stylesheet" href="${site_domain}/static/assets/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${site_domain}/static/assets/css/login.css">
    <link rel="stylesheet" href="${site_domain}/static/assets/vendors/animate/animate.min.css">

    <!-- JavaScript -->
    <script type="text/javascript" src="${site_domain}/static/assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="${site_domain}/static/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${site_domain}/static/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${site_domain}/static/assets/vendors/validate/jquery-validate.js"></script>

    <!-- Favicons -->
    <link rel="apple-touch-icon-precomposed" href="${site_domain}/static/assets/images/logo/m99.png"/>
    <link href="${site_domain}/static/assets/images/logo/logo90.ico" rel="icon" type="image/x-icon" />

</head>
<body class="sign">
    <#nested>

    <#--<p class="small">-->
    <#--${site_copyright} <a href="http://www.miitbeian.gov.cn/" target="_blank">${site_icp}-->
    <#--</p>-->

    <script type="text/javascript">
        //<!CDATA[
        var bodyBgs = [];
        bodyBgs[0] = "${site_domain}/static/assets/images/first/bg-1.jpg";
        bodyBgs[1] = "${site_domain}/static/assets/images/first/bg-2.jpg";
        bodyBgs[2] = "${site_domain}/static/assets/images/first/bg-3.jpg";
        bodyBgs[3] = "${site_domain}/static/assets/images/first/bg-4.jpg";
        bodyBgs[4] = "${site_domain}/static/assets/images/first/bg-5.jpg";
        bodyBgs[5] = "${site_domain}/static/assets/images/first/bg-9.jpg";
        var randomBgIndex = Math.round( Math.random() * 5 );
        //输出随机的背景图
        document.write('<style>body{background:url(' + bodyBgs[randomBgIndex] + ') no-repeat 100% 0}</style>');
        //]]>
    </script>
</body>
</html>

</#macro>
