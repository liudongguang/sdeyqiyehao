<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" uri="/WEB-INF/tld/pagerForBootStrap.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="assets/yzcx/quanxian/lib/html5.js"></script>
<script type="text/javascript" src="assets/yzcx/quanxian/lib/respond.min.js"></script>
<script type="text/javascript" src="assets/yzcx/quanxian/lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link href="assets/yzcx/quanxian/css/H-ui.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/yzcx/quanxian/css/H-ui.admin.css" rel="stylesheet"
	type="text/css" />
<link href="assets/yzcx/quanxian/skin/default/skin.css" rel="stylesheet"
	type="text/css" />
<link href="assets/yzcx/quanxian/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap-theme.css" />
<title>角色管理</title>
</head>
<body>
	<nav class="breadcrumb">
		院长查询系统 <span class="c-gray en">&gt;</span> 角色权限 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<div class="cl pd-5 bg-1 bk-gray">
			<span class="l"> <a href="javascript:;"
				onClick="product_del_all(this,'10001')"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a class="btn btn-primary radius"
				href="yzcx/quanxian/add_admin_role.jsp"><i class="Hui-iconfont">&#xe600;</i>
					添加角色</a>
			</span> <span class="r">共有数据：<strong>${page.total}</strong> 条
			</span>
		</div>
		<table class="table table-border table-bordered table-hover table-bg">
			<thead>
				<tr>
					<th scope="col" colspan="6">用户管理</th>
				</tr>
				<tr class="text-c">
					<th width="25"><input type="checkbox" value="" name=""></th>
					<th width="200">姓名</th>
					<th width="300">科室</th>
					<th>权限</th>
					<th width="70">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="obj">
					<tr class="text-c">
						<td><input type="checkbox" value="${obj.id}" name="id"></td>
						<td>${obj.username}</td>
						<td><a href="javascript:;">${obj.ksname}</a></td>
						<td>${obj.qxids}</td>
						<td class="f-14"><a title="编辑" href="javascript:;"
							onclick="admin_role_edit('角色编辑','/yzcx/getQXUserById','${obj.id}')"
							style="text-decoration: none"><i class="Hui-iconfont">&#xe6df;</i></a>
							<a title="删除" href="javascript:;"
							onclick="admin_role_del(this,'${obj.id}')" class="ml-5"
							style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="pageDIV" class="text-center">
		<c:if test="${page!=null}">
			<page:createPager pages="${page.pages}" pageSize="${page.pageSize}"
				url="yzcx/qXUserList" total="${page.total}"
				pageNum="${page.pageNum}"></page:createPager>
		</c:if>
	</div>

	<script type="text/javascript"
		src="assets/yzcx/quanxian/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="assets/yzcx/quanxian/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="assets/yzcx/quanxian/js/H-ui.js"></script>
	<script type="text/javascript"
		src="assets/yzcx/quanxian/js/H-ui.admin.js"></script>
	<script type="text/javascript">
		/*管理员-角色-添加*/
		function admin_role_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		/*管理员-角色-编辑*/
		function admin_role_edit(title, url, id, w, h) {
			//layer_show(title, url, w, h);
			var url = '${pageContext.request.contextPath }' + url + "?id=" + id;
			location.href = url;
		}
		/*管理员-角色-删除*/
		function admin_role_del(obj, id) {
			layer
					.confirm(
							'角色删除须谨慎，确认要删除吗？',
							function(index) {
								//此处请求后台程序，下方是成功后的前台处理……
								$
										.post(
												'${pageContext.request.contextPath }/yzcx/delUserByID',
												{
													id : id
												},
												function(data) {
													location.href = '${pageContext.request.contextPath }/yzcx/qXUserList'
												}, 'json');
							});
		}
		/*图片-全部删除*/
		function product_del_all(obj, id) {
			layer
					.confirm(
							'确认要全部删除吗？',
							function(index) {
								var jq_box = $("input[type='checkbox'][name='id']:checked");
								if (jq_box.length == 0) {
									layer.alert("至少选择一项！");
									return false;
								}
								var ids = "";
								jq_box.each(function() {
									ids += $(this).val() + ",";
								})
								$
										.post(
												'${pageContext.request.contextPath }/yzcx/delUserByIDS',
												{
													ids : ids.substring(0,
															ids.length - 1)
												},
												function(data) {
													location.href = '${pageContext.request.contextPath }/yzcx/qXUserList'
												}, 'json');
							});
		}
	</script>
</body>
</html>