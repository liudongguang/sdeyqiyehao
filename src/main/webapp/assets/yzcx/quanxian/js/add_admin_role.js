var basePath = $("#basePath").val();
$(document).ready(function() {
	$("#addUserid").click(function() {
		layerWindow('/yzcx/test', "人员选择", 600, 400, true);
	});
	$("#subFormID").submit(function() {
		var jq_ksname = $("input[type='hidden'][name='ksname']");
		if (jq_ksname.length == 0) {
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
});
function xuanzeSuccess(userid, userName, ksName) {
	var jq_ry = $("#renyuanID");
	jq_ry.empty();
	// alert(userid + " " + userName)
	var str = '<input name="ksname" type="hidden" value="'
			+ ksName
			+ '"/><input name="username" type="hidden" value="'
			+ userName
			+ '"/><input name="userid" type="hidden" value="'
			+ userid
			+ '"/> <div class="formControls"><span class="l mar_right"> <a href="javascript:;"class="btn btn-default radius"> '
			+ userName
			+ '</a></span></div>';
	jq_ry.append(str);
	$("#delImgID").click(function() {
		// $(this).parent().parent().parent().remove();
		//<img class="focus"	id="delImgID"	style="width: 15px; margin-left: 5px; vertical-align: middle"	src="assets/yzcx/quanxian/images/x.png" />
	})
}
