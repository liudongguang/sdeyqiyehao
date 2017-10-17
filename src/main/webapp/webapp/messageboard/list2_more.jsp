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
	<li>
		<div class="container div_bot">
			<!--用户头像-->
			<div class="header">
				<div>
					<a href="messageboard/getLiuYanByID?id=${l.id}"><img
						class="head_img" src="${l.wxheadimg}"></a>
				</div>
			</div>
			<div class="right_con">
				<a href="messageboard/getLiuYanByID?id=${l.id}">
					<div class="demo" style="text-align: left">
						<div class="use">
							<div class="usename am-list-item-hd">
								<p class="fx_content list_p1">${l.title}</p>
								<br />
								<p class="fx_content1 list_p2">${l.wxqyusername}</p>
								<span class="list_span1"><img
									src="assets/messageboard/images/say.png"
									style="width: 13px; vertical-align: middle" alt="" /><span>&nbsp;</span>${l.pinglunCount}</span>
								<span class="list_span2"><fmt:formatDate
										value="${l.createtime}" pattern=" yyyy-MM-dd HH:mm:ss" /></span>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</li>
</c:forEach>
