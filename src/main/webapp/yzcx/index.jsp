<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath }/" />
	<meta charset="utf-8">
	<title>院长查询系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!--引入 mui文件-->
	<link rel="stylesheet" href="assets/yzcx/mui/css/mui.min.css">
	<link rel="stylesheet" href="assets/yzcx/mui/css/iconfont.css">
	<!--引入 自定义文件-->
	<link rel="stylesheet" href="assets/yzcx/css/general.css">
	<link rel="stylesheet" href="assets/yzcx/css/page.css">
</head>

<body>
<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
	<div class="mui-inner-wrap">
		<!--------------侧滑菜单部分-------------->
		<%@ include file="yzcxNav.jsp"%>
		<!------------页面主标题 ------------>
		<header class="mui-bar mui-bar-nav">
			<a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
			<h1 class="mui-title">全院概览</h1>
		</header>
		<!------------页面内容容器------------>
		<div id="offCanvasContentScroll" class="mui-content mui-scroll-wrapper">
			<div class="mui-content-padded">
				<!--卡片（门急诊）-->
				<div class="mui-card">
					<div class="mui-card-header">门诊急诊（今日） <span class="grayText">共426人</span></div>
					<div class="mui-card-content">

						<!--总数模块区-->
						<div class="mui-row totalBox">
							<ul class="mui-col-sm-6 mui-col-xs-6">
								<li class="total-colorA" style="width: 92%; margin-left: 6%;">
									<p>出诊医生总数</p>
									<font>156位</font>
								</li>
							</ul>
							<ul class="mui-col-sm-6 mui-col-xs-6">
								<li class="total-colorB" style="width: 92%; margin-right: 6%;">
									<p>医生平均门急诊量</p>
									<font>500人次</font>
								</li>
							</ul>
							<ul class="mui-col-sm-6 mui-col-xs-6">
								<li class="total-colorC" style="width: 92%; margin-left: 6%;">
									<p>处方数量</p>
									<font>200个</font>
								</li>
							</ul>

							<ul class="mui-col-sm-6 mui-col-xs-6">
								<li class="total-colorD" style="width: 92%; margin-right: 6%;">
									<p>医生平均处方数量</p>
									<font>300个</font>
								</li>
							</ul>
						</div>
						<!--图表容器 门诊急诊-->
						<div id="pie-outPatient" style="width:100%;height:240px; margin-top: 20px;"> </div>
						<div class="mui-row" style="border-top:1px solid #ebebeb">
							<div class="mui-col-sm-12 mui-col-xs-12 bedUse-profile">处方总额：300元</div>
						</div>
						<!--图表容器 处方金额-->
						<div id="bar-recipel" style="width: 100%;height:260px;"> </div>
					</div>
				</div>
				<!--卡片（在院人数）-->
				<div class="mui-card">
					<div class="mui-card-header">在院人数（今日）<span class="grayText">共3845人</span></div>
					<div class="mui-card-content">
						<!--总数模块区-->
						<div class="mui-row totalBox">
							<ul class="mui-col-sm-4 mui-col-xs-4">
								<li class="total-colorA" style="width: 92%; margin-left: 6%;">
									<p>入院</p>
									<font>156066002</font>
								</li>
							</ul>
							<ul class="mui-col-sm-4 mui-col-xs-4">
								<li class="total-colorB">
									<p>出院</p>
									<font>500</font>
								</li>
							</ul>
							<ul class="mui-col-sm-4 mui-col-xs-4">
								<li class="total-colorC" style="width: 92%; margin-right: 6%;">
									<p>病重</p>
									<font>200</font>
								</li>
							</ul>
							<ul class="mui-col-sm-4 mui-col-xs-4">
								<li class="total-colorD" style="width: 92%; margin-left: 6%;">
									<p>转入、转出</p>
									<font>8</font>
								</li>
							</ul>
							<ul class="mui-col-sm-4 mui-col-xs-4">
								<li class="total-colorE">
									<p>死亡</p>
									<font>20</font>
								</li>
							</ul>

						</div>
						<div class="mui-row" style="border-top:1px solid #ebebeb">
							<div class="mui-col-sm-12 mui-col-xs-12 bedUse-profile">床位使用率：103%</div>
						</div>
						<!--图表容器 床位使用-->
						<div id="bar-inHospital" style="width: 100%;height:220px;"> </div>
					</div>
				</div>
				<!--卡片（收入分析）-->
				<div class="mui-card">
					<div class="mui-card-header">收入（昨日）</div>
					<div class="mui-card-content">
						<!--总数模块区-->
						<div class="totalBox">
							<span>昨日总收入</span>
							<b>￥208411.00</b>
						</div>
						<!--图表容器-->
						<div id="pie-income" style="width:100%;height:260px; margin: 20px 0px;"> </div>
					</div>
				</div>

			</div>
		</div>
		<!--侧滑栏出现后，主页面遮罩层-->
		<div class="mui-off-canvas-backdrop"></div>
	</div>
</div>
<!--引入 mui文件-->
<script src="assets/yzcx/mui/js/mui.min.js"></script>
<!--引入 ECharts文件 -->
<script src="assets/yzcx/echarts/echarts.common.min.js"></script>
<script src="assets/yzcx/echarts/walden.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart_pie_outPatient = echarts.init(document.getElementById('pie-outPatient'), 'walden');
    var myChart_bar_recipel = echarts.init(document.getElementById('bar-recipel'), 'walden');
    var myChart_bar_inHospital = echarts.init(document.getElementById('bar-inHospital'), 'walden');
    var myChart_pie_income = echarts.init(document.getElementById('pie-income'), 'walden');
    // 配置项和数据（门诊、急诊）
    var option_pie_outPatient = {

        title: {
            text: ' ',
            subtext: '  单位：元'
        },
        legend: {
            x: 'center',
            y: 'top',
            data: ['门诊', '急诊']

        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series: [{
            name: '全院总收入',
            type: 'pie',
            radius: '70%',
            center: ['50%', '55%'],
            data: [{
                value: 33885,
                name: '门诊'
            },
                {
                    value: 31880,
                    name: '急诊'
                }
            ].sort(function(a, b) {
                return a.value - b.value;
            }),
            roseType: 'radius',

            labelLine: {
                normal: {
                    smooth: 0.1,
                    length: 8,
                    length2: 10
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 40,
                    shadowColor: 'rgba(0, 0, 0, 0.1)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function(idx) {
                return Math.random() * 200;
            }
        }]
    };

    // 配置项和数据（处方）
    var option_bar_recipel = {
        title: {
            text: ' ',
            subtext: '  单位：元',
            x: 'left',
            align: 'left'
        },
        legend: {
            data: ['处方金额']
        },
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}:{c0}M',
            axisPointer: {
                type: 'line'
            }
        },
        grid: {
            left: '5%',
            right: '5%',
            bottom: '13%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 0, //横轴信息全部显示
                rotate: 30 //30度角倾斜显示
            },
            boundaryGap: [0, 0.1]
        },
        yAxis: {
            type: 'category',
            data: ['最大处方金额', '平均处方金额','最小处方金额']
        },
        series: [{
            name: '处方金额',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },

            data: [{
                value: 2850,
                name: '最大处方金额'
            },
                {
                    value: 1000,
                    name: '平均处方金额'
                },
                {
                    value: 100,
                    name: '最小处方金额'
                }],

        }]
    };

    // 配置项和数据（在院人数）
    var option_bar_inHospital = {
        title: {
            text: ' ',
            subtext: '  单位：个',
            x: 'left',
            align: 'left'
        },
        legend: {
            data: ['床位']
        },
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}:{c0}M',
            axisPointer: {
                type: 'line'
            }
        },
        grid: {
            left: '5%',
            right: '5%',
            bottom: '13%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 0, //横轴信息全部显示
                rotate: 30 //30度角倾斜显示
            },
            boundaryGap: [0, 0.1]
        },
        yAxis: {
            type: 'category',
            data: ['开放床位', '使用床位']
        },
        series: [{
            name: '床位',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },

            data: [{
                value: 2850,
                name: '开放床位'
            },
                {
                    value: 1000,
                    name: '使用床位'
                }],

        }]
    };

    // 配置项和数据（收入）
    var option_pie_income = {

        title: {
            text: ' ',
            subtext: '  单位：元'
        },
        legend: {
            x: 'center',
            y: 'top',
            data: ['药品', '医疗', '其他']

        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series: [{
            name: '全院昨日收入',
            type: 'pie',
            radius: '70%',
            center: ['50%', '55%'],
            data: [{
                value: 33500,
                name: '药品'
            },
                {
                    value: 31000,
                    name: '医疗'
                },
                {
                    value: 27400,
                    name: '其他'
                }
            ].sort(function(a, b) {
                return a.value - b.value;
            }),
            roseType: 'radius',

            labelLine: {
                normal: {
                    smooth: 0.1,
                    length: 8,
                    length2: 10
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 40,
                    shadowColor: 'rgba(0, 0, 0, 0.1)'
                }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function(idx) {
                return Math.random() * 200;
            }
        }]
    };

    // 将图表显示出来
    myChart_pie_outPatient.setOption(option_pie_outPatient);
    myChart_bar_recipel.setOption(option_bar_recipel);
    myChart_bar_inHospital.setOption(option_bar_inHospital);
    myChart_pie_income.setOption(option_pie_income);

    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();
</script>
</body>
</html>