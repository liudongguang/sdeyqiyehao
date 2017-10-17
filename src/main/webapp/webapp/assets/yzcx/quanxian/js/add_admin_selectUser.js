var basePath = $("#basePath").val();
$(document).ready(function() {
	$("#qdBTID").click(function() {
		var jq_redio = $("input[type='radio']:checked");
		if (jq_redio.length == 0) {
			layer.alert("至少选择一个人！");
			return false;
		}
		var userid = jq_redio.val();
		var userName = jq_redio.next().text();
		var ksName=$("#ksNameId").text();
		parent.xuanzeSuccess(userid, userName,ksName);
		parent.closeCurrentWindow();
	});
});
