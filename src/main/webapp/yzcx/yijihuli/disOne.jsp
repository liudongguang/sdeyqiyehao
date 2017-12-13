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
        <li class="list-group-item">住院号码：${param.zyhm}</li>
        <li class="list-group-item">患者科室：${param.brks}</li>
        <li class="list-group-item">患者病区：${param.brbq}</li>
        <li class="list-group-item">患者姓名：${param.brxm}</li>
        <li class="list-group-item">患者性别：${param.brxb}</li>
        <li class="list-group-item">开始时间：${param.kssj}</li>
        <li class="list-group-item">停止时间：${param.tzsj}</li>
    </ul>
</div>
<script language="javascript" type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/popper.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
</body>
</html>