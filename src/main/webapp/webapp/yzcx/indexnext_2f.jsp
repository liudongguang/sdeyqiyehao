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
			<h1>住院情况一览</h1>
		</header>

		<nav class="slide">
			<ul>
				<li><a href="yzcx/index.jsp" class="active">全院概览</a></li>
				<li><a href="yzcx/list2.jsp">门诊信息</a></li>
				<li><a href="yzcx/list3.jsp">医院总收入</a></li>
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
					style="background-color: white !important; margin-bottom: -43px !important; padding-bottom: 65px !important;">
					<div class="tit_sty_div_all">
						<div class="tit_sty_div_all_tab3">
							<div class="tit_sty_div_all_tab1_div1">
								<p class="tit_sty_div_all_tab1_div1_p">入院</p>
								<p>1000</p>
							</div>
						</div>
						<div class="tit_sty_div_all_tab3">
							<div class="tit_sty_div_all_tab1_div1">
								<p class="tit_sty_div_all_tab1_div1_p">出院</p>
								<p>500</p>
							</div>
						</div>
						<div class="tit_sty_div_all_tab3">
							<div class="tit_sty_div_all_tab1_div1">
								<p class="tit_sty_div_all_tab1_div1_p">空床</p>
								<p>500</p>
							</div>
						</div>
						<div class="tit_sty_div_all_tab3">
							<div class="tit_sty_div_all_tab1_div1">
								<p class="tit_sty_div_all_tab1_div1_p">病危</p>
								<p>500</p>
							</div>
						</div>
						<div class="tit_sty_div_all_tab3">
							<div class="tit_sty_div_all_tab1_div1">
								<p class="tit_sty_div_all_tab1_div1_p">病重</p>
								<p>500</p>
							</div>
						</div>
					</div>
				</li>
				<li class="header-section"
					style="background-color: #ecf0f1 !important;">
					<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container"
						style="width: 100%; height: 300px; margin: 0 auto"></div> <script
						type="text/javascript">
							$(function() {
								$('#container')
										.highcharts(
												{
													chart : {
														plotBackgroundColor : null,
														plotBorderWidth : null,
														plotShadow : false
													},
													title : {
														text : ' '
													},
													subtitle : {
														text : '住院概要'
													},
													tooltip : {
														pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
													},
													plotOptions : {
														pie : {
															allowPointSelect : true,
															cursor : 'pointer',
															dataLabels : {
																enabled : true,
																format : '<b>{point.name}</b>: {point.percentage:.1f} %',
																style : {
																	color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
																			|| 'black'
																}
															}
														}
													},
													series : [ {
														type : 'pie',
														name : 'Browser share',
														data : [ [ '入院', 45 ],
																[ '出院', 26 ],
																[ '空床', 8 ],
																[ '病危', 6 ],
																[ '病重', 10 ] ]
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
						<span class="tit_sty_span1">当月出院人次同期分析</span>
					</div> <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container1"
						style="width: 100%; height: 300px; margin: 0 auto"></div> <script
						type="text/javascript">
							$(function() {
								$('#container1')
										.highcharts(
												{
													chart : {
														type : 'column'
													},
													title : {
														text : ' '
													},
													//							subtitle: {
													//								text: 'Source: WorldClimate.com'
													//							},
													xAxis : {
														categories : [
																'2015年1月',
																'2016年1月' ],
														crosshair : true
													},
													yAxis : {
														min : 0,
														title : {
															text : '单位：人'
														}
													},
													tooltip : {
														headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
														pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
																+ '<td style="padding:0"><b>{point.y:1f} 人</b></td></tr>',
														footerFormat : '</table>',
														shared : true,
														useHTML : true
													},
													plotOptions : {
														column : {
															pointPadding : 0.2,
															borderWidth : 0
														}
													},
													series : [ {
														name : '门诊',
														data : [ 106, 129 ]
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
				<li class="footer-section"
					style="padding-top: 0 !important; background-color: #ecf0f1 !important;">
					<div class="tit_sty">
						<span class="tit_sty_span1">出院患者数分析</span>
					</div> <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container2"
						style="width: 100%; height: 300px; margin: 0 auto"></div> <script
						type="text/javascript">
							$(function() {
								$('#container2').highcharts(
										{
											chart : {
												type : 'bar'
											},
											title : {
												text : ' '
											},
											xAxis : {
												categories : [ '胸外科', '内分泌科',
														'血液科', '口腔科', '手足外科',
														'心内科', '神经外科', '消化内科',
														'呼吸内科' ]
											},
											yAxis : {
												min : 0,
												title : {
													text : '单位：人'
												}
											},
											legend : {
												reversed : true
											},
											plotOptions : {
												series : {
													stacking : 'normal'
												}
											},
											series : [ {
												name : '出院',
												data : [ 5, 3, 4, 7, 2, 3, 4,
														7, 2 ]
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