<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OAuthTest</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery3.1.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var codeIDVal = $("#codeID").val();
		if (codeIDVal) {
			$.post('${pageContext.request.contextPath }/wxsq/getUserInfo', {
				'code' : codeIDVal
			}, function(data) {
				alert(data)
			}, 'json');
		}
	});
</script>
</head>
<body>
	<input type="hidden" value="${param.code}" id="codeID" />
</body>
</html>