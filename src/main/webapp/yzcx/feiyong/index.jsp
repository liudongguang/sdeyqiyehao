<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <base href="${pageContext.request.contextPath }/"/>
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
        <%@ include file="../yzcxNav.jsp" %>
        <!------------页面主标题 ------------>
        <!------------页面主标题 ------------>
        <header class="mui-bar mui-bar-nav">
            <a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
            <h1 class="mui-title">全院收入情况表</h1>
        </header>

        <!------------页面内容容器------------>
        <div id="offCanvasContentScroll" class="mui-content mui-scroll-wrapper">
            <div class="mui-content-padded">
                <!--卡片（概述）-->
                <div class="mui-card">
                    <div class="mui-card-content">
                        <!--图表容器-->
                        <table width="100%" cellspacing="0" cellpadding="0" class="totalIcome-profile-box">
                            <tr>
                                <td colspan="2" class="extrude">&nbsp;总收入</td>
                            </tr>
                            <tr>
                                <td colspan="2" class="extrude money">￥<fmt:formatNumber type="number" value="${obj.zhuyuanzong+obj.menzhenzong}" pattern="0"
                                                                                        maxFractionDigits="2"/></td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="hidden" value="${obj.qianribaifenbi}" id="qianribfbID"/>
                                    &nbsp;<span id="dyhlvID"></span>%<span id="dyhlvimgID" class="mui-icon mui-icon-arrowthinup"></span></td>
                                <td align="right"><span url="webyzcxFeiYong/feiyong_yue_page">查看月费用</span><span class="mui-icon mui-icon-arrowright"
                                                             style="font-size:15px;"></span></td>
                            </tr>
                            <!--下箭头
                            <tr>
                                <td colspan="2">&nbsp;10.5%<span class="mui-icon mui-icon-arrowthindown" style="color: #ee2a2a;"></span></td>
                            </tr>
                            -->
                        </table>
                    </div>
                </div>

                <!--卡片（住院总收入）-->
                <div class="mui-card">
                    <div class="mui-card-header">住院总收入<span class="grayText" >￥<span id="zhuyuanID"></span></span></div>
                    <div class="mui-card-content">
                        <!--图表容器-->
                        <div id="bar-inHospiTI" class="chart-box" style="width:100%;height:260px;"></div>
                    </div>
                </div>

                <!--卡片（门诊总收入）-->
                <div class="mui-card">
                    <div class="mui-card-header">门诊总收入<span class="grayText">￥<span id="menzhenID"></span></span></div>
                    <div class="mui-card-content">
                        <!--图表容器-->
                        <div id="bar-outPatientTI" class="chart-box" style="width:100%;height:260px;"></div>
                    </div>
                </div>

                <!--卡片（全院总收入）-->
                <div class="mui-card">
                    <div class="mui-card-header">全院总收入</div>
                    <div class="mui-card-content">
                        <!--总数模块区-->
                        <div class="mui-row totalBox">
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorA" style="width: 92%; margin-left: 6%;">
                                    <p>总费用</p>
                                    <font id="zongfeiID"></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorB">
                                    <p>门诊</p>
                                    <font id="menzhenID2"></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorC" style="width: 92%; margin-right: 6%;">
                                    <p>住院</p>
                                    <font id="zhuyuanID2"></font>
                                </li>
                            </ul>
                        </div>
                        <!--图表容器-->
                        <div id="pie-hospiTI" class="chart-box" style="width:100%;height:250px;"></div>
                    </div>
                </div>

                <!--卡片（全院收入类别占比）-->
                <div class="mui-card">
                    <div class="mui-card-header">全院收入类别占比</div>
                    <div class="mui-card-content">
                        <!--总数模块区-->
                        <div class="mui-row totalBox">
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorA" style="width: 92%; margin-left: 6%;">
                                    <p>医疗</p>
                                    <font id="yiliaoID"></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorB">
                                    <p>药品</p>
                                    <font id="yaopinID"></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorC" style="width: 92%; margin-right: 6%;">
                                    <p>其他</p>
                                    <font id="qitaID"></font>
                                </li>
                            </ul>
                        </div>
                        <!--图表容器-->
                        <div id="pie-incomeAssort" class="chart-box" style="width:100%;height:290px;"></div>
                    </div>
                </div>

                <!--卡片（住院科室收入排名 前十名）-->
                <div class="mui-card">
                    <div class="mui-card-header">住院科室收入排名（前十名）<span class="grayText toList">列表 <span
                            class="mui-icon mui-icon-arrowright" style="font-size: 15px;"></span></span>
                    </div>
                    <div class="mui-card-content">
                        <!--图表容器-->
                        <div id="bar-departIncomeRank" class="chart-box" style="width:100%;height:550px;"></div>
                    </div>
                </div>

                <!--卡片（门诊科室收入排名 前十名）-->
                <div class="mui-card">
                    <div class="mui-card-header">门诊科室收入排名（前十名）<span class="grayText toList">列表 <span
                            class="mui-icon mui-icon-arrowright" style="font-size: 15px;"></span></span>
                    </div>
                    <div class="mui-card-content">
                        <!--图表容器-->
                        <div id="bar-outPatientIncomeRank" class="chart-box" style="width:100%;height:550px;"></div>
                    </div>
                </div>
            </div>
        </div>
        <!--侧滑栏出现后，主页面遮罩层-->
        <div class="mui-off-canvas-backdrop"></div>
    </div>
</div>
<script language="javascript" type="text/javascript" src="assets/yzcx/mui/js/mui.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/echarts.common.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/wonderland.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/walden.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/feiyong/index.js"></script>
</body>
</html>