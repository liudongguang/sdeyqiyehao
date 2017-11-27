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
<title>员工自助</title>
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath }"
	   id="basePath" />
<input type="hidden" value="${param.code}" id="codeID" />
<!--
	 姓名：
	<span id="nameID"></span>
	<br /> 头像：
	<img width="200px" height="200px" alt="" src="" id="avatarID">
    <br/>
ID: <span id="userID"></span>
-->
</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript" src="js/OAuthForSKHY.js"></script>
</html>