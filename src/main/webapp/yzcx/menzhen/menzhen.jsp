<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <header class="mui-bar mui-bar-nav">
            <a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
            <h1 class="mui-title">全院门诊情况</h1>
        </header>
        <!------------页面内容容器------------>
        <div id="offCanvasContentScroll" class="mui-content  mui-scroll-wrapper">
            <div class="mui-content-padded">
                <!--卡片（）-->
                <div class="mui-card">
                    <div class="mui-card-header">当日门诊分析
                        <span url="webyzcx/menzhen_yue">
                            月门诊
                        </span>
                    </div>
                    <div class="mui-card-content">
                        <!--总数模块区-->
                        <div class="mui-row totalBox">
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorA" style="width: 92%; margin-left: 6%;">
                                    <p>总人数</p>
                                    <font><fmt:formatNumber type="number" value="${obj.putong+obj.jizhen}" pattern="0"
                                                            maxFractionDigits="0"/></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorB">
                                    <p>门诊</p>
                                    <font><fmt:formatNumber type="number" value="${obj.putong}" pattern="0"
                                                            maxFractionDigits="0"/></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorC" style="width: 92%; margin-right: 6%;">
                                    <p>急诊</p>
                                    <font><fmt:formatNumber type="number" value="${obj.jizhen}" pattern="0"
                                                            maxFractionDigits="0"/></font>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!--卡片容器-->
                    <div class="mui-card">
                        <div class="mui-card-header">每小时门诊情况</div>
                        <div id="ri-menzhenLine" class="chart-box" style="width: 100%;height:310px;"></div>
                    </div>
                    <div class="mui-card">
                        <div class="mui-card-header">门诊预约分析<span url="webyzcx/menzhen_yuyue_yue">月预约</span>
                        </div>
                        <div class="mui-card-content">
                            <!--总数模块区-->
                            <div class="totalBox">
                                <span>预约总人数</span>
                                <b><fmt:formatNumber type="number" value="${obj.yuyueshu}" pattern="0"
                                                     maxFractionDigits="0"/></b>
                            </div>
                        </div>
                    </div>
                    <div class="mui-card">
                        <div class="mui-card-header">当日门诊预约排名（前十名）</div>
                        <div id="ri-yuyuebar" class="chart-box" style="width: 100%;height:450px;"></div>
                    </div>
                    <div class="mui-card">
                        <div class="mui-card-header">当日疾病占比排名（前十名）</div>
                        <div id="ri-jibingbar" class="chart-box" style="width: 100%;height:550px;"></div>
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
<script language="javascript" type="text/javascript" src="assets/yzcx/menzhen/menzhen.js"></script>
</body>
</html>