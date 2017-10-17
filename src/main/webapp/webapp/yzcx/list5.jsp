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
<!--<script type="text/javascript" src="assets/yzcx/js/jquery-1.11.0.min.js"></script>-->
<script src="assets/yzcx/js/jquery.monthpicker.js"></script>
<script type="text/javascript">
	$(function() {
		$('#monthpicker').monthpicker({
			years : [ 2015, 2014, 2013, 2012, 2011 ],
			topOffset : 6,
			onMonthSelect : function(m, y) {
				console.log('Month: ' + m + ', year: ' + y);
			}
		});
		$('#monthly').monthpicker({
			years : [ 2016, 2015, 2014, 2013, 2012, 2011 ],
			topOffset : 6
		})
	});
</script>
<style type="text/css">
.input {
	width: 63px;
	padding: 4px;
	border: 1px solid #2f4554;
	border-radius: 5px
}
</style>
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
					<div class="tit_sty" style="border: none !important;">
						<!--<span style="font-size: 16px;color: #2f4554">当日门诊分析</span>-->
						<span class="tit_sty_span1" style="float: right">当日出诊分析</span> <img
							style="width: 35px; vertical-align: middle;" src="assets/yzcx/image/data.png"
							alt="" /> <input type="text" class="input" id="monthly"
							placeholder="选择月份">
					</div>
				</li>
				<li class="header-section"
					style="background-color: #ecf0f1 !important;">
					<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container"
						style="width: 100%; height: 350px; margin: 0 auto;"></div> <script
						type="text/javascript">
							$(function() {
								$('#container')
										.highcharts(
												{
													chart : {
														type : 'column'
													},
													title : {
														text : ' '
													},
													//							subtitle: {
													//								text: 'Source: <a href="http://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
													//							},
													xAxis : {
														type : 'category',
														labels : {
															rotation : -45,
															style : {
																fontSize : '13px',
																fontFamily : 'Verdana, sans-serif'
															}
														}
													},
													yAxis : {
														min : 0,
														title : {
															text : '单位：百分比'
														}
													},
													legend : {
														enabled : false
													},
													tooltip : {
														pointFormat : ' <b>{point.y:1f} 人</b>'
													},
													series : [ {
														name : 'Population',
														data : [
																[ '胸外科', 150 ],
																[ '内分泌科', 50 ],
																[ '血液科', 80 ],
																[ '口腔科', 120 ],
																[ '手足外科', 50 ],
																[ '心内科', 180 ],
																[ '神经外科', 90 ],
																[ '消化内科', 100 ] ],
														dataLabels : {
															enabled : true,
															rotation : -90,
															color : '#FFFFFF',
															align : 'right',
															//									format: '{point.y:.1f}', // one decimal
															y : 10, // 10 pixels down from the top
															style : {
																fontSize : '13px',
																fontFamily : 'Verdana, sans-serif'
															}
														}
													} ],
													credits : {
														enabled : false
													},
													exporting : {
														enabled : false
													}
												});
							});
						</script>
				</li>
				<li class="body-section"
					style="padding-top: 0 !important; background-color: white">
					<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id=""
						style="width: 100%; height: 200px; top: 10px !important;">
						<a href="yzcx/list5next.jsp"><div class="tit_sty">
								<span class="tit_sty_span1">查看列表</span> <span
									class="tit_sty_span2"><img
									style="width: 20px; vertical-align: middle;"
									src="assets/yzcx/image/right.png" alt="" /></span>
							</div></a>
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