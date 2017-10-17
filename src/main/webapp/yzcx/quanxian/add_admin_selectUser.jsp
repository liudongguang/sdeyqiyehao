<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/bootstrap-treeview/bootstrap-treeview.min.css" />
<link rel="stylesheet"
	href="assets/yzcx/quanxian/css/add_admin_selectUser.css" />
<title>角色管理</title>
</head>
<body>
	<div class="ldgfix">
		<a style="float: left;" href="javascript:history.back(-1)">选择部门</a>
		<span id="ksNameId">${ksname}</span>
		<button id="qdBTID" class="btn btn-info qdbtn">确定</button>
	</div>
	<ul class='kk list-unstyled list-inline'>
		<c:forEach items="${obj.userlist}" var="itm">
			<li><label><img alt="${itm.name}" src="${itm.avatar}" />
					<input type="radio" value="${itm.userid}" name="employeeID" /> <span
					style="padding-left: 2px"> ${fn:substring(itm.name, 0, 3)} </span>
			</label></li>
		</c:forEach>
	</ul>
</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript"
	src="assets/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript"
	src="assets/yzcx/quanxian/js/add_admin_selectUser.js">
	
</script>
</html>