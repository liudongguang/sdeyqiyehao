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
<title>企业会话</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.css" />
</head>
<body>
	<input type="hidden" value="${pageContext.request.contextPath }"
		id="basePah" />
	<div style="width: 200px; height: 200px; margin:0 auto; margin-top:35%">
		<button id="selectMember" style="width: 200px; height: 200px;"
			class="btn btn-default">打开企业通讯录</button>
	</div>
</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript" src="assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="js/jweixin-1.1.0.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript" src="js/huihuan2.js">
	
</script>
</html>