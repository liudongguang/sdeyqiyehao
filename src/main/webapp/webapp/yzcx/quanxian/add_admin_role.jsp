<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>角色管理</title>
</head>
<body>
	<input id="basePath" type="hidden"
		value="${pageContext.request.contextPath }/" />
	<nav class="breadcrumb">
		院长查询系统 <span class="c-gray en">&gt;</span> 角色权限<span class="c-gray en">&gt;</span>添加
		<a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20" style="padding-top: 20px;">
		<p>
			<i class="Hui-iconfont"></i>添加用户
		</p>
	</div>
	<div class="pd-20">
		<form id="subFormID" action="yzcx/saveQXUser" method="post" class="form form-horizontal"
			id="form-user-character-add">
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>角色名称：</label>
				<div class="formControls col-1">
					<span class="l"> <a href="javascript:;" id="addUserid"
						class="choose btn btn-primary radius"> 请选择</a></span>
				</div>

				<div id="renyuanID"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>系统权限：</label>
				<div class="formControls col-10">
					<dl class="permission-list mar_bot">
						<dt>
							<label> <input type="checkbox" value="1"
								name="qxids" id="qxids"> 门诊信息
							</label>
						</dt>
					</dl>
					<dl class="permission-list mar_bot">
						<dt>
							<label> <input type="checkbox" value="2"
								name="qxids" id="user-Character-0-1"> 医院总收入
							</label>
						</dt>
					</dl>
					<dl class="permission-list mar_bot">
						<dt>
							<label> <input type="checkbox" value="3"
								name="qxids" id="user-Character-0-2"> 医技信息
							</label>
						</dt>
					</dl>
					<dl class="permission-list mar_bot">
						<dt>
							<label> <input type="checkbox" value="4"
								name="qxids" id="user-Character-0-3"> 医生出诊
							</label>
						</dt>
					</dl>
					<dl class="permission-list mar_bot">
						<dt>
							<label> <input type="checkbox" value="5"
								name="qxids" id="user-Character-0-4"> 手术安排
							</label>
						</dt>
					</dl>
					<dl class="permission-list mar_bot">
						<dt>
							<label> <input type="checkbox" value="6"
								name="qxids" id="user-Character-0-5"> 住院信息
							</label>
						</dt>
					</dl>
					<dl class="permission-list mar_bot">
						<dt>
							<label> <input type="checkbox" value="7"
								name="qxids" id="user-Character-0-6"> 护理单元
							</label>
						</dt>
					</dl>
					<dl class="permission-list mar_bot">
						<dt>
							<label> <input type="checkbox" value="8"
								name="qxids" id="user-Character-0-7"> 会诊信息
							</label>
						</dt>
					</dl>
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button type="submit" class="btn btn-success radius"
						id="admin-role-save" name="admin-role-save">
						<i class="icon-ok"></i> 保存
					</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript"
		src="assets/yzcx/quanxian/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="assets/yzcx/quanxian/js/H-ui.js"></script>
	<script type="text/javascript"
		src="assets/yzcx/quanxian/js/H-ui.admin.js"></script>
	<script>
		/*管理员-角色-添加*/
		function admin_role_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		$(function() {
			$(".permission-list dt input:checkbox").click(
					function() {
						$(this).closest("dl").find("dd input:checkbox").prop(
								"checked", $(this).prop("checked"));
					});
			$(".permission-list2 dd input:checkbox")
					.click(
							function() {
								var l = $(this).parent().parent().find(
										"input:checked").length;
								var l2 = $(this).parents(".permission-list")
										.find(".permission-list2 dd").find(
												"input:checked").length;
								if ($(this).prop("checked")) {
									$(this).closest("dl").find(
											"dt input:checkbox").prop(
											"checked", true);
									$(this).parents(".permission-list").find(
											"dt").first()
											.find("input:checkbox").prop(
													"checked", true);
								} else {
									if (l == 0) {
										$(this).closest("dl").find(
												"dt input:checkbox").prop(
												"checked", false);
									}
									if (l2 == 0) {
										$(this).parents(".permission-list")
												.find("dt").first().find(
														"input:checkbox").prop(
														"checked", false);
									}
								}

							});
		});
	</script>
	<script>
		$(function() {
			$(".focus").each(function(i) {
				$(this).bind("click", function() {
					$(".mar_right:eq(" + i + ")").hide();
				});
			});
			$(".choose").bind("click", function() {
				if ($(".dn").css("display") == "none") {
					$(".dn").show();
				} else {
					$(".dn").hide();
				}
			});

		})
	</script>
	<script type="text/javascript" src="js/layer/layer.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript"
		src="assets/yzcx/quanxian/js/add_admin_role.js"></script>
</body>
</html>