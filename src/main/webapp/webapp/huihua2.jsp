<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建会话</title>

<link rel="stylesheet" href="assets/bootstrap/css/bootnav.css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/bootstrap-treeview/bootstrap-treeview.min.css" />
<link rel="stylesheet" href="assets/css/huihua.css" />
</head>
<body>
	<div class="container">
		<input type="hidden" value="${pageContext.request.contextPath }"
			id="basePah" />

		<form class="form-inline" role="form" action="" id="subForm"
			style="margin-top: 10px;height:20px">
			<div class="form-group col-xs-12">
				<input type="text" style="width: 100%" class="form-control"
					id="name" name="searchKSName" value="${param.searchKSName}"
					placeholder="搜索科室..">
			</div>
		</form>
		<div id="searchRSDIVID"  style="position: absolute;z-index:10;width: 330px;display: none;">
			<ul class="list-group" id="searchRSKS">
			</ul>
		</div>
		<!-- Start Navigation -->
		<div class="row">
			<div class="col-lg-3 col-sm-4">
				<nav
					class="navbar navbar-default  navbar-transparent white bootsnav on"
					style="position: absolute; opacity: 0.8; width: 92%">
					<div class="navbar-header">
						<button id="daohangBTID" type="button" class="navbar-toggle"
							data-toggle="collapse" data-target="#navbar-menu">
							<span class="sr-only">切换导航</span> <span class="icon-bar"></span>
							<span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
					</div>
					<div class="collapse navbar-collapse" id="navbar-menu">
						<ul class="nav navbar-nav nav_new" data-in="fadeInDown"
							data-out="fadeOutUp" style="opacity: 0.8">
							<li><div id="departmentTree"></div></li>
						</ul>
					</div>



				</nav>


			</div>

			<div class="col-lg-9 col-sm-8">
				<div class="btn_group2">
					<button class="btn btn-info ldghide" id="checkedAll">全选</button>
					<button class="btn btn-info ldghide" id="fanxuanCheckedAll">反选</button>
					<button class="btn btn-success ldghide f_right" id="subBT">创建会话</button>
				</div>
				<div class="clearfix"></div>
				<div style="max-height: 830px; overflow-y: auto;">

					<form id="subForm" action="wxassets/SendMsgToEmployee"
						method="post">
						<div id="checkedInfo"></div>
					</form>

				</div>
			</div>
		</div>

	</div>

</body>
<script type="text/javascript" src="js/jquery3.1.0.min.js"></script>
<script type="text/javascript" src="js/jweixin-1.1.0.js"></script>
<script type="text/javascript" src="assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="assets/bootstrap/js/bootsnav.js"></script>
<script type="text/javascript"
	src="assets/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript" src="assets/js/ajaxForm.js"></script>
<script type="text/javascript" src="assets/js/huihua2.js"></script>
</html>