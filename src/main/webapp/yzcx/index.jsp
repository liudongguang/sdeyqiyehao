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
		<h1>全院概览</h1>
	</header>

	<nav class="slide">
		<ul>
			<li><a href="webyzcx/index" class="active">全院概览</a></li>
			<li><a href="webyzcx/menzhen">门诊信息</a></li>
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
					<span class="tit_sty_span2"><a href="indexnext.html">查看月门诊量&nbsp;></a></span>
				</div>
				<div class="tit_sty_div_all">
					<div class="tit_sty_div_all_tab1">
						<div class="tit_sty_div_all_tab1_div1">
							<p class="tit_sty_div_all_tab1_div1_p">总人次</p>
							<p><fmt:formatNumber type="number" value="${obj.putong+obj.jizhen}" pattern="0" maxFractionDigits="0"/> </p>
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
				<script type="text/javascript">
                    $(function () {
                        $('#container').highcharts({
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: ' '
                            },
                            xAxis: {
                                categories: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24']
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: '单位：人'
                                }
                            },
                            tooltip: {
                                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}人</b><br/>',
                                shared: true
                            },
                            plotOptions: {
                                column: {
                                    stacking: 'normal'
                                }
                            },
                            series: [{
                                name: '门诊',
                                data: [3, 3, 4, 7, 2, 4, 7, 450, 510, 470, 370, 42, 54, 200, 230, 153, 47, 79, 72, 4, 7, 2, 7, 2]
                            }, {
                                name: '急诊',
                                data: [3, 4, 4, 2, 5, 4, 2, 5, 14, 14, 12, 15, 14, 12, 15, 4, 4, 2, 5, 4, 2, 5, 2, 5]
                            }],
                            credits: {
                                enabled:false
                            },
                            exporting: {
                                enabled:false
                            }
                        });
                    });
				</script>
			</li>
			<li class="body-section" style="padding-top: 0!important;background-color: #ecf0f1!important;margin-bottom: -65px!important;">
				<div class="tit_sty">
					<span class="tit_sty_span1">住院工作量日报</span>
					<a href="indexnext_2f.html"><span class="tit_sty_span2">详情&nbsp;></span></a>
				</div>
				<div class="tit_sty_div_all" style="padding-bottom: 30px!important;">
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
				<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
				<div id="container1" style="min-width: 100%; height: 700px;margin: 30px auto;"></div>
				<script type="text/javascript">
                    $(function () {
                        $('#container1').highcharts({
                            chart: {
                                type: 'bar'
                            },
                            title: {
                                text: ' '
                            },
                            xAxis: {
                                categories: ['产科', '创伤骨科', '儿内科', '耳鼻咽喉头颈外科', '妇科', '感染/肝病科', '肛肠外科', '关节外科','呼吸内科', '急诊科', '脊柱外科', '甲状腺外科', '健康管理科', '介入科', '介入医技', '口腔科','临床医技科室', '麻醉二科', '泌尿外科', '男科', '皮肤科','普外二科', '普外一科', '乳腺外科', '神经内科', '神经外科', '肾移植科', '肾脏内科', '手足外科','消化内科','心血管内科', '心血管内科特检','心血管外科', '胸外科', '血液净化', '眼科', '整形烧伤医技', '肿瘤防治中心', '重症医学科', '周围血管病科'],
                                title: {
                                    text: null
                                }
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: '单位：人',
                                    align: 'high'
                                },
                                labels: {
                                    overflow: 'justify'
                                }
                            },
                            tooltip: {
                                valueSuffix: ' 人	'
                            },
                            plotOptions: {
                                bar: {
                                    dataLabels: {
                                        enabled: true
                                    }
                                }
                            },
                            series: [{
                                name: '入院',
                                data: [27, 4, 3, 4, 10, 1, 1, 2, 1, 1, 8, 5, 2, 1, 1, 1, 1, 5, 3, 1, 1, 3, 5, 6, 1, 1, 3, 1, 3, 3, 9, 15, 12, 3, 3, 3, 1, 3, 1, 1]
                            }],
                            credits: {
                                enabled:false
                            },
                            exporting: {
                                enabled:false
                            }
                        });
                    });

				</script>
			</li>
			<li class="header-section" style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 1px!important;padding-top: 1px!important;">
				<div class="tit_sty" style="border:none!important;">
					<span class="tit_sty_span1"><img style="vertical-align: middle;width: 30px;height: 30px" src="assets/yzcx/image/money.png" alt=""/>11月总收入</span>
					<span class="tit_sty_span2">123800075.00</span>
				</div>
			</li>
			<li class="header-section" style="background-color: #ecf0f1!important;">
				<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
				<div id="container3" style="min-width: 100%; height: 300px;margin: 0 auto"></div>
				<script type="text/javascript">
                    $(function () {
                        $('#container3').highcharts({
                            chart: {
                                type: 'bar'
                            },
                            title: {
                                text: ' '
                            },
                            subtitle: {
                                text: ' '
                            },
                            xAxis: {
                                categories: ['其他', '药品', '医疗'],
                                title: {
                                    text: null
                                }
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: '单位：元',
                                    align: 'high'
                                },
                                labels: {
                                    overflow: 'justify'
                                }
                            },
                            tooltip: {
                                valueSuffix: '元'
                            },
                            plotOptions: {
                                bar: {
                                    dataLabels: {
                                        enabled: true
                                    }
                                }
                            },
                            series: [{
                                name: '收入',
                                data: [207, 500, 635]
                            }],
                            credits: {
                                enabled:false
                            },
                            exporting: {
                                enabled:false
                            }
                        });
                    });
				</script>
			</li>
		</ul>
	</div>

</div>

<!--<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>-->
<script>window.jQuery || document.write('<script src="assets/yzcx/js/jquery-1.11.0.min.js"><\/script>')</script>
<script type="text/javascript" language="javascript" src="assets/yzcx/js/trunk.js"></script>
</body>
</html>