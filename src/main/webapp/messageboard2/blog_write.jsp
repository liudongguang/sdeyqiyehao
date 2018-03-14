<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath }/"/>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>山大二院</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp">
	<link rel="stylesheet" href="assets/messageboard2/css/amazeui.min.css">
	<link rel="stylesheet" href="assets/messageboard2/css/style.css">
	<link rel="stylesheet" href="assets/messageboard2/css/abc.css">
	<link rel="stylesheet" href="assets/messageboard2/css/emotion.css">
</head>
<body class="bdbg">
<div class="group-body nav_bgc blog_header_pd">
	<header class="navbar">
		<span class="navbar-title navbar-center">写留言</span>
		<div class="navbar-nav navbar-right">
			<span class="navbar-title navbar-right">发表</span>
		</div>
	</header>
</div>
<div class="em_all">
	<div class="tb_pad group-body">
		<div class="am-g">
			<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
				<form method="post" class="am-form">
					<div id="Demo">
						<div class="Main">
							<div class="Input_Box">
								<textarea placeholder="文本框中显示文字不超过500字，内容将由管理员筛选后发布.." class="Input_text"></textarea>
								<div class="faceDiv"> </div>
								<div class="Input_Foot"> <a class="imgBtn" href="javascript:void(0);"><img style="width: 24px;" src="assets/messageboard2/images/imgs.png" alt=""/></a></div>
							</div>
						</div>
					</div>
					<div class="am-form-group am-form-file am-margin-top-sm">
						<button type="button" class=" am-btn am-btn-default am-radius am-text-x"><img class="plus" src="assets/messageboard2/images/plus.png" alt=""/></button>
						<input id="doc-form-file" type="file" multiple>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="assets/messageboard2/js/jquery-3.2.1.js"></script>
<script src="assets/messageboard2/js/amazeui.min.js"></script>
<script src="assets/messageboard2/js/app.js"></script>
<script src="assets/messageboard2/js/emotion.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>

</body>
</html>