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
																check : {
																	enable : true
																},
																callback : {
																	onCheck : zTreeOnCheck
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
				});
function zTreeOnCheck(event, treeId, treeNode) {
	// console.log(treeNode.id + ", " + treeNode.name + "," + treeNode.checked);
	var checked = treeNode.checked;
	var checkedID = treeNode.id;
	var checkedName = treeNode.name;
	// 选中的情况下
	if (checked) {
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		$.post(basePath + '/wxassets/getEmployeeByDepartMentID', {
			departmentId : checkedID
		}, function(data) {
			var userList=data.data.userlist;
			var jq_checkedInfo=$("#checkedInfo").empty();
			for(var i=0;i<userList.length;i++){
				var user=userList[i];
				var userId=user.userid;
				var userName=user.name;
				var jq_span=$("<span></span>");
				jq_span.text("["+userId+":"+userName+"] ");
				jq_checkedInfo.append(jq_span);
			}
			layer.close(ii);
		}, 'json');
	} else {

	}
};