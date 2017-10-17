$(document)
		.ready(
				function() {
					var basePath = $("#basePah").val();
					$("#getAllDepartBT")
							.click(
									function() {
										var ii = layer.load(0, {
											shade : [ 0.8, '#fff' ]
										// 0.1透明度的白色背景
										});
										$
												.post(
														basePath
																+ "/wxassets/getAllDepartForzTreeNodes",
														{},
														function(data) {
															var data = data.data;
															// 定义参数
															var zTreeObj, setting = {
																view : {
																	selectedMulti : false
																}
															}, zTreeNodes = data;
															// 初始化tree
															zTreeObj = $.fn.zTree
																	.init(
																			$("#departmentTree"),
																			setting,
																			zTreeNodes);
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
															console.log(data)
															// 定义参数
															var zTreeObj, setting = {
																data : {
																	simpleData : {
																		enable : true,
																		idKey : "id",
																		pIdKey : "parentid",
																		rootPId : 0
																	}
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