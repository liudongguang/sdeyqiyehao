<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath }/"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>山大二院</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <link rel="stylesheet" href="assets/messageboard2/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/messageboard2/css/style.css">
    <link rel="stylesheet" href="assets/messageboard2/css/abc.css">
    <style>
        .more {
            width: 100%;
            text-align: center;
            display: block;
            padding: 5px
        }
    </style>
</head>
<body class="bdbg">
<div class="group-body nav_bgc blog_header_pd">
    <header class="navbar">
        <span class="navbar-title navbar-center">留言板列表</span>
        <div class="navbar-nav navbar-right">
            <span class="navbar-title navbar-right">留言</span>
        </div>
    </header>
</div>

<div class="em_all">
    <form action="messageboard/liuYanList" id="subForm" method="post">
        <div class="am-g send_fixed">
            <div class="am-u-lg-12">
                <div class="am-input-group">
                    <input type="text" class="am-form-field send_ipt" name="title" value="${param.title}"
                           placeholder="模糊搜索标题..">
                    <span class="am-input-group-btn">
                        <button class="am-btn send_btn send_ipt" type="submit">
                            搜索
                        </button></span>
                </div>
            </div>
        </div>
    </form>
    <div class="tb_pad group-body">
        <input type="hidden" value="${page.pageNum}" id="pageNumID"/>
        <input type="hidden" value="${page.pages}" id="pagesID"/>
        <!--数据列表-->
        <ul id="datacontentID" class="list">
            <c:choose>
                <c:when test="${fn:length(page.list) gt 0}">
                    <c:forEach items="${page.list}" var="lymsg">
                        <li target="_blank" class="item item-content blog_li">
                            <div class="blog_divs">
                                <div class="item-media">
                                    <a href="">
                                        <img class="blog_head_img" width="48" src="${lymsg.wxheadimg}">
                                    </a>
                                </div>
                                <div class="item-main">
                                    <a href="">
                                        <div class="item-title-row">
                                            <h2 class="item-title jy_tit">${lymsg.wxqyusername}</h2>
                                        </div>
                                        <div class="item-title-row">
                                            <div class="item-subtitle"><fmt:formatDate
                                                    value="${lymsg.createtime}"
                                                    pattern=" yyyy-MM-dd HH:mm:ss"/></div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="blog_divs_pad">
                                <p class="blog_main_p">
                                        ${lymsg.content}
                                </p>
                            </div>
                            <div class="blog_divs_say">
                                <c:if test="${fn:length(lymsg.pingLunByLY) gt 0}">
                                    <span class="all_says">全部评论</span>
                                    <c:forEach items="${lymsg.pingLunByLY}" var="pinglun">
                                        <div class="says_txt">
                                            <span>${pinglun.wxqyusername}：</span>
                                            <span>${pinglun.hfnr}</span>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>
                </c:when>
            </c:choose>
        </ul>
    </div>
    <span class="more" id="loadMore" onclick="load()"><a href="javascript:void(0)"
                                                         data-am-modal="{target: '#my-alert'}" style="font-size: 14px">更多</a></span>
</div>

<script src="assets/messageboard2/js/jquery-3.2.1.js"></script>
<script src="assets/messageboard2/js/amazeui.min.js"></script>
<script src="assets/messageboard2/js/app.js"></script>
<script src="assets/messageboard2/js/emotion.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script src="assets/messageboard2/list.js"></script>
</body>
</html>