<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" type="text/css" href="assets/yzcx/mui/css/mui.picker.min.css"/>
    <!--引入 自定义文件-->
    <link rel="stylesheet" href="assets/yzcx/css/general.css">
    <!--<script src="js/general.js" type="text/javascript"></script>-->

    <style>

    </style>
</head>

<body>
<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
    <div class="mui-inner-wrap">
        <!--------------侧滑菜单部分-------------->
        <%@ include file="../yzcxNav.jsp" %>
        <!------------页面主标题 ------------>
        <header class="mui-bar mui-bar-nav">
            <a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
            <h1 class="mui-title">全院患者情况</h1>
        </header>
        <!------------页面内容容器------------>
        <div id="offCanvasContentScroll" class="mui-content  mui-scroll-wrapper">
            <div class="mui-content-padded">
                <div class="mui-card">
                    <div class="mui-card-header">患者年龄性别比例图</div>
                    <div id="ri-huanzhe" class="chart-box" style="width: 100%;height:300px;"></div>
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
<script language="javascript" type="text/javascript" src="assets/yzcx/mui/js/mui.picker.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/huanzhe/index.js"></script>
</body>
</html>