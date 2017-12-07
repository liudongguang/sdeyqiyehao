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

</head>
<body>
<   <nav class="navbar-header">
    <a class="nav-btn-left" href="../index.html">dashboard</a>
    最基础的新闻列表
</nav>
<div class="content">
    <div id="minirefresh" class="minirefresh-wrap">
        <div class="minirefresh-scroll">

            <ul class="data-list" id="listdata">

            </ul>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/shoushu/common.js"></script>
<script language="javascript" type="text/javascript" src="assets/minirefresh/minirefresh.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/popper.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
<script>
    var appendTestData = Common.appendTestData,
        // 记录一个最新
        maxDataSize = 30,
        listDom = document.querySelector('#listdata'),
        requestDelayTime = 600;

    var miniRefresh = new MiniRefresh({
        container: '#minirefresh',
        down: {
            callback: function() {
                setTimeout(function() {
                    // 每次下拉刷新后，上拉的状态会被自动重置
                    appendTestData(listDom, 10, true);
                    miniRefresh.endDownLoading(true);
                }, requestDelayTime);
            }
        },
        up: {
            isAuto: true,
            callback: function() {
                setTimeout(function() {
                    appendTestData(listDom, 10);
                    miniRefresh.endUpLoading(listDom.children.length >= maxDataSize ? true : false);
                }, requestDelayTime);
            }
        }
    });
</script>
</body>
</html>