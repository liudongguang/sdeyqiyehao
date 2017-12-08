<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" href="assets/bootstrap4.0.0-beta/css/bootstrap.css"/>
</head>
<body style="font-size: 12px">
<div class="card text-left">
    <ul class="list-group list-group-flush">
        <li class="list-group-item">病人姓名：${param.brxm}</li>
        <li class="list-group-item">病人年龄：${param.brnl}岁</li>
        <li class="list-group-item">病人性别：${param.brxb}</li>
        <li class="list-group-item">病人科室：${param.brks}</li>
        <li class="list-group-item">手术时间：${param.ssrq}</li>
        <li class="list-group-item">手术名称：${param.ssmc}</li>
        <li class="list-group-item">手术医生：${param.ssys}</li>

    </ul>
</div>
<script language="javascript" type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/popper.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
</body>
</html>