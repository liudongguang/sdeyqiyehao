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
    <!--引入 自定义文件-->
    <link rel="stylesheet" href="assets/yzcx/css/general.css">
    <link rel="stylesheet" href="assets/yzcx/css/page.css">
</head>

<body>
<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
    <div class="mui-inner-wrap">
        <!--------------侧滑菜单部分-------------->
        <%@ include file="yzcxNav.jsp" %>
        <!------------页面主标题 ------------>
        <header class="mui-bar mui-bar-nav">
            <a href="#offCanvasSide" class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"></a>
            <h1 class="mui-title">全院概览</h1>
        </header>
        <!------------页面内容容器------------>
        <div id="offCanvasContentScroll" class="mui-content mui-scroll-wrapper">
            <div class="mui-content-padded">
                <!--卡片（门急诊）-->
                <div class="mui-card">
                    <div class="mui-card-header">门诊急诊（今日） <span class="grayText">共<fmt:formatNumber type="number"
                                                                                                    value="${obj.mzsum+obj.jzsum}"
                                                                                                    pattern="0"
                                                                                                    maxFractionDigits="0"/>人</span>
                    </div>
                    <div class="mui-card-content">

                        <!--总数模块区-->
                        <div class="mui-row totalBox">
                            <ul class="mui-col-sm-6 mui-col-xs-6">
                                <li class="total-colorA" style="width: 92%; margin-left: 6%;">
                                    <p>出诊医生总数</p>
                                    <font><fmt:formatNumber type="number" value="${obj.ysgs}" pattern="0"
                                                            maxFractionDigits="0"/>位</font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-6 mui-col-xs-6">
                                <li class="total-colorB" style="width: 92%; margin-right: 6%;">
                                    <p>医生平均门急诊量</p>
                                    <font><fmt:formatNumber type="number" value="${(obj.mzsum+obj.jzsum)*1.0/obj.ysgs}"
                                                            pattern="0"
                                                            maxFractionDigits="2"/>人次</font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-6 mui-col-xs-6">
                                <li class="total-colorC" style="width: 92%; margin-left: 6%;">
                                    <p>处方数量</p>
                                    <font><fmt:formatNumber type="number" value="${obj.chufangshu}" pattern="0"
                                                            maxFractionDigits="0"/>个</font>
                                </li>
                            </ul>

                            <ul class="mui-col-sm-6 mui-col-xs-6">
                                <li class="total-colorD" style="width: 92%; margin-right: 6%;">
                                    <p>医生平均处方数量</p>
                                    <font><fmt:formatNumber type="number" value="${(obj.chufangshu)*1.0/obj.ysgs}"
                                                            pattern="0"
                                                            maxFractionDigits="2"/>个</font>
                                </li>
                            </ul>
                        </div>
                        <!--图表容器 门诊急诊-->
                        <input type="hidden" id="menzhensumID" value="${obj.mzsum}"/>
                        <input type="hidden" id="jizhensumID" value="${obj.jzsum}"/>
                        <div id="pie-outPatient" style="width:100%;height:240px; margin-top: 20px;"></div>
                        <div class="mui-row" style="border-top:1px solid #ebebeb">
                            <div class="mui-col-sm-12 mui-col-xs-12 bedUse-profile">处方总额：<fmt:formatNumber type="number"
                                                                                                           value="${obj.sumchufang}"
                                                                                                           pattern="0"
                                                                                                           maxFractionDigits="2"/>元
                            </div>
                        </div>
                        <!--图表容器 处方金额-->
                        <input type="hidden" id="pjchufangID" value="${obj.pjchufang}"/>
                        <input type="hidden" id="maxchufangID" value="${obj.maxchufang}"/>
                        <input type="hidden" id="minchufangID" value="${obj.minchufang}"/>
                        <div id="bar-recipel" style="width: 100%;height:260px;"></div>
                    </div>
                </div>
                <!--卡片（在院人数）-->
                <div class="mui-card">
                    <div class="mui-card-header">在院人数（今日）<span class="grayText">共<fmt:formatNumber type="number"
                                                                                                   value="${obj.zhuyuan.zaiyuanNum}"
                                                                                                   pattern="0"
                                                                                                   maxFractionDigits="0"/>人</span>
                    </div>
                    <div class="mui-card-content">
                        <!--总数模块区-->
                        <div class="mui-row totalBox">
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorA" style="width: 92%; margin-left: 6%;">
                                    <p>入院</p>
                                    <font><c:if test="${obj.zhuyuan.ruyuan!=null}">
                                        <fmt:formatNumber type="number" value="${obj.zhuyuan.ruyuan}"
                                                          pattern="0"/></c:if>
                                        <c:if test="${obj.zhuyuan.ruyuan==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorB">
                                    <p>出院</p>
                                    <font><c:if test="${obj.zhuyuan.chuyuan!=null}">
                                        <fmt:formatNumber type="number" value="${obj.zhuyuan.chuyuan}"
                                                          pattern="0"/></c:if>
                                        <c:if test="${obj.zhuyuan.chuyuan==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorC" style="width: 92%; margin-right: 6%;">
                                    <p>病重</p>
                                    <font><c:if test="${obj.zhuyuan.weizhong!=null}">
                                        <fmt:formatNumber type="number" value="${obj.zhuyuan.weizhong}"
                                                          pattern="0"/></c:if>
                                        <c:if test="${obj.zhuyuan.weizhong==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorD" style="width: 92%; margin-left: 6%;">
                                    <p>转入、转出</p>
                                    <font><c:if test="${obj.zhuyuan.zhuanru!=null}">
                                        <fmt:formatNumber type="number" value="${obj.zhuyuan.zhuanru}"
                                                          pattern="0"/></c:if>
                                        <c:if test="${obj.zhuyuan.zhuanru==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorE">
                                    <p>死亡</p>
                                    <font><c:if test="${obj.zhuyuan.siwang!=null}">
                                        <fmt:formatNumber type="number" value="${obj.zhuyuan.siwang}"
                                                          pattern="0"/></c:if>
                                        <c:if test="${obj.zhuyuan.siwang==null}">
                                            0</c:if></font>
                                </li>
                            </ul>

                        </div>
                        <div class="mui-row" style="border-top:1px solid #ebebeb">
                            <div class="mui-col-sm-12 mui-col-xs-12 bedUse-profile">床位使用率：<fmt:formatNumber
                                    type="number" value="${obj.zhuyuan.cwshiyonglv}"
                                    pattern="0"
                                    maxFractionDigits="2"/>%
                            </div>
                        </div>
                        <!--图表容器 床位使用-->
                        <input type="hidden" id="shizhanID" value="${obj.zhuyuan.shizhan}"/>
                        <input type="hidden" id="kaifangID" value="${obj.zhuyuan.kaifang}"/>
                        <div id="bar-inHospital" style="width: 100%;height:220px;"></div>
                    </div>
                </div>
                <div class="mui-card">
                    <div class="mui-card-header">手术情况</div>
                    <div class="mui-card-content">
                        <!--总数模块区-->
                        <div class="mui-row totalBox">
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorA" style="width: 92%; margin-left: 6%;">
                                    <p>手术按排量</p>
                                    <font><c:if test="${obj.shoushudata.anpai!=null}">
                                        <fmt:formatNumber type="number" value="${obj.shoushudata.anpai}"
                                                          pattern="0"/></c:if>
                                        <c:if test="${obj.shoushudata.anpai==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorB">
                                    <p>实际手术量</p>
                                    <font><c:if test="${obj.shoushudata.shijiss!=null}">
                                        <fmt:formatNumber type="number" value="${obj.shoushudata.shijiss}"
                                                          pattern="0"/></c:if>
                                        <c:if test="${obj.shoushudata.shijiss==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                            <ul class="mui-col-sm-4 mui-col-xs-4">
                                <li class="total-colorC" style="width: 92%; margin-right: 6%;">
                                    <p>明日手术按排量</p>
                                    <font><c:if test="${obj.shoushudata.nextDayanpai!=null}">
                                        <fmt:formatNumber type="number" value="${obj.shoushudata.nextDayanpai}"
                                                          pattern="0"/></c:if>
                                        <c:if test="${obj.shoushudata.nextDayanpai==null}">
                                            0</c:if></font>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--卡片（收入分析）-->
                <div class="mui-card">
                    <div class="mui-card-header">收入（昨日）</div>
                    <div class="mui-card-content">
                        <!--总数模块区-->
                        <div class="totalBox">
                            <span>昨日总收入</span>
                            <b>￥<fmt:formatNumber type="number"
                                                  value="${obj.indexFeiYongZong.zhuyuanzong+obj.indexFeiYongZong.menzhenzong}"
                                                  pattern="0"
                                                  maxFractionDigits="2"/></b>
                        </div>
                        <!--图表容器-->
                        <input type="hidden" id="yiLiaoID" value="${obj.yiLiao}"/>
                        <input type="hidden" id="yaoID" value="${obj.yao}"/>
                        <input type="hidden" id="qiTaID" value="${obj.qiTa}"/>
                        <div id="pie-income" style="width:100%;height:260px; margin: 20px 0px;"></div>
                    </div>
                </div>

            </div>
        </div>
        <!--侧滑栏出现后，主页面遮罩层-->
        <div class="mui-off-canvas-backdrop"></div>
    </div>
</div>
<!--引入 mui文件-->
<script src="assets/yzcx/mui/js/mui.min.js"></script>
<!--引入 ECharts文件 -->
<script src="assets/yzcx/echarts/echarts.common.min.js"></script>
<script src="assets/yzcx/echarts/walden.js"></script>
<script type="text/javascript" src="assets/yzcx/index.js"></script>
</body>
</html>