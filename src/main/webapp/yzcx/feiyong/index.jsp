<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <li class="header-section"
                style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 65px!important;">
                <div class="tit_sty">
                    <span class="tit_sty_span1">当日门诊，住院收入占比</span>
                    <!--
                    <span class="tit_sty_span2"><a href="webyzcx/menzhen_yue">查看月门诊量&nbsp;></a></span>
                    -->
                </div>
                <div class="tit_sty_div_all">
                    <div class="tit_sty_div_all_tab1">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">总费用</p>
                            <p id="zongfeiID"></p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab1">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">门诊</p>
                            <p id="menzhenID"></p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab1">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">住院</p>
                            <p id="zhuyuanID"></p>
                        </div>
                    </div>
                </div>
            </li>
            <li class="header-section" style="background-color: #ecf0f1!important;">
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container4" style="width: 100%; height: 300px;margin: 0 auto"></div>
            </li>
            <li class="header-section"
                style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 65px!important;">
                <div class="tit_sty">
                    <span class="tit_sty_span1">当日医疗，药品，其他收入占比</span>
                    <!--
                    <span class="tit_sty_span2"><a href="webyzcx/menzhen_yue">查看月门诊量&nbsp;></a></span>
                    -->
                </div>
                <div class="tit_sty_div_all">
                    <div class="tit_sty_div_all_tab1">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">医疗</p>
                            <p id="yiliaoID"></p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab1">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">药品</p>
                            <p id="yaopinID"></p>
                        </div>
                    </div>
                    <div class="tit_sty_div_all_tab1">
                        <div class="tit_sty_div_all_tab1_div1">
                            <p class="tit_sty_div_all_tab1_div1_p">其他</p>
                            <p id="qitaID"></p>
                        </div>
                    </div>
                </div>
            </li>
            <li class="header-section" style="background-color: #ecf0f1!important;">
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container5" style="width: 100%; height: 300px;margin: 0 auto"></div>
            </li>
            <li class="header-section" style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 1px!important;">
                <div class="tit_sty" style="border:none!important;">
                    <span class="tit_sty_span1"><img style="vertical-align: middle;width: 30px;height: 30px" src="image/money.png" alt=""/>住院总收入</span>
                    <span class="tit_sty_span2">${obj.zhuyuanzong}</span>
                </div>
            </li>
            <li  style="background-color: #ecf0f1!important;">
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container" style="min-width: 100%; height: 180px;margin: 0 auto"></div>
            </li>
            <li class="header-section" style="background-color: white!important;margin-bottom: -43px!important;padding-bottom: 1px!important;">
                <div class="tit_sty" style="border:none!important;">
                    <span class="tit_sty_span1"><img style="vertical-align: middle;width: 30px;height: 30px" src="image/money.png" alt=""/>门诊总收入</span>
                    <span class="tit_sty_span2">${obj.menzhenzong}</span>
                </div>
            </li>
            <li  style="background-color: #ecf0f1!important;">
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container1" style="min-width: 100%; height: 180px;margin: 0 auto"></div>
            </li>
            <li class="header-section" style="padding-top: 0!important;background-color: #ecf0f1!important;">
                <div class="tit_sty">
                    <span class="tit_sty_span1">科室住院费用（前十名）</span>
                    <a href="#"><span class="tit_sty_span2">列表&nbsp;></span></a>
                </div>
                <div style="clear: left;width: 100%;height: 15px"></div>
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container2" style="min-width: 100%; height: 300px;margin: 0 auto;"></div>
            </li>
            <li class="header-section2" style="padding-top: 0!important;background-color: #ecf0f1!important;">
                <div class="tit_sty">
                    <span class="tit_sty_span1">科室门诊费用（前十名）</span>
                    <a href="#"><span class="tit_sty_span2">列表&nbsp;></span></a>
                </div>
                <div style="clear: left;width: 100%;height: 15px"></div>
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container3" style="min-width: 100%; height: 300px;margin: 0 auto;"></div>
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
<script type="text/javascript" language="javascript" src="assets/yzcx/feiyong/index.js"></script>
</body>
</html>