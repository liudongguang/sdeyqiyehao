<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
	<base href="${pageContext.request.contextPath }/"/>
	<title>生日祝福</title>
	<link rel="stylesheet" href="assets/bootstrap4.0.0-beta/css/bootstrap.css"/>
</head>
<body>
<div style=" height: 1610px">
	<img src="assets/img/birthday.jpg" style="width:100%; height: 100%;">
	<div style="position:absolute;color:#8d6551;font-size:45px;top:400px;font-family: 华文楷体;left:100px;padding: 15px;">
		<p>亲爱的xxx：</p><p>本月xx号是您的生日，在此祝您生日快乐，工作顺利！我们将于10号赠您100元餐补，请注意查收。谢谢！</p><p>山东大学第二医院</p></div>

	<audio src="assets/mp3/bir2.mp3" id="audio">
		Your browser does not support the audio tag.
	</audio>
</div>
<script language="javascript" type="text/javascript" src="assets/js/jquery3.2.1.js"></script>
<script language="javascript" type="text/javascript">
    $(function(){
        $("#audio")[0].play();
    })
</script>
</body>
</html>
