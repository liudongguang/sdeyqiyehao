<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh">
<head>
	<base href="${pageContext.request.contextPath }/" />
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="HandheldFriendly" content="True">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<title>院长查询系统</title>
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
	<link rel="stylesheet" type="text/css" href="assets/yzcx/css/highslide.css"/>
	<link rel="stylesheet" type="text/css" href="assets/yzcx/css/jquery.monthpicker.css">
	<!--<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>-->
	<script src="assets/yzcx/js/jquery.monthpicker.js"></script>
	<style type="text/css">
		.input{width: 78px;padding:4px;border:1px solid #2f4554;border-radius: 5px}
	</style>
</head>
<body>
<input type="hidden" value="1" id="navNum"/>
<div class="container">

	<header class="slide">     <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
		<ul id="navToggle" class="burger slide">    <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
			<li></li><li></li><li></li>
		</ul>
		<h1>门诊信息</h1>
	</header>

	<%@ include file="yzcxNav.jsp"%>

	<div class="content slide">     <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
		<ul class="responsive">
			<li class="header-section" style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 1px!important;">
				<div class="tit_sty" style="border:none!important;">
					<!--<span style="font-size: 16px;color: #2f4554">当日门诊分析</span>-->
					<span class="tit_sty_span2"><a href="webyzcx/menzhen">&lt;查看日预约量&nbsp;</a></span>
					<img style="width: 35px;vertical-align: middle;" src="assets/yzcx/image/data.png" alt=""/>
					<input type="text" class="input" id="monthly" placeholder="选择月份" value="<fmt:formatDate value="${obj.param.start}" pattern="yyyy-MM"></fmt:formatDate>">
				</div>
			</li>
			<li class="header-section" style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 65px!important;">
				<div class="tit_sty_div_all">
					<div class="tit_sty_div_all_tab1">
						<div class="tit_sty_div_all_tab1_div1">
							<p class="tit_sty_div_all_tab1_div1_p">总门诊</p>
							<p id="zongmenzhenID"><c:if test="${obj.zongmenzhen!=null}">
								<fmt:formatNumber type="number" value="${obj.zongmenzhen}" pattern="0" maxFractionDigits="0"/></c:if></p>
						</div>
					</div>
					<div class="tit_sty_div_all_tab1">
						<div class="tit_sty_div_all_tab1_div1">
							<p class="tit_sty_div_all_tab1_div1_p">门诊预约</p>
							<p id="yuyueID"><c:if test="${obj.yuyue!=null}">
								<fmt:formatNumber type="number" value="${obj.yuyue}" pattern="0" maxFractionDigits="0"/></c:if></p>
						</div>
					</div>
					<div class="tit_sty_div_all_tab1">
						<div class="tit_sty_div_all_tab1_div1">
							<p class="tit_sty_div_all_tab1_div1_p">门诊挂号</p>
							<p id="menzhenGuaHaoID"><c:if test="${obj.menzhenGuaHao!=null}">
								<fmt:formatNumber type="number" value="${obj.menzhenGuaHao}" pattern="0" maxFractionDigits="0"/></c:if></p>
						</div>
					</div>
				</div>
			</li>
			<li class="header-section" style="background-color: #ecf0f1!important;">
				<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
				<div id="container" style="min-width: 100%; height: 300px;margin: 0 auto"></div>
			</li>
			<li class="body-section" style="padding-top: 0!important;background-color: #ecf0f1!important;">
				<div class="tit_sty">
					<span class="tit_sty_span1">当月门诊预约排名（前十名）</span>
					<!--<span style="font-size: 12px;float: right">详情&nbsp;></span>-->
				</div>
				<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
				<div id="container2" style="width: 100%; height: 400px;margin: 0 auto"></div>
			</li>
			<li class="body-section" style="padding-top: 0!important;background-color: #ecf0f1!important;">
				<div class="tit_sty">
					<span class="tit_sty_span1">同期分析</span>
					<!--<span style="font-size: 12px;float: right">详情&nbsp;></span>-->
				</div>
				<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
				<div id="container3" style="width: 100%; height: 300px;margin: 0 auto"></div>
			</li>

		</ul>
	</div>
</div>
<!--<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>-->
<script type="text/javascript" language="javascript" src="assets/yzcx/js/trunk.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script type="text/javascript" language="javascript" src="assets/yzcx/menzhen_yuyue_yue.js"></script>
</body>
</html>