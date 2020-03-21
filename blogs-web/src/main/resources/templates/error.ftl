<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>访问出错</title>
    <meta name="keywords" content="${site_keywords}">
    <meta name="description" content="${site_description}">


    <link href="${site_domain}/static/assets/images/logo/logo90.ico" rel="icon" type="image/x-icon" />
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style>
        html { height: 100%; }
        body {
            height: 100%;
            position: relative;
            padding: 0;
            background-color: #f4e5e5;
            /*background-color: #ebe9e9;*/
            /*background-color: #f7e6e6;*/
            font-family: "Open Sans",sans-serif;
            font-size: 12px;
            color: #555;
            margin-left: -20px;
        }
        /*body.texture {*/
            /*background: url("/assets/images/bg.jpg");*/
        /*}*/
        h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
            font-family: 'Open Sans',sans-serif;
            font-weight: 300;
        }
        h2, h1:first-child {
            margin-top: 0;
        }
        .wrapper {
            display: table;
            width: 100%;
            position: absolute;
            height: 100%;
            padding-top: 150px;
            margin-right: 15px;
            margin-left: 15px;
        }

        .page-error {
            margin-top: 50px;
            margin-bottom: 40px;
        }
        .page-error .number {
            color: #fff;
            font-size: 180px;
            font-family: Arial Black;
            text-shadow: 1px 1px 5px rgba(0,0,0,0.6);
            text-shadow: 9px 9px 16px #ffae34;
        }
        .page-error .description {
            color: #fff;
            font-size: 40px;
            text-shadow: 2px 2px 6px #ffae34;
         }
        }
        /*.page-error h3 {*/
            /*color: #fff;*/
            /*text-shadow: 1px 1px 5px rgba(0,0,0,0.6);*/
        /*}*/
        .page-error .the-boot{
            color: #fff;
            font-size: 20px;
            text-shadow: 2px 2px 7px rgba(0,0,0,0.6);
        }
        .copy, .copy a {
            color: #c9d4f6;
            text-shadow: 1px 1px 0 rgba(0,0,0,0.3);
        }
    </style>
</head>
<body class="texture">
<div class="wrapper container">
    <div class="page-error">
        <h1 class="number text-center">${code?default(404)}</h1>
        <h2 class="description text-center">${msg?default("抱歉, 您访问的页面不存在!")}</h2>
        </br>
        <h3 class="text-center the-boot">您可以从这里返回首页 <a href="/index">index</a></h3>
    </div>
    <h5 class="text-center copy">© 2019 - 2020&nbsp;&nbsp; <a href="/index">IT云博客</a></h5>
</div>
</body>
</html>
