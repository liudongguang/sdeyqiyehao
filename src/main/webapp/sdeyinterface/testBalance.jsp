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
<link rel="stylesheet" href="assets/zTree/css/zTreeStyle/zTreeStyle.css" />
</head>
<body>
	<div class="container">
		<input type="hidden" value="${pageContext.request.contextPath }"
			id="basePah" />
		<h1>管理通讯录</h1>
		<div class="row">
			<div class="col-md-3">
				<button class="btn btn-default" id="getAllSimpleDataDepartBT">SimpleData企业号部门列表</button>
				<ul id="departmentTreeForSimpleData" class="ztree"
					style="max-height: 830px; overflow-y: scroll;"></ul>
			</div>
			<div class="col-md-9">
				<div style="max-height: 830px; overflow-y: scroll;">
					<form id="subForm" action="wxassets/SendMsgToEmployeeForSDEY"
						method="post">
						<div id="checkedInfo"></div>
						流水号：<input type="text" name="serialNumber" value="22111" /><br />
						操作类型：<input type="text" name="type" value="1" /><br /> 金额：<input
							type="text" name="balance" value="23.66" /><br /> 日期：<input
							type="text" name="date" value="2016-12-05 18:21:33" /><br />
						余额：<input type="text" name="currentBalance" value="233.11" /><br />
						备注：<input type="text" name="remarks" value="我是备注信息。。。。。。" /><br />
					</form>
					<button id="subBT">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript" src="assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript" src="assets/zTree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="assets/js/ajaxForm.js"></script>
<script type="text/javascript"
	src="assets/js/sdeyinterface/testBalance.js"></script>
</html>