<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 每次加载删除之前的隐藏文本域 -->
<div id="morenumID">
	<input type="hidden" value="${morepage.pages}" id="morepages" /> <input
		type="hidden" value="${morepage.pageNum}" id="morepageNum" />
</div>
<c:forEach items="${morepage.list}" var="l">
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
							<p class="fx_content1 list_p4">${l.wxqyusername}</p>
							<span class="list_span6"><fmt:formatDate
									value="${l.createtime}" pattern=" yyyy-MM-dd HH:mm:ss" /></span>
							<p class="fx_content3 list_p1">${l.hfnr}</p>
							<br />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>


