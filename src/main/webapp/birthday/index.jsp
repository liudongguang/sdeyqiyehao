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
	<div style="position:absolute;color:#8d6551;font-size:45px;top:150px;font-family: 华文楷体;left:100px;padding: 15px;">
		<p>亲爱的${param.xingming}：</p><p style="text-indent:2em">时光的树越长越葱茏，生命的花越开越艳丽！一年一度，每一次生日都是崭新的开始，每一次祝福都是浓浓的情谊！
	</p>
	  <p style="text-indent:2em">${param.month}月${param.day}日是您的生日，山东大学齐鲁第二医院祝您生日快乐！愿您幸福安康、顺意连年！</p>
		<p style="text-indent:2em">100元生日补贴已发放到您的善德餐卡中，浓浓的祝福请您收下。</p>
		<p style="text-align: right">山东大学齐鲁第二医院工会</p>
	</div>
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
