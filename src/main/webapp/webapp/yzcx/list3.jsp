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
			<h1>全院收入情况表</h1>
		</header>
		<nav class="slide">
			<ul>
				<li><a href="yzcx/index.jsp">全院概览</a></li>
				<li><a href="yzcx/list2.jsp">门诊信息</a></li>
				<li><a href="yzcx/list3.jsp" class="active">医院总收入</a></li>
				<li><a href="yzcx/list4.jsp">医技信息</a></li>
				<li><a href="yzcx/list5.jsp">医生出诊</a></li>
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
						<span class="tit_sty_span1"><img
							style="vertical-align: middle; width: 30px; height: 30px"
							src="assets/yzcx/image/money.png" alt="" />总收入</span> <span class="tit_sty_span2">123800075.00</span>
					</div>
				</li>
				<li class="header-section"
					style="background-color: #ecf0f1 !important;">
					<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container"
						style="min-width: 100%; height: 300px; margin: 0 auto"></div> <script
						type="text/javascript">
							$(function() {
								$('#container').highcharts({
									chart : {
										type : 'bar'
									},
									title : {
										text : ' '
									},
									subtitle : {
										text : ' '
									},
									xAxis : {
										categories : [ '其他', '药品', '医疗' ],
										title : {
											text : null
										}
									},
									yAxis : {
										min : 0,
										title : {
											text : '单位：元',
											align : 'high'
										},
										labels : {
											overflow : 'justify'
										}
									},
									tooltip : {
										valueSuffix : '元'
									},
									plotOptions : {
										bar : {
											dataLabels : {
												enabled : true
											}
										}
									},
									series : [ {
										name : '收入',
										data : [ 207, 500, 635 ]
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
					style="padding-top: 0 !important; background-color: #ecf0f1 !important;">
					<div class="tit_sty">
						<span class="tit_sty_span1">按科室查看</span> <a href="#"><span
							class="tit_sty_span2">列表&nbsp;></span></a>
					</div>
					<div style="clear: left; width: 100%; height: 15px"></div> <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container1"
						style="min-width: 100%; height: 300px; margin: 0 auto;"></div> <script
						type="text/javascript">
							$(function() {
								$('#container1').highcharts(
										{
											chart : {
												type : 'bar'
											},
											title : {
												text : ' '
											},
											subtitle : {
												text : ' '
											},
											xAxis : {
												categories : [ '骨外科', '神经外科',
														'心内科', '消化内科' ],
												title : {
													text : null
												}
											},
											yAxis : {
												min : 0,
												title : {
													text : '单位：元',
													align : 'high'
												},
												labels : {
													overflow : 'justify'
												}
											},
											tooltip : {
												valueSuffix : ' millions'
											},
											plotOptions : {
												bar : {
													dataLabels : {
														enabled : true
													}
												}
											},
											series : [ {
												name : '其他收入',
												data : [ 107, 31, 635, 203 ]
											}, {
												name : '药品收入',
												data : [ 133, 156, 947, 408 ]
											}, {
												name : '医疗收入',
												data : [ 973, 914, 800, 732 ]
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