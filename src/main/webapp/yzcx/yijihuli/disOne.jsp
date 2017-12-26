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
                       class="center datatable listTable detailTable">
                    <tbody>
                    <tr>
                        <td>住院号码：</td>
                        <td>${param.zyhm}</td>
                    </tr>
                    <tr>
                        <td>患者科室：</td>
                        <td>${param.brks}</td>
                    </tr>
                    <tr>
                        <td>患者病区：</td>
                        <td>${param.brbq}</td>
                    </tr>
                    <tr>
                        <td>病人科室：</td>
                        <td>${param.brks}</td>
                    </tr>
                    <tr>
                        <td>患者姓名：</td>
                        <td>${param.brxm}</td>
                    </tr>
                    <tr>
                        <td>患者性别：</td>
                        <td>${param.brxb}</td>
                    </tr>
                    <tr>
                        <td>开始时间：</td>
                        <td>${param.kssj}</td>
                    </tr>
                    <tr>
                        <td>停止时间：</td>
                        <td>${param.tzsj}</td>
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