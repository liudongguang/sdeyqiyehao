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
</head>
<body>
<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
    <div class="mui-inner-wrap">
<%@ include file="../yzcxNav.jsp" %>
<!------------页面主标题 ------------>
<header class="mui-bar mui-bar-nav">
    <a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
    <h1 class="mui-title">当前一级护理病人</h1>
</header>
<!--搜索-->
<div class="searchBox">
    <div class="mui-input-row mui-search">
        <input type="search" class="mui-input-clear search" placeholder="科室名称或科室首字母" fangbaidu_searurl="hisoffice/searchKS">
        <input type="hidden" value="" id="pageNumID"/>
        <input type="hidden" value="" id="ksNameID"/>
    </div>
</div>
<!--列表-->
<!------------页面内容容器------------>
<div id="offCanvasContentScroll" class="mui-content mui-scroll-wrapper" style="padding-top:0px;margin-top:90px;">
    <!--下拉刷新容器-->
    <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
        <div class="mui-scroll">
            <div class="mui-content-padded">
                <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="center datatable listTable content-box">

                    <tr class="content-box-cell">
                        <td>患者科室</td>
                        <td>患者姓名</td>
                        <td>开始时间</td>
                        <td>停止时间</td>
                    </tr>
                    <tbody id="infoID">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!--侧滑栏出现后，主页面遮罩层-->
<div class="mui-off-canvas-backdrop"></div>
</div>
</div>
<!--搜索弹出匹配结果-->
<div class="search-matchBox" id="disRSDIVID" style="display: none">
    <div class="mui-content-padded" style="margin-top:0px; box-shadow:0px 4px 10px 0px #c3c3c3;">
        <ul class="mui-table-view" id="addULID">
        </ul>
    </div>
</div>

<!--引入 mui文件-->
<script src="assets/yzcx/mui/js/mui.min.js"></script>
<!--引入 自定义文件 -->
<script src="assets/yzcx/js/general.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/yijihuli/dataList.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/baidu_mui.js"></script>
</body>
</html>