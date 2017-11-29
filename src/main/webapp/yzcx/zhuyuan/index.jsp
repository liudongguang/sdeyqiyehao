<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh">
<head>
    <base href="${pageContext.request.contextPath }/"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="HandheldFriendly" content="True">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>院长查询系统</title>
    <link rel="stylesheet" type="text/css" media="all" href="assets/yzcx/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="assets/yzcx/css/default.css">
    <link rel="stylesheet" type="text/css" media="all" href="assets/yzcx/css/trunk.css"/>
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
<input type="hidden" value="3" id="navNum"/>
<div class="container">

    <header class="slide">     <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
        <ul id="navToggle" class="burger slide">    <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
            <li></li><li></li><li></li>
        </ul>
        <h1>全院收入情况表</h1>
    </header>
    <%@ include file="../yzcxNav.jsp"%>
    <div class="content slide">     <!--	Add "slideRight" class to items that move right when viewing Nav Drawer  -->
        <ul class="responsive">
            <li class="header-section" style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 65px!important;">
                <div class="tit_sty_div_all">
                    <div class="tit_sty_div_all_tab3">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">入院</p>
                            <p><c:if test="${obj.ruyuan!=null}">
                                <fmt:formatNumber type="number" value="${obj.ruyuan}" pattern="0"/></c:if>
                                <c:if test="${obj.ruyuan==null}">
                                    0</c:if>
                            </p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab3">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">出院</p>
                            <p><c:if test="${obj.chuyuan!=null}">
                                <fmt:formatNumber type="number" value="${obj.chuyuan}" pattern="0"/></c:if>
                                <c:if test="${obj.chuyuan==null}">
                                    0</c:if></p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab3">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">危重</p>
                            <p><c:if test="${obj.weizhong!=null}">
                                <fmt:formatNumber type="number" value="${obj.weizhong}" pattern="0"/></c:if>
                                <c:if test="${obj.weizhong==null}">
                                    0</c:if></p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab3">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">转入</p>
                            <p><c:if test="${obj.zhuanru!=null}">
                                <fmt:formatNumber type="number" value="${obj.zhuanru}" pattern="0"/></c:if>
                                <c:if test="${obj.zhuanru==null}">
                                    0</c:if></p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab3">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">转出</p>
                            <p><c:if test="${obj.zhuanchu!=null}">
                                <fmt:formatNumber type="number" value="${obj.zhuanchu}" pattern="0"/></c:if>
                                <c:if test="${obj.zhuanchu==null}">
                                    0</c:if></p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab3">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">死亡</p>
                            <p><c:if test="${obj.siwang!=null}">
                                <fmt:formatNumber type="number" value="${obj.siwang}" pattern="0"/></c:if>
                                <c:if test="${obj.siwang==null}">
                                    0</c:if></p>
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
                                plotBackgroundColor: null,
                                plotBorderWidth: null,
                                plotShadow: false
                            },
                            title: {
                                text: ' '
                            },
                            subtitle: {
                                text: '住院概要'
                            },
                            tooltip: {
                                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    size:100,
                                    innerSize:'10',
                                    dataLabels: {
//										width: '30px', // 重点在此
                                        enabled: true,
//										useHTML: true,
                                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                        style: {
                                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                        }
                                    }
                                }
                            },
                            series: [{
                                type: 'pie',
                                name: 'Browser share',
                                data: [
                                    ['入院', 45],
                                    ['出院', 26],
                                    ['空床',  8],
                                    ['病危',  6],
                                    ['病重', 10]
                                ]
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
            <li class="body-section" style="padding-top: 0!important;background-color: #ecf0f1!important;">
                <div class="tit_sty">
                    <span class="tit_sty_span1">当月出院人次同期分析</span>
                </div>
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container1" style="width: 100%; height: 300px;margin: 0 auto"></div>
                <script type="text/javascript">
                    $(function () {
                        $('#container1').highcharts({
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: ' '
                            },
//							subtitle: {
//								text: 'Source: WorldClimate.com'
//							},
                            xAxis: {
                                categories: [
                                    '2015年1月',
                                    '2016年1月'
                                ],
                                crosshair: true
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: '单位：人'
                                }
                            },
                            tooltip: {
                                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:1f} 人</b></td></tr>',
                                footerFormat: '</table>',
                                shared: true,
                                useHTML: true
                            },
                            plotOptions: {
                                column: {
                                    pointPadding: 0.2,
                                    borderWidth: 0
                                }
                            },
                            series: [{
                                name: '门诊',
                                data: [ 106, 129]
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
            <li class="footer-section" style="padding-top: 0!important;background-color: #ecf0f1!important;">
                <div class="tit_sty">
                    <span class="tit_sty_span1">住院患者数分析</span>
                </div>
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container2" style="min-width: 100%; height: 700px;margin: 30px auto;"></div>
                <script type="text/javascript">
                    $(function () {
                        $('#container2').highcharts({
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
                                data: [814, 480, 370, 311, 265, 206, 157, 156, 146, 140, 135, 132, 120, 111, 110, 105, 100, 95, 93, 91, 89, 83, 85, 76, 71, 65, 63, 61, 53, 51, 49, 46, 42, 38, 35, 30, 28, 23, 21, 11]
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

</div>

<!--<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>-->
<script type="text/javascript" language="javascript" src="assets/yzcx/js/trunk.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>

</body>
</html>