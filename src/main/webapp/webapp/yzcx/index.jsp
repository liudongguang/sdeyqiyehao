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
<link rel="stylesheet" type="text/css" media="all"
	href="assets/yzcx/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="assets/yzcx/css/default.css">
<link rel="stylesheet" type="text/css" media="all"
	href="assets/yzcx/css/trunk.css" />
<link rel="stylesheet" type="text/css" href="assets/yzcx/css/style.css">
<script type="text/javascript" src="assets/yzcx/js/jquery-1.11.0.min.js"></script>
<script src="assets/yzcx/js/highcharts.js"></script>
<script src="assets/yzcx/js/data.js"></script>
<script src="assets/yzcx/js/exporting.js"></script>
<!-- Additional files for the Highslide popup effect -->
<script src="assets/yzcx/js/highslide-full.min.js"></script>
<script src="assets/yzcx/js/highslide.config.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="assets/yzcx/css/highslide.css" />
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
			<h1>全院概览</h1>
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
					<div class="tit_sty">
						<span class="tit_sty_span1">当日门诊分析</span> <span
							class="tit_sty_span2"><a href="yzcx/indexnext.jsp">查看月门诊量&nbsp;></a></span>
					</div>
					<div class="tit_sty_div_all">
						<div class="tit_sty_div_all_tab1">
							<div class="tit_sty_div_all_tab1_div1">
								<p class="tit_sty_div_all_tab1_div1_p">总人次</p>
								<p>1000</p>
							</div>
						</div>
						<div class="tit_sty_div_all_tab1">
							<div class="tit_sty_div_all_tab1_div1">
								<p class="tit_sty_div_all_tab1_div1_p">门诊</p>
								<p>500</p>
							</div>
						</div>
						<div class="tit_sty_div_all_tab1">
							<div class="tit_sty_div_all_tab1_div1">
								<p class="tit_sty_div_all_tab1_div1_p">急诊</p>
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
								$('#container').highcharts(
										{
											chart : {
												type : 'line'
											},
											title : {
												text : ' '
											},
											//							subtitle: {
											//								text: 'Source: WorldClimate.com'
											//							},
											xAxis : {
												categories : [ '0', '1', '2',
														'3', '4', '5', '6',
														'7', '8', '9', '10',
														'11', '12', '13', '14',
														'15', '16', '17', '18',
														'19', '20', '21', '22',
														'23', '24/h' ]
											},
											yAxis : {
												title : {
													text : '单位：人'
												}
											},
											plotOptions : {
												line : {
													dataLabels : {
														enabled : false
													},
													enableMouseTracking : false
												}
											},
											series : [
													{
														name : '门诊',
														data : [ 110, 101, 150,
																103, 102, 240,
																600, 400, 300,
																200, 109, 200,
																300, 200, 500,
																200, 100, 50,
																50, 20, 30, 20,
																10, 20, 10 ]
													},
													{
														name : '急诊',
														data : [ 80, 90, 120,
																150, 200, 180,
																200, 302, 250,
																240, 300, 300,
																200, 402, 450,
																300, 350, 210,
																200, 150, 200,
																120, 110, 100,
																80 ]
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
						<span class="tit_sty_span1">住院工作量日报</span> <a
							href="yzcx/indexnext_2f.jsp"><span class="tit_sty_span2">详情&nbsp;></span></a>
					</div> <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container1"
						style="width: 100%; height: 350px; margin: 0 auto;"></div> <script
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
															text : '单位：人'
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
				<li class="footer-section"
					style="padding-top: 0 !important; background-color: #ecf0f1 !important;">
					<div class="tit_sty">
						<span class="tit_sty_span1">当日会诊</span> <a
							href="yzcx/indexnext_3f.jsp"><span class="tit_sty_span2">详情&nbsp;></span></a>
					</div> <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
					<div id="container2"
						style="width: 100%; height: 350px; margin: 0 auto; border-radius: 5px !important;"></div>
					<script type="text/javascript">
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
													'心内科', '神经外科', '消化内科' ]
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
											name : '会诊申请',
											data : [ 5, 3, 4, 7, 2, 4, 7, 2 ]
										}, {
											name : '会诊接受',
											data : [ 3, 4, 4, 2, 5, 4, 2, 5 ]
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
	<script type="text/javascript" language="javascript"
		src="assets/yzcx/js/trunk.js"></script>
</body>
</html>