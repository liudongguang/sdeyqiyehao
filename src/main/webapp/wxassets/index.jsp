<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源接口</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet"
	href="assets/bootstrap-treeview/bootstrap-treeview.min.css" />
</head>
<body>
	<input type="hidden" value="${pageContext.request.contextPath }"
		id="basePah" />
	<a href="${pageContext.request.contextPath }/wxassets/getqyApp">获取企业号应用</a>
	<br />
	<a href="${pageContext.request.contextPath }/wxassets/setqyApp">设置企业号应用</a>
	<br />
	<a href="${pageContext.request.contextPath }/wxassets/getqyAppList">企业号应用列表</a>
	<br />
	<hr />
	<h1>管理通讯录</h1>
	<a href="${pageContext.request.contextPath }/wxassets/getAllDepart"></a>
	<button class="btn btn-default" id="getAllDepartBT">企业号部门列表</button>
	<br />
	<div class="row">
		<div class="col-sm-4">
			<div id="treeview1" class=""></div>
		</div>
		<div class="col-sm-4"></div>
		<div class="col-sm-4"></div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<a href="wxassets/TreeViewDemo.jsp">bootStrapTreeDemo</a><br /> <a
				href="wxassets/ztreeDepartment.jsp">ztreeDepartment
				简单数据类型，与普通数据类型（递归产生）</a> <br /> <a href="wxassets/ztreeDepartment2.jsp">bootStrapTreeDemo2
				加复选框，勾选会出现对应的处理</a><br /> <a
				href="wxassets/ztreeDepartmentForSendMsg.jsp">ztreeDepartmentForSendMsg
				选择发送信息</a><br /> <a href="wxassets/ztreeDepartmentForSendMsg2.jsp">ztreeDepartmentForSendMsg2
				选择发送信息，有头像</a>
		</div>
	</div>

</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript" src="assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript"
	src="assets/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="js/wxassets.js"></script>
</html>