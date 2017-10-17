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
<title>JSSDK1</title>
</head>
<body>
	<input type="hidden" value="${pageContext.request.contextPath }"
		id="basePah" />
	<div>
		开发JSSDK
		<button id="dkxcID">打开相册</button>
		<button id="yltpID">预览图片</button>
	</div>
	<div id="imgsDiv"></div>
	<div id="imgSrc"></div>
</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript" src="js/jweixin-1.1.0.js"></script>
<script type="text/javascript" src="js/jssdkCommon.js"></script>
<script type="text/javascript" src="js/jssdkAjax.js"></script>
</html>