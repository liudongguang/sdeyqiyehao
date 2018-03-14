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
</head>
<body class="bdbg">
<div class="group-body nav_bgc blog_header_pd">
    <header class="navbar">
        <span class="navbar-title navbar-center">${object.title}</span>

        <div class="navbar-nav navbar-right">
            <span class="navbar-title navbar-right">返回</span>
        </div>
    </header>
</div>
<div class="em_all">
    <div class="tb_pad group-body">
        <ul class="list">
            <li target="_blank" class="item item-content blog_li">
                <div class="blog_divs">
                    <div class="item-media">
                        <a href="">
                            <img class="blog_head_img" width="48" src="${object.wxheadimg}">
                        </a>
                    </div>
                    <div class="item-main">
                        <a href="">
                            <div class="item-title-row">
                                <h2 class="item-title jy_tit">${object.wxqyusername}</h2>
                            </div>
                            <div class="item-title-row">
                                <div class="item-subtitle jy_txt"><fmt:formatDate
                                        value="${object.createtime}" pattern=" yyyy-MM-dd HH:mm"/></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="blog_divs_pad">
                    <p class="blog_main_p">${object.content}
                    </p>
                </div>
            </li>
        </ul>
        <div class="blog_divs_say_show">
            <c:if test="${fn:length(object.pingLunByLY) gt 0}">
                <span class="all_says">全部评论</span>
                <ul class="list">
                    <c:forEach items="${object.pingLunByLY}" var="pinglun">
                        <li class="blog_divs_show_pad item-content">
                            <div class="blog_divs_show">
                                <div class="item-media">
                                    <a href="">
                                        <img class="blog_head_img" width="42" src="images/2421.png">
                                    </a>
                                </div>
                                <div class="item-main">
                                    <a href="">
                                        <div class="item-title-row">
                                            <h2 class="item-title jy_tit color_nam size_txt">${pinglun.wxqyusername}</h2>
                                        </div>
                                        <div class="item-title-row">
                                            <div class="item-subtitle size_txt"><fmt:formatDate
                                                    value="${pinglun.createtime}" pattern=" yyyy-MM-dd HH:mm"/></div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="blog_divs_pad">
                                <p class="blog_main_p">${pinglun.hfnr}
                                </p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
        <div class="am-g send_fixed am-u-sm-12">
            <div class="am-u-lg-12">
                <div class="am-input-group">
                    <input type="text" class="am-form-field send_ipt">
                    <span class="am-input-group-btn">
                        <button class="am-btn send_btn send_ipt"
                                style=""
                                type="button">发表
        </button>
      </span>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="assets/messageboard2/js/jquery-3.2.1.js"></script>
<script src="assets/messageboard2/js/amazeui.min.js"></script>
<script src="assets/messageboard2/js/app.js"></script>
<script src="assets/messageboard2/js/emotion.js"></script>
</body>
</html>