<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 每次加载删除之前的隐藏文本域 -->
<input id="pltotalid" type="hidden" value="${page.total}" />
<c:forEach items="${page.list}" var="l">
	<div class="container div_bot">
		<div class="container1 div_bot1">
			<!--用户头像-->
			<div class="header">
				<div>
					<a href="javascript:void(0);"><img class="head_img1" src="${l.wxheadimg}"></a>
				</div>
			</div>
			<div class="right_con2">
				<div class="demo" style="text-align: left">
					<div class="use1">
						<div class="usename1 am-list-item-hd">
							<div class="top_line"><span class="re_name">${l.wxqyusername}</span>
							<span class="re_time"><fmt:formatDate
									value="${l.createtime}" pattern=" yyyy-MM-dd HH:mm:ss" /></span>
								</div>	
							<p class="fx_content3 list_p1">${l.hfnr}</p>
							<br />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
<div id="morelistID"></div>
<div
	style="width: 100%; height: 40px; clear: left; background-color: white !important;">
	<!-- page.pages==page.pageNum 最后一页显示-->
	<p
		class="<c:if test="${page.pages==page.pageNum||page.pages==0}">show</c:if><c:if test="${page.pages!=0&&page.pages!=page.pageNum}">hide</c:if>"
		id="noMore"
		style="line-height: 40px; text-align: center; font-size: 14px; color: #0087f9">没有更多评论</p>

	<!-- page.pages==page.pageNum 是最后一页我隐藏 -->
	<p id="moreLiuYan" pageNum="${page.pageNum}"
		class="<c:if test="${page.pages==page.pageNum||page.pages==0}">hide</c:if><c:if test="${page.pages!=0&&page.pages!=page.pageNum}">show</c:if>"
		style="line-height: 40px; text-align: center; font-size: 14px; color: #0087f9; cursor: pointer;">查看更多评论</p>
</div>
<script src="assets/messageboard/js/listshow_pinglun.js"></script>

