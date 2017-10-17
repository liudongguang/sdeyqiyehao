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
<title>OAuthTest2...</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.css" />
</head>
<body>
	<input type="hidden" value="${pageContext.request.contextPath }"
		id="basePath" />
	<input type="hidden" value="${param.code}" id="codeID" /> 姓名：
	<span id="nameID"></span>
	<br /> 头像：
	<img width="200px" height="200px" alt="" src="" id="avatarID">
	<div>
		<button class="btn btn-default" id="dkxcID">打开相册</button>
		<button class="btn btn-default" id="yltpID">预览图片</button>
		<button class="btn btn-default" id="getPositionID">所在位置</button>
		<button class="btn btn-default" id="openPositionID">打开地图</button>
	</div>
	<div>

		<span id="positionID"></span>
	</div>
	<div id="imgsDiv"></div>
	<div id="imgSrc"></div>
</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript" src="assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript" src="js/jweixin-1.1.0.js"></script>
<script type="text/javascript" src="js/jssdkCommon.js"></script>
<script type="text/javascript" src="js/OAuthTest2.js">
	
</script>
</html>