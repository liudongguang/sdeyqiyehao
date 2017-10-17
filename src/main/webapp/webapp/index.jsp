<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery3.1.0.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#testBT")
								.click(
										function() {
											$
													.post(
															'${pageContext.request.contextPath }/test/testTransaction',
															null,
															function(data) {
																alert(data);
																console
																		.log(data);
															}, 'json');
										});
						$("#testBT2")
								.click(
										function() {
											$
													.ajax({
														url : '${pageContext.request.contextPath }/test/testTransaction',// 跳转到 action  
														data : {

														},
														type : 'post',
														cache : false,
														dataType : 'json',
														success : function(data) {
															alert(data);
														},
														error : function(
																httpxml,
																errinfo) {
															// view("异常！");  
															alert("异常！"
																	+ errinfo);
														}
													});
										});
					});
</script>
</head>
<body>Welcome ............  <%=request.getRequestURL() %>
</body>
</html>