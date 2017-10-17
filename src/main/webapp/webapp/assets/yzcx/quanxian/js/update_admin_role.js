var basePath = $("#basePath").val();
$(document)
		.ready(
				function() {

					$("#subFormID")
							.submit(
									function() {
										var userid = $(
												"input[type='hidden'][name='userid']")
												.val();
										if (!userid) {
											layer.alert("至少选择一个人！");
											return false;
										}
										var jq_box = $("input[type='checkbox'][name='qxids']:checked");
										if (jq_box.length == 0) {
											layer.alert("至少选择一项权限！");
											return false;
										}
										return true;
									});
					var qxidsVal = $("#qxids").val().split(",");
					for (i = 0; i < qxidsVal.length; i++) {
						var qxval = qxidsVal[i];
						$(
								"input[type='checkbox'][name='qxids'][value='"
										+ qxval + "']").prop("checked",
								"checked");
					}
				});
