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
<!------------页面主标题 ------------>
<header class="mui-bar mui-bar-nav">
    <a class="mui-icon  mui-pull-left"></a>
    <h1 class="mui-title">详情信息</h1>
</header>
<!------------页面内容容器------------>
<div class="mui-content ">
    <div class="mui-content-padded">
        <!--详情列表-->
        <div class="mui-card table-box" style="margin: 0px 0px 10px 0px;">
            <div class="mui-card-content">
                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                       class="center datatable  detailTable">
                    <tbody>
                    <tr>
                        <td>病人姓名：</td>
                        <td>${param.brxm}</td>
                    </tr>
                    <tr>
                        <td>病人年龄：</td>
                        <td>${param.brnl}岁</td>
                    </tr>
                    <tr>
                        <td>病人性别：</td>
                        <td>${param.brxb}</td>
                    </tr>
                    <tr>
                        <td>病人科室：</td>
                        <td>${param.brks}</td>
                    </tr>
                    <tr>
                        <td>手术时间：</td>
                        <td>${param.ssrq}</td>
                    </tr>
                    <tr>
                        <td>手术名称：</td>
                        <td>${param.ssmc}</td>
                    </tr>
                    <tr>
                        <td>手术医生：</td>
                        <td>${param.ssys}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!--引入 mui文件-->
<script src="assets/yzcx/mui/js/mui.min.js"></script>
</body>
</html>