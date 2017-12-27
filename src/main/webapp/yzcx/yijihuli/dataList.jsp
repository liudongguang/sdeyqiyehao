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
    <link rel="stylesheet" href="assets/yzcx/css/page.css">
    <link rel="stylesheet" href="assets/css/baidu.css"/>
</head>
<body>
<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
    <div class="mui-inner-wrap">
        <!--------------侧滑菜单部分-------------->
        <%@ include file="../yzcxNav.jsp" %>
        <!------------页面主标题 ------------>
        <header class="mui-bar mui-bar-nav">
            <a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
            <h1 class="mui-title">一级护理</h1>
        </header>
        <!--搜索-->
        <div class="searchBox">
            <div class="mui-input-row mui-search">
                <input type="search" class="mui-input-clear search" placeholder="科室名称"
                       fangbaidu_searurl="hisoffice/searchKS">
                <input type="hidden" value="6" id="navNum"/>
                <input type="hidden" value="" id="pageNumID"/>
                <input type="hidden" value="" id="ksNameID"/>
            </div>
            <div id="disRSDIVID" class="bdsug" style="height: auto; display: none;">
                <ul id="addULID">
                </ul>
            </div>
        </div>

        <!--列表-->
        <!------------页面内容容器------------>
        <div id="offCanvasContentScroll" class="mui-content mui-scroll-wrapper"
             style="padding-top:0px;margin-top:90px;">
            <div class="mui-content-padded">

                <div class="mui-card table-box" style="margin: 0px 0px 10px 0px;">
                    <div class="mui-card-content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               class="center datatable listTable">
                            <thead>
                            <tr>
                                <td>序号</td>
                                <td>住院号码</td>
                                <td>病人姓名</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>01</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td id="viewDetail">
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>02</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>03</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>04</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>05</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>06</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>07</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>08</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>09</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>10</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>11</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>12</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>13</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>14</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>15</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>16</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>17</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>18</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>19</td>
                                <td>2468</td>
                                <td>李大维</td>
                                <td>
                                    <span class="mui-icon iconfont icon-xiangqing1"></span>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                    </div>
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
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/echarts.common.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/wonderland.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/echarts/walden.js"></script>
<script language="javascript" type="text/javascript" src="assets/yzcx/yijihuli/dataList.js"></script>
<script type="text/javascript" language="javascript" src="assets/js/baidu.js"></script>
</body>
</html>