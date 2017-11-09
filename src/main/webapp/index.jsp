<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
	<base href="${pageContext.request.contextPath }/"/>
	<title>微信企业号管理平台</title>
	<link rel="stylesheet" href="assets/bootstrap4.0.0-beta/css/bootstrap.css"/>
	<link rel="stylesheet" href="assets/menu/css/menu.css"/>
	<link rel="stylesheet" href="assets/menu/css/public.css"/>
	<link rel="stylesheet" href="assets/nprogress-0.2.0/nprogress.css"/>
	<link rel="stylesheet" href="assets/jsTree/themes/default/style.css"/>
	<link rel="stylesheet" href="assets/css/index_fix.css"/>
</head>
<body>
<input type="hidden" value="${param.isRedirect}" id="isRedirect"/>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="#"></a>
	<button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Settings</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Profile</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Help</a>
			</li>
		</ul>
		<form class="form-inline mt-2 mt-md-0">
			<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
</nav>
<div class="container-fluid">
	<div class="row" style="margin-top: 56px;">
		<div class="col-md-2">
			<div class="middle">
				<!-- 隐藏菜单
                <div class="hidLeftMenu">
                    <img class="openMenu" src="assets/menu/img/open.png"/>
                    <p class="hidTitle">主菜单</p>
                </div>
                -->
				<!-- 隐藏菜单结束 -->
				<!-- 菜单-开始 -->

				<div class="leftMenu">
					<!--
                  <div class="topMenu">
                      <img class="banshi" src="assets/menu/img/banshidating.png"/>
                      <p class="menuTitle">主菜单</p>
                      <img class="changeMenu" src="assets/menu/img/shouqicaidan.png" id="hidIcon"/>
                  </div>
                  -->
					<div class="menu_list">
						<ul>
							<li class="lis">
								<p id="defaultDisplay" class="fuMenu">权限管理</p>
								<img class="xiala" src="assets/menu/img/xiala.png"/>
								<div class="div1">
									<p class="zcd" id="zcd1" href="permission_shiro/getPermissionPageInfo">权限管理</p>
									<p class="zcd" id="zcd2" href="permission_shiro/getRolePageInfo">角色管理</p>
									<p class="zcd" id="zcd3" href="permission_shiro/getUserPageInfo">用户管理</p>
									<p class="zcd" id="zcd4" href="shirotest/testQX">权限测试</p>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<!-- 菜单-结束 -->
			</div>
		</div>
		<div class="col-md-10" id="pajaxMainContainer" style="padding: 20px">
		</div>
	</div>
</div>
<script language="javascript" type="text/javascript" src="assets/js/jquery3.2.1.js"></script>
<script language="javascript" type="text/javascript" src="assets/menu/js/menu.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/popper.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script language="javascript" type="text/javascript" src="assets/jsTree/jstree.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/page/jPage.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jsp/general/weixin/index.js"></script>
</body>
</html>
