<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" type="text/css" href="assets/yzcx/mui/css/mui.picker.min.css"/>
    <!--引入 自定义文件-->
    <link rel="stylesheet" href="assets/yzcx/css/general.css">
    <!--<script src="js/general.js" type="text/javascript"></script>-->

    <style>

    </style>
</head>

<body>
<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
    <div class="mui-inner-wrap">
        <!--------------侧滑菜单部分-------------->
        <%@ include file="../yzcxNav.jsp" %>
        <!------------页面主标题 ------------>
        <header class="mui-bar mui-bar-nav">
            <a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
            <h1 class="mui-title">全院住院情况</h1>
        </header>
        <!------------页面内容容器------------>
        <div id="offCanvasContentScroll" class="mui-content  mui-scroll-wrapper">
            <div class="mui-content-padded">
                <!--卡片（）-->
                <div class="mui-card">
                    <div class="mui-card-header">在院人数<fmt:formatNumber type="number" value="${obj.zaiyuanNum}"
                                                                       pattern="0"
                                                                       maxFractionDigits="0"/>
                        <span url="webyzcxZyxx/zhuyuan_yue_page">
                            月住院
                        </span>
                    </div>
                    <div class="mui-card-content">
                        <div class="mui-row totalBox">
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorA" style="width: 92%; margin-left: 6%;">
                                    <p>入院</p>
                                    <font><c:if test="${obj.ruyuan!=null}">
                                        <fmt:formatNumber type="number" value="${obj.ruyuan}" pattern="0"/></c:if>
                                        <c:if test="${obj.ruyuan==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorB">
                                    <p>出院</p>
                                    <font><c:if test="${obj.chuyuan!=null}">
                                        <fmt:formatNumber type="number" value="${obj.chuyuan}" pattern="0"/></c:if>
                                        <c:if test="${obj.chuyuan==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorC" style="width: 92%; margin-right: 6%;">
                                    <p>危重</p>
                                    <font><c:if test="${obj.weizhong!=null}">
                                        <fmt:formatNumber type="number" value="${obj.weizhong}" pattern="0"/></c:if>
                                        <c:if test="${obj.weizhong==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorD" style="width: 92%; margin-left: 6%;">
                                    <p>转入</p>
                                    <font><c:if test="${obj.zhuanru!=null}">
                                        <fmt:formatNumber type="number" value="${obj.zhuanru}" pattern="0"/></c:if>
                                        <c:if test="${obj.zhuanru==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorE">
                                    <p>转出</p>
                                    <font><c:if test="${obj.zhuanchu!=null}">
                                        <fmt:formatNumber type="number" value="${obj.zhuanchu}" pattern="0"/></c:if>
                                        <c:if test="${obj.zhuanchu==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorB" style="width: 92%; margin-right: 6%;">
                                    <p>死亡</p>
                                    <font><c:if test="${obj.siwang!=null}">
                                        <fmt:formatNumber type="number" value="${obj.siwang}" pattern="0"/></c:if>
                                        <c:if test="${obj.siwang==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="mui-card">
                    <div class="mui-card-header">入院人次波动图</div>
                    <div id="ri-ryrc" class="chart-box" style="width: 100%;height:300px;"></div>
                </div>
                <div class="mui-card">
                    <div class="mui-card-header">床位情况</div>
                    <div class="mui-card-content">
                        <div class="mui-row totalBox">
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorA" style="width: 92%; margin-left: 6%;">
                                    <p>开放床位</p>
                                    <font><c:if test="${obj.shizhan!=null}">
                                        <fmt:formatNumber type="number" value=" ${obj.kaifang}" pattern="0"/></c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorB">
                                    <p>使用床位</p>
                                    <font><c:if test="${obj.kaifang!=null}">
                                        <fmt:formatNumber type="number" value="${obj.shizhan}" pattern="0" /></c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorC" style="width: 92%; margin-right: 6%;">
                                    <p>床位使用率</p>
                                    <font><c:if test="${obj.cwshiyonglv!=null}">
                                        <fmt:formatNumber type="percent" value="${obj.cwshiyonglv}" maxFractionDigits="2" /></c:if></font>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="mui-card">
                    <div class="mui-card-header">科室入院人数排名（前十名）</div>
                    <div id="ri-ryrs" class="chart-box" style="width: 100%;height:550px;"></div>
                </div>
                <div class="mui-card">
                    <div class="mui-card-header">床位使用率排名（前十名）</div>
                    <div id="ri-cwsy" class="chart-box" style="width: 100%;height:550px;"></div>
                </div>
            </div>
        </div>
        <!--侧滑栏出现后，主页面遮罩层-->
        <div class="mui-off-canvas-backdrop"></div>
    </div>
</div>
<script language="javascript" type="text/javascript" src="assets/yzcx/mui/js/mui.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/echarts.common.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/wonderland.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/walden.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/mui/js/mui.picker.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/zhuyuan/index.js"></script>
</body>
</html>