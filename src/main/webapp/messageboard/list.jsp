<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" uri="/WEB-INF/tld/pagerForBootStrap.tld"%>
<!DOCTYPE>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>留言列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet" href="assets/messageboard/css/amazeui.min.css" />
<link rel="stylesheet" type="text/css"
	href="assets/messageboard/css/commes.css" />
<link rel="stylesheet" href="assets/messageboard/css/photoswipe.css">
<link rel="stylesheet"
	href="assets/messageboard/css/default-skin/default-skin.css">
<link rel="stylesheet" href="assets/messageboard/css/style.css">
<link rel="stylesheet" href="assets/messageboard/css/list.css">
</head>
<body style="background-color: #f2f2f2 !important;">
	<input value="${pageContext.request.contextPath }/" type="hidden"
		id="basePath" />
	<div id="post__li">
		<!--<div style="width: 100%">-->
		<!--<div class="am-form-group am-form-icon">-->
		<!--<input class="am-form-field sy_ipt_search" type="text" placeholder="搜索医院，医生，科室，疾病">-->
		<!--</div>-->
		<!--</div>-->
		<img id="search" style="width: 100%"
			src="assets/messageboard/images/so.jpg" alt="" />
		<c:forEach items="${page.list}" var="l">
			<div class="container div_bot">
				<!--用户头像-->
				<div class="header">
					<div>
						<a href="messageboard/getLiuYanByID?id=${l.id}"><img
							class="head_img" src="${l.wxheadimg}"></a>
					</div>
				</div>
				<div class="right_con">
					<a href="messageboard/getLiuYanByID?id=${l.id}">
						<div class="demo" style="text-align: left">
							<div class="use">
								<div class="usename am-list-item-hd">
									<p class="fx_content list_p1">${l.title}</p>
									<br />
									<p class="fx_content1 list_p2">${l.wxqyusername}</p>
									<span class="list_span1"><img
										src="assets/messageboard/images/say.png"
										style="width: 13px; vertical-align: middle" alt="" /><span>&nbsp;</span>155</span>
									<span class="list_span2"><fmt:formatDate
											value="${l.createtime}" pattern=" yyyy-MM-dd HH:mm:ss" /></span>
								</div>
							</div>
						</div>
					</a>
				</div>
			</div>
		</c:forEach>

		<div id="pageDIV" class="text-center">
			<c:if test="${page!=null}">
				<page:createPager pages="${page.pages}" pageSize="${page.pageSize}"
					url="messageboard/liuYanList" total="${page.total}"
					pageNum="${page.pageNum}"></page:createPager>
			</c:if>
		</div>
	</div>
	<div id="show" class="am-g send_fixed am-u-sm-12"
		style="position: fixed; top: 0; background-color: white; padding: 6px 0; display: none;">
		<div class="am-u-lg-6">
			<div class="am-input-group">
				<input style="border-radius: 5px" type="text"
					class="am-form-field send_ipt" placeholder="搜主题（支持模糊查询）"> <span
					class="am-input-group-btn">
					<button class="am-btn send_btn send_ipt" zhezhao
						style="color: white; border-radius: 5px; margin-left: 10px; background-color: #0087f9 !important; border: solid 1px #0087f9 !important;"
						type="button">搜索</button>
				</span>
			</div>
		</div>
	</div>
	<div class="am-margin-top-sm">
		<a href="wxsq/jumpPageForOAuth?page=messageboard/index.jsp"><input
			type="submit" name="" value="发表新话题"
			class="btn_color am-btn am-btn-primary am-btn-block"></a>
	</div>
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
	<script src="assets/messageboard/js/list.js"></script>
	<script src="js/layer/layer.js"></script>
	<script src="js/common.js"></script>
	<script src="assets/messageboard/js/list.js"></script>
	<script src="assets/js/pagersuper.js"></script>
	<script>
		function $$(id) {
			return document.getElementById(id);
		}
		$$("search").onclick = function() {
			$$("show").style.display = "block";
		}
	</script>
</body>
</html>