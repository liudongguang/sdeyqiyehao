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
    <link rel="stylesheet" href="assets/minirefresh/minirefresh.css"/>
    <link rel="stylesheet" href="assets/yzcx/shoushu/common.css"/>
    <script language="javascript" type="text/javascript" src="js/jquery3.1.0.min.js"></script>
</head>
<body style="font-size: 12px">
<input type="hidden" value="8" id="navNum"/>
<input type="hidden" value="" id="pageNumID"/>
<div id="minirefresh" class="minirefresh-wrap">
    <div class="minirefresh-scroll">
        <div class="container-fluid">
            <div class="row">
                <table class="table table-hover table-striped text-center">
                    <thead>
                    <tr>
                        <th scope="col" style="width:30%">患者科室</th>
                        <th scope="col" style="width:30%">患者姓名</th>
                        <th scope="col">开始时间</th>
                        <th scope="col">停止时间</th>
                    </tr>
                    </thead>
                    <tbody id="infoID">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript" src="assets/minirefresh/minirefresh.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/popper.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
<script type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script type="text/javascript" language="javascript" src="assets/yzcx/yijihuli/dataList.js"></script>
</body>
</html>