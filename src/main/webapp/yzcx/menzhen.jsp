<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="zh">
<head>
	<base href="${pageContext.request.contextPath }/" />
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="HandheldFriendly" content="True">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<title>查询系统</title>
	<link rel="stylesheet" type="text/css" media="all" href="assets/yzcx/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="assets/yzcx/css/default.css">
	<link rel="stylesheet" type="text/css" media="all" href="assets/yzcx/css/trunk.css" />
	<link rel="stylesheet" type="text/css" href="assets/yzcx/css/style.css">
	<script type="text/javascript" src="assets/yzcx/js/jquery-1.11.0.min.js"></script>
	<script src="assets/yzcx/js/highcharts.js"></script>
	<script src="assets/yzcx/js/data.js"></script>
	<script src="assets/yzcx/js/exporting.js"></script>
	<!-- Additional files for the Highslide popup effect -->
	<script src="assets/yzcx/js/highslide-full.min.js"></script>
	<script src="assets/yzcx/js/highslide.config.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="assets/yzcx/css/highcharts.css"/>
</head>
<body>
<div class="container">

	<header class="slide">     <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
		<ul id="navToggle" class="burger slide">    <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
			<li></li><li></li><li></li>
		</ul>
		<h1>门诊信息</h1>
	</header>

	<nav class="slide">
		<ul>
			<li><a href="webyzcx/index">全院概览</a></li>
			<li><a href="webyzcx/menzhen" class="active">门诊信息</a></li>
			<li><a href="list3.html">医院总收入</a></li>
			<li><a href="list4.html">医技信息</a></li>
			<li><a href="list5.html">医生出诊</a></li>
			<li><a href="list6.html">手术安排</a></li>
			<li><a href="indexnext_2f.html">住院信息</a></li>
			<li><a href="list8.html">护理单元</a></li>
			<li><a href="list9.html">会诊信息</a></li>
		</ul>
	</nav>

	<div class="content slide">     <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
		<ul class="responsive">
			<li class="header-section" style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 65px!important;">
				<div class="tit_sty">
					<span class="tit_sty_span1">当日门诊分析</span>
					<span class="tit_sty_span2"><a href="list2next.html">查看月门诊量&nbsp;></a></span>
				</div>
				<div class="tit_sty_div_all">
					<div class="tit_sty_div_all_tab1">
						<div class="tit_sty_div_all_tab1_div1">
							<p class="tit_sty_div_all_tab1_div1_p">总人次</p>
							<p><fmt:formatNumber type="number" value="${obj.putong+obj.jizhen}" pattern="0" maxFractionDigits="0"/></p>
						</div>
					</div>
					<div class="tit_sty_div_all_tab1">
						<div class="tit_sty_div_all_tab1_div1">
							<p class="tit_sty_div_all_tab1_div1_p">门诊</p>
							<p><fmt:formatNumber type="number" value="${obj.putong}" pattern="0" maxFractionDigits="0"/></p>
						</div>
					</div>
					<div class="tit_sty_div_all_tab1">
						<div class="tit_sty_div_all_tab1_div1">
							<p class="tit_sty_div_all_tab1_div1_p">急诊</p>
							<p><fmt:formatNumber type="number" value="${obj.jizhen}" pattern="0" maxFractionDigits="0"/></p>
						</div>
					</div>
				</div>
			</li>
			<li class="header-section" style="background-color: #ecf0f1!important;">
				<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
				<div id="container" style="width: 100%; height: 300px;margin: 0 auto"></div>

			</li>
			<li class="body-section" style="padding-top: 0!important;background-color: #ecf0f1!important;">
				<div class="tit_sty">
					<span class="tit_sty_span1">门诊预约分析</span>
					<a href="list2next.html"><span class="tit_sty_span2">查看月门诊量&nbsp;></span></a>
				</div>
				<p style="display: inline-block;padding-top: 5px;color: #000">统计今天前十名科室的预约情况：</p>
				<div class="tit_sty_div_all">
					<div class="tit_sty_div_all_tab4">
						<div class="tit_sty_div_all_tab1_div1">
							<p class="tit_sty_div_all_tab1_div1_p">预约总人数</p>
							<p><fmt:formatNumber type="number" value="${obj.yuyueshu}" pattern="0" maxFractionDigits="0"/></p>
						</div>
					</div>
					<div style="width: 100%;height: 10px"></div>
				</div>
				<div style="clear: left;width: 100%;height: 15px"></div>
				<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
				<div id="container1" style="min-width: 100%; height: 700px;"></div>

			</li>
		</ul>
	</div>

</div>

<!--<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>-->
<script>window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')</script>
<script type="text/javascript" language="javascript" src="js/trunk.js"></script>
<script type="text/javascript" language="javascript" src="assets/yzcx/js/trunk.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script type="text/javascript" language="javascript" src="assets/yzcx/menzhen.js"></script>
</body>
</html>