
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>留言板</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">-->
<link rel="stylesheet" href="assets/messageboard/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/messageboard/css/style.css" />
<link rel="stylesheet" href="assets/messageboard/css/index.css" />
</head>
<body class="body_color">
	<input type="hidden" value="${pageContext.request.contextPath }"
		id="basePath" />
	<div class="am-g">
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<form action="messageboard/addLiuYan" method="post" class="am-form"
				id="subformID">
				<input type="hidden" value="${sessionScope.session_wxUSER.userid}"
					id="wxUserID" name="wxqyuserid" /> <input type="hidden"
					value="${sessionScope.session_wxUSER.name}" id="wxUserNameID"
					name="wxqyusername" /> <input type="hidden"
					value="${sessionScope.session_wxUSER.avatar}" id="wxTouxiangID"
					name="wxheadimg" /> <br>
				<div class="am-form-group">
					<input class="input_border" type="text" id="doc-ipt-email-1"
						maxlength="16" name="title" placeholder="请输入话题的标题(16字以内)">
				</div>
				<div id="Demo" style="text-align: center;">
					<div class="Main">
						<div class="Input_Box">
							<textarea class="Input_text" placeholder="请输入话题内容(500字以内).."
								id="contentID" name="content" maxlength="500"></textarea>
							<div class="faceDiv"></div>
							<div class="Input_Foot">
								<a class="imgBtn" href="javascript:void(0);"><img
									style="width: 24px;" src="assets/messageboard/images/imgs.png"
									alt="" /></a>
							</div>
						</div>
					</div>
				</div>
				<div>
					<input type="hidden" id="mediaimagesid" name="messageimages" />
					<div id="lyimgdiv"></div>
					<div class="am-form-group am-form-file am-fl up_all">
						<button type="button" id="dkxcID"
							class="am-btn am-btn-default am-radius am-text-x up_plus">
							<img style="width: 35px; height: 35px;"
								src="assets/messageboard/images/plus.png" alt="" />
						</button>
					</div>
					<div id="init" class="am-form-group am-form-file am-fl up_all">
						<button type="button"
							class=" am-btn am-btn-default am-radius am-text-x up_plus">
							<img style="width: 35px; height: 35px;"
								src="assets/messageboard/images/minus.png" alt="" />
						</button>
						<!--<input  type="file" multiple>-->
					</div>
					<div class="am-form-group am-form-file am-fl up_all">
						<img style="width: 90px; height: 45px; border-radius: 5px;"
							src="assets/messageboard/images/max.jpg" alt="" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="am-u-sm-12">
		<a href="list.html"><input type="button" name="" value="提交"
			id="submitBT" class="btn_color am-btn am-btn-primary am-btn-block"></a>
	</div>
	<script src="assets/messageboard/js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jweixin-1.1.0.js"></script>
	<script src="js/layer/layer.js"></script>
	<script src="assets/messageboard/js/index.js">
		
	</script>
</body>

</html>