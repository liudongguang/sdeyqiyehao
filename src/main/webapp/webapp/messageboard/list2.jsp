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
<link rel="stylesheet" href="assets/css/iscroll.css" />
</head>
<body>
	<input value="${pageContext.request.contextPath }/" type="hidden"
		id="basePath" />
	<form class="form-inline search_h" role="form" action="" id="subForm">
		<div class="form-group col-xs-9">
			<input type="text" style="width: 100%" class="form-control" id="name"
				name="searchTitle" value="${param.searchTitle}"
				placeholder="模糊搜索标题..">
		</div>
		<button type="submit" class="btn btn-primary">搜索</button>
	</form>
	<div id="post__li" style="margin-top: 20px;">
		<div id="wrapper" style="max-height: 400px;overflow:hidden;">
			<div id="scroller">
				<div id="pullDown" style="display: none;text-align: center;">
					<span>下拉刷新…</span>
				</div>
				<ul pageNum=${page.pageNum}>
					<c:forEach items="${page.list}" var="l">
						<li>
						   
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
														style="width: 13px; vertical-align: middle" alt="" /><span>&nbsp;</span>${l.pinglunCount}</span>
													<span class="list_span2"><fmt:formatDate
															value="${l.createtime}" pattern=" yyyy-MM-dd HH:mm:ss" /></span>
												</div>
											</div>
										</div>
									</a>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
				<div id="pullUp" style="display: none;text-align: center;">
					<span>上拉加载更多…</span>
				</div>
			</div>
		</div>
	</div>
	<!--
	<div
		style="width: 100%; height: 40px; clear: left; background-color: white !important;">
		 page.pages!=page.pageNum 没到最后一页，隐藏这句话 
		<p
			class="<c:if test="${page.pages==page.pageNum||page.pages==0}">show</c:if><c:if test="${page.pages!=0&&page.pages!=page.pageNum}">hide</c:if>"
			id="noMore"
			style="line-height: 40px; text-align: center; font-size: 14px; color: #0087f9">没有更多话题</p>

		 page.pages!=page.pageNum 没到最后一页，显示更多话题查看 
		<p id="moreLiuYan" pageNum="${page.pageNum}"
			class="<c:if test="${page.pages==page.pageNum||page.pages==0}">hide</c:if><c:if test="${page.pages!=0&&page.pages!=page.pageNum}">show</c:if>"
			style="line-height: 40px; text-align: center; font-size: 14px; color: #0087f9; cursor: pointer;">查看更多话题</p>
	</div>
-->

	<div class="am-margin-top-sm"
		style="bottom: 0px; position: fixed; width: 100%">
		<a href="messageboard/index.jsp"><input type="submit" name=""
			style="height: 38px" value="发表新话题"
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
	<script src="js/layer/layer.js"></script>
	<script src="js/common.js"></script>
	<script src="assets/messageboard/js/list2.js"></script>
	<script type="text/javascript" src="assets/js/iscroll-probe.js"></script>
	<script src="assets/messageboard/js/iscrollList2.js"></script>
</body>
</html>