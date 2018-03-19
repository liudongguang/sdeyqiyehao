
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<input type="hidden" value="${pageContext.request.contextPath }"
		id="basePath" />
	<input type="hidden" value="${param.code}" id="codeID" />
	<script src="assets/messageboard/js/jquery.min.js"></script>
	<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
	<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
	<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
	<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
	<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
	<script src="assets/messageboard/js/login.js"></script>
</body>
</html>