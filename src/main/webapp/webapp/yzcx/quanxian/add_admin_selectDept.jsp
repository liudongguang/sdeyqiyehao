<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>角色管理</title>
</head>
<body>
	<input id="basePath" type="hidden"
		value="${pageContext.request.contextPath }/" />
	<div id="departmentTree"></div>
</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript"
	src="assets/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript"
	src="assets/yzcx/quanxian/js/add_admin_selectDept.js">
	
</script>
</html>