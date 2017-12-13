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
    <link rel="stylesheet" type="text/css" href="assets/yzcx/css/jquery.monthpicker.css">
    <script language="javascript" type="text/javascript" src="js/jquery3.1.0.min.js"></script>
    <style type="text/css">
        .input{width: 78px;padding:4px;border:1px solid #2f4554;border-radius: 5px}
    </style>
</head>
<body style="font-size: 12px">
<img style="width: 35px;vertical-align: middle;" src="assets/yzcx/image/data.png" alt=""/>
<input type="text" class="input" id="monthly" placeholder="选择月份" value="<fmt:formatDate value="${obj.param.start}" pattern="yyyy-MM"></fmt:formatDate>">
<table class="table table-hover table-striped text-center">
    <thead>
    <tr>
        <th colspan="2">总人数：${obj.menzhenRenci+obj.zhuyuanRenci}</th>
    </tr>
    <tr>
        <th scope="col">门诊</th>
        <th scope="col">住院</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${obj.menzhenRenci}</td>
            <td>${obj.zhuyuanRenci}</td>
        </tr>
    </tbody>
</table>
<table class="table table-hover table-striped text-center">
    <thead>
    <tr>
        <th colspan="2">总金额：<fmt:formatNumber type="number" value="${obj.menzhenHeji+obj.zhuyuanHeji}" pattern="0"
                                              maxFractionDigits="2"/></th>
    </tr>
    <tr>
        <th scope="col">门诊</th>
        <th scope="col">住院</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><fmt:formatNumber type="number" value="${obj.menzhenHeji}" pattern="0"
                              maxFractionDigits="2"/></td>
        <td><fmt:formatNumber type="number" value="${obj.zhuyuanHeji}" pattern="0"
                              maxFractionDigits="2"/></td>
    </tr>
    </tbody>
</table>
<table class="table table-hover table-striped text-center">
    <thead>
    <tr>
        <th colspan="2">平均金额：<fmt:formatNumber type="number" value="${obj.zongHejiPingjun}" pattern="0"
                                               maxFractionDigits="2"/></th>
    </tr>
    <tr>
        <th scope="col">门诊</th>
        <th scope="col">住院</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><fmt:formatNumber type="number" value="${obj.menzhenPingjun}" pattern="0"
                              maxFractionDigits="2"/></td>
        <td><fmt:formatNumber type="number" value="${obj.zhuyuanPingjun}" pattern="0"
                              maxFractionDigits="2"/></td>
    </tr>
    </tbody>
</table>
<div id="container" style="width: 100%; height:600px;margin: 0 auto;"></div>

<script src="assets/yzcx/js/jquery.monthpicker.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/popper.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
<script type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/echarts.js"></script>
<script type="text/javascript" language="javascript" src="assets/yzcx/yiji/month.js"></script>
</body>
</html>