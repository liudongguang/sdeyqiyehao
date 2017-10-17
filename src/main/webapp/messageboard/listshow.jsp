<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>留言评论</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="assets/messageboard/css/amazeui.min.css" />
<link rel="stylesheet" type="text/css"
	href="assets/messageboard/css/commes.css" />
<link rel="stylesheet" href="assets/messageboard/css/photoswipe.css">
<link rel="stylesheet"
	href="assets/messageboard/css/default-skin/default-skin.css">
<link rel="stylesheet" href="assets/messageboard/css/style.css">
<link rel="stylesheet" href="assets/messageboard/css/list.css">
</head>
<body style="max-height: 0">
	<input value="${pageContext.request.contextPath }/" type="hidden"
		id="basePath" />
	<div id="postli2">
		<div class="container div_bot"
			style="padding-bottom: 10px; margin-bottom: 15px;">
			<div class="right_con1">
				<div class="demo" style="text-align: left">
					<div class="use">
						<div class="usename am-list-item-hd">
							<p class="fx_content2 list_p3">${object.title}</p>
							<br />

							<p class="fx_content1 list_p2" style="float: left">${object.wxqyusername}</p>
							<span class="list_span4"><fmt:formatDate
									value="${object.createtime}" pattern=" yyyy-MM-dd HH:mm:ss" /></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="background-color: white; padding: 0 15px;"
			id="lybcontentID">
			<input id="lybimagesID" type="hidden" value="${object.messageimages}" />
			<p style="text-indent: 2em; font-size: 14px">
				<span id="lybctID">${object.content}</span>
			</p>
		</div>
		<div id="post_ul">
			<div class="am-u-sm-12 all_say">
				<span>全部回复</span> <span class="list_span1" style="margin-right: 5px"><img
					src="assets/messageboard/images/say.png"
					style="width: 13px; vertical-align: middle" alt="" /><span>&nbsp;</span><span
					id="plsID">0</span></span>
			</div>
			<div id="lybhuifuID"></div>
			<div class="am-g send_fixed am-u-sm-12"
				style="position: fixed; bottom: 0; background-color: white; padding: 10px 0">
				<div class="am-u-lg-6">
					<div class="am-input-group">
						<form id="submitForm" action="messageboard/addLiuYanHuiFu"
							method="post">
							<input type="hidden" id="messageID" name="messageid"
								value="${object.id}" placeholder="留言id缺失" /><input
								placeholder="留言用户id缺失" type="hidden"
								value="${sessionScope.session_wxUSER.userid}" id="wxUserID"
								name="wxqyuserid" /> <input type="hidden"
								placeholder="留言用户姓名缺失"
								value="${sessionScope.session_wxUSER.name}" id="wxUserNameID"
								name="wxqyusername" /> <input type="hidden"
								placeholder="留言用户头像缺失"
								value="${sessionScope.session_wxUSER.avatar}" id="wxTouxiangID"
								name="wxheadimg" /> <input style="border-radius: 5px"
								placeholder="请输入评论内容！(50字以内)" type="text" name="hfnr"
								maxlength="50" class="am-form-field send_ipt" />
						</form>
						<span class="am-input-group-btn">
							<button class="am-btn send_btn send_ipt" id="submitBTID"
								style="color: white; border-radius: 5px; margin-left: 10px; background-color: #0087f9 !important; border: solid 1px #0087f9 !important;"
								type="button">发表</button>
						</span>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div
		style="width: 100%; height: 60px; clear: left !important; background-color: transparent"></div>


	<!--以下内容不要管-->
	<div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="pswp__bg"></div>
		<div class="pswp__scroll-wrap">
			<div class="pswp__container">
				<div class="pswp__item"></div>
				<div class="pswp__item"></div>
				<div class="pswp__item"></div>
			</div>
			<div class="pswp__ui pswp__ui--hidden">
				<div class="pswp__top-bar">
					<div class="pswp__counter"></div>
					<button class="pswp__button pswp__button--close"
						title="Close (Esc)"></button>
					<div class="pswp__preloader">
						<div class="pswp__preloader__icn">
							<div class="pswp__preloader__cut">
								<div class="pswp__preloader__donut"></div>
							</div>
						</div>
					</div>
				</div>
				<div
					class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
					<div class="pswp__share-tooltip"></div>
				</div>
				<button class="pswp__button pswp__button--arrow--left"
					title="Previous (arrow left)"></button>
				<button class="pswp__button pswp__button--arrow--right"
					title="Next (arrow right)"></button>
				<div class="pswp__caption">
					<div class="pswp__caption__center"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="assets/messageboard/js/jquery.min.js"></script>
	<script src="js/layer/layer.js"></script>
	<script src="assets/js/ajaxForm.js"></script>
	<script src="js/common.js"></script>
	<script src="assets/messageboard/js/listshow.js"></script>
</body>
</html>