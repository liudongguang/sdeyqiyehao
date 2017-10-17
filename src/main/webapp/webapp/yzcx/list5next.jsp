<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
<head>
<base href="${pageContext.request.contextPath }/" />
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1.0,user-scalable=no,maximum-scale=1"
	media="(device-height: 568px)">
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
<link rel="stylesheet" type="text/css" href="assets/yzcx/css/highslide.css" />
<link rel="stylesheet" type="text/css" href="assets/yzcx/css/jquery.monthpicker.css">
</head>
<body>
	<div class="container">

		<header class="slide">
			<!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
			<ul id="navToggle" class="burger slide">
				<!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
				<li></li>
				<li></li>
				<li></li>
			</ul>
			<h1>医生出诊</h1>
		</header>

		<nav class="slide">
			<ul>
				<li><a href="yzcx/index.jsp">全院概览</a></li>
				<li><a href="yzcx/list2.jsp">门诊信息</a></li>
				<li><a href="yzcx/list3.jsp">医院总收入</a></li>
				<li><a href="yzcx/list4.jsp">医技信息</a></li>
				<li><a href="yzcx/list5.jsp" class="active">医生出诊</a></li>
				<li><a href="yzcx/list6.jsp">手术安排</a></li>
				<li><a href="yzcx/indexnext_2f.jsp">住院信息</a></li>
				<li><a href="#">护理单元</a></li>
				<li><a href="yzcx/list9.jsp">会诊信息</a></li>
			</ul>
		</nav>

		<div class="content slide">
			<!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
			<ul class="responsive">
				<li class="header-section"
					style="background-color: white !important; margin-bottom: -43px !important; padding-bottom: 1px !important;">
					<div class="tit_sty">
						<!--<span style="font-size: 16px;color: #2f4554">当日门诊分析</span>-->
						<span class="tit_sty_span1">出诊医生总人数：</span>
					</div>
				</li>
				<li class="header-section" style="padding-top: 1px !important;">
					<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container"
						style="width: 100%; height: 500px; margin: 0 auto; overflow-y: scroll">
						<div class="tit_sty_div_all">
							<div class="tit_sty_div_all_tab1">
								<div class="tit_sty_div_all_tab1_div2">
									<p class="tit_sty_div_all_tab1_div1_p">日期</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
									<p>2016/12/02</p>
								</div>
							</div>
							<div class="tit_sty_div_all_tab1">
								<div class="tit_sty_div_all_tab1_div2">
									<p class="tit_sty_div_all_tab1_div1_p">科室名称</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
									<p>呼吸内科</p>
								</div>
							</div>
							<div class="tit_sty_div_all_tab1">
								<div class="tit_sty_div_all_tab1_div2">
									<p class="tit_sty_div_all_tab1_div1_p">人数</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
									<p>500</p>
								</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<!--<script src="http://libs.useso.com/assets/yzcx/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>-->
	<script>
		window.jQuery
				|| document
						.write('<script src="assets/yzcx/js/jquery-1.11.0.min.js"><\/script>')
	</script>
	<script type="text/javascript" language="javascript" src="assets/yzcx/js/trunk.js"></script>
</body>
</html>