var basePath = $("#basePah").val();
$(document)
		.ready(
				function() {
					$("#getAllSimpleDataDepartBT")
							.click(
									function() {
										var ii = layer.load(0, {
											shade : [ 0.8, '#fff' ]
										// 0.1透明度的白色背景
										});
										$
												.post(
														basePath
																+ "/wxassets/getAllSimpleDataDepartForzTreeNodes",
														{},
														function(data) {
															var data = data.data;
															// 定义参数
															var zTreeObj, setting = {
																data : {
																	simpleData : {
																		enable : true,
																		idKey : "id",
																		pIdKey : "parentid",
																		rootPId : 0
																	}
																},
																callback : {
																	onClick : zTreeOnCheck
																}

															};
															// 初始化tree
															zTreeObj = $.fn.zTree
																	.init(
																			$("#departmentTreeForSimpleData"),
																			setting,
																			data);
															// 展开第一个节点 start
															var nodes = zTreeObj
																	.getNodes();
															var firstNode = nodes[0];
															zTreeObj
																	.expandNode(
																			firstNode,
																			true,
																			false,
																			false,
																			false);
															// 展开第一个节点 end
															layer.close(ii);
														}, 'json')
									});
					$("#subBT").click(
							function() {
								var zzcid = null;
								function showResponse(data) {
									layer.close(zzcid);
									data = jQuery.parseJSON(data.data);
									if (data.errcode == 0) {
										layer.alert("发送成功！");
									} else {
										layer.alert("发送失败！ 错误码：" + data.errcode
												+ "    错误信息：" + data.errmsg);
									}
								}
								var options = {
									success : showResponse
								}
								zzcid = layer.load(0, {
									shade : [ 0.8, '#fff' ]
								// 0.1透明度的白色背景
								});
								$("#subForm").ajaxSubmit(options);
								return false;
							});
				});
function zTreeOnCheck(event, treeId, treeNode) {
	var checkedID = treeNode.id;
	var checkedName = treeNode.name;
	if (checkedID) {
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		$.post(basePath + '/wxassets/getEmployeeByDepartMentID', {
			departmentId : checkedID
		}, function(data) {
			var userList = data.data.userlist;
			var jq_checkedInfo = $("#checkedInfo").empty();
			for (var i = 0; i < userList.length; i++) {
				var user = userList[i];
				var userId = user.userid;
				var userName = user.name;
				var jq_radio = $("<input type='radio' />");
				jq_radio.attr({
					value : userId,
					name : "employeeID"
				});
				jq_checkedInfo.append(jq_radio);
				jq_radio.after(userName);
			}
			layer.close(ii);
		}, 'json');
	} else {

	}
};