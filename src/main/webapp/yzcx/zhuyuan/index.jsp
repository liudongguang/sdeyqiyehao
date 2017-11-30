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
                <div id="container" style="width: 100%; height:300px;margin: 0 auto;"></div>
            </li>
            <li class="body-section" style="padding-top: 0!important;background-color: #ecf0f1!important;">
                <div class="tit_sty">
                    <span class="tit_sty_span1">当月出院人次同期分析</span>
                </div>
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container1" style="width: 100%; height: 300px;margin: 0 auto"></div>

            </li>
            <li class="footer-section" style="padding-top: 0!important;background-color: #ecf0f1!important;">
                <div class="tit_sty">
                    <span class="tit_sty_span1">住院患者数分析</span>
                </div>
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="container2" style="min-width: 100%; height: 700px;margin: 30px auto;"></div>
            </li>
        </ul>
    </div>
</div>
</div>

<!--<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>-->
<script type="text/javascript" language="javascript" src="assets/yzcx/js/trunk.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/echarts.js"></script>
<script type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script type="text/javascript" language="javascript" src="assets/yzcx/zhuyuan/index.js"></script>
</body>
</html>