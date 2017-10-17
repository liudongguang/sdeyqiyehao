﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="assets/yzcx/quanxian/favicon.ico">
<LINK rel="Shortcut Icon" href="assets/yzcx/quanxian/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="assets/yzcx/quanxian/lib/html5.js"></script>
<script type="text/javascript" src="assets/yzcx/quanxian/lib/respond.min.js"></script>
<script type="text/javascript" src="assets/yzcx/quanxian/lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link href="assets/yzcx/quanxian/css/H-ui.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/yzcx/quanxian/css/H-ui.admin.css" rel="stylesheet"
	type="text/css" />
<link href="assets/yzcx/quanxian/skin/default/skin.css" rel="stylesheet"
	type="text/css" />
<link href="assets/yzcx/quanxian/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<link href="assets/yzcx/quanxian/css/style.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>企业号管理平台</title>
<meta name="keywords"
	content="H-ui.admin v2.3,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v2.3，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<header class="Hui-header cl">
		<a class="Hui-logo l" title="H-ui.admin v2.3" href="/">山大二院企业号管理平台</a>
		<a class="Hui-logo-m l" href="/" title="H-ui.admin">H-ui</a>
		<ul class="Hui-userbar">
			<!--<li>超级管理员</li>-->
			<li class="dropDown dropDown_hover"><a href="#"
				class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="login.html">切换账户</a></li>
					<li><a href="login.html">退出</a></li>
				</ul></li>
			<li id="Hui-msg"><a href="#" title="消息"><span
					class="badge badge-danger">1</span><i class="Hui-iconfont"
					style="font-size: 18px">&#xe68a;</i></a></li>
			<li id="Hui-skin" class="dropDown right dropDown_hover"><a
				href="javascript:;" title="换肤"><i class="Hui-iconfont"
					style="font-size: 18px">&#xe62a;</i></a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
					<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
					<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
					<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
					<!--<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>-->
					<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
				</ul></li>
		</ul>
		<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>
	</header>
	<aside class="Hui-aside">
		<input runat="server" id="divScrollValue" type="hidden" value="" />
		<div class="menu_dropdown bk_2">
			<dl id="menu-admin">
				<dt>
					<i class="Hui-iconfont">&#xe62d;</i> <a _href="admin-role.html"
						href="javascript:void(0)"> 角色权限</a>
				</dt>
			</dl>
		</div>
	</aside>
	<div class="dislpayArrow">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面"
						data-href="yzcx/qXUserList">角色权限</span><em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="yzcx/qXUserList"></iframe>
			</div>
		</div>
	</section>
	<script type="text/javascript"
		src="assets/yzcx/quanxian/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="assets/yzcx/quanxian/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="assets/yzcx/quanxian/js/H-ui.js"></script>
	<script type="text/javascript"
		src="assets/yzcx/quanxian/js/H-ui.admin.js"></script>
	<script type="text/javascript">
		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>
	<script type="text/javascript">
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s)
		})();
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
	</script>
</body>
</html>