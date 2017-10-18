<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>留言板管理</title>
<link rel="stylesheet"
	href="assets/bootstrap4.0.0-beta/css/bootstrap.css" />
<link rel="stylesheet" href="assets/css/index_fix.css" />
</head>
<body>
	<div class="container-fluid">
		<div class="col-md-12" id="pajaxMainContainer" style="padding: 10px">
			<form method="post" id="subForm"
				action="${pageContext.request.contextPath }/messageboard/managerLiuYan"
				class="form-inline" style="margin: 10px">
				<div class="form-group">
					<label>用户名：</label> <input type="text" name="userName"
						value="${param.userName}" class="form-control mx-sm-3"
						placeholder="搜索的用户名" />
				</div>
				<div class="form-group">
					<label>关键字：</label> <input type="text" placeholder="搜索的关键字"
						value="${param.guanjianzi}" name="guanjianzi"
						class="form-control mx-sm-3">
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-outline-primary btn-sm">搜索</button>
				</div>
			</form>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>标题</th>
						<th>发布人</th>
						<th>内容</th>
						<th>发布时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="obj">
						<tr>
							<td>${obj.title}</td>
							<td>${obj.wxqyusername}</td>
							<td><div
									style="max-width: 110px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${obj.content}</div></td>
							<td><fmt:formatDate value="${obj.createtime}"
									pattern=" yyyy-MM-dd HH:mm:ss" /></td>
							<th scope="row">
								<button class="btn btn-outline-danger table_bt_cz" button-data
									del="del"
									href="${pageContext.request.contextPath }/messageboard/deleteLiuYanById?messageid=${obj.id}">
									<div class="table_btspan_cz">删除</div>
								</button>
								<button class="btn btn-outline-primary table_bt_cz" button-frame
									href="${pageContext.request.contextPath }/messageboard/getLiuYanByID?id=${obj.id}">
									<div class="table_btspan_cz">查看</div>
								</button>
								<button class="btn btn-outline-success table_bt_cz_fourzi"
									button-frame width="65%" height="80%"
									href="${pageContext.request.contextPath }/messageboard/getLiuYanPingLunByID?messageid=${obj.id}&toptitle=${obj.title}">
									<div class="table_btspan_cz">评论管理</div>
								</button>
							</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="pagesDIV"
				class="d-flex justify-content-end align-items-center"></div>
			<input id="pageNum" type="hidden" value="${page.pageNum}" /> <input
				id="pageSize" type="hidden" value="${page.pageSize}" /> <input
				id="pages" type="hidden" value="${page.pages}" /> <input id="total"
				type="hidden" value="${page.total}" /> <input id="loadDataURL"
				type="hidden" value="messageboard/managerLiuYan" /> <input
				id="searFormID" type="hidden" value="subForm" /> <input
				id="containerID" type="hidden" value="pajaxMainContainer" />
		</div>
	</div>

	<script language="javascript" type="text/javascript"
		src="assets/js/jquery3.2.1.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/bootstrap4.0.0-beta/popper.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/js/pajax/jquery.pjax.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/nprogress-0.2.0/nprogress.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/layer/layer.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/js/commonMain.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/js/page/jPage.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/js/page/jPageExt.js"></script>
	<script language="javascript" type="text/javascript"
		src="assets/js/liuyangl.js"></script>
</body>
</html>
