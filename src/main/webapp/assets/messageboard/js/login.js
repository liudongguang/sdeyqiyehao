$(document).ready(function(e) {
	var ii = layer.load(0, {
		shade : [ 0.8, '#fff' ]
	// 0.1透明度的白色背景
	});
	var codeIDVal = $("#codeID").val();
	var basePath = $("#basePath").val();
	if (codeIDVal) {
		$.post(basePath + '/wxsq/getUserInfo', {
			'code' : codeIDVal
		}, function(data) {
			if (data.errorCode == 0) {
				layer.close(ii);
				location.href=basePath+'/messageboard/liuYanList';
			}else{
				layer.alert("授权失败");
				layer.close(ii);
			}
		});
	} else {
		layer.msg("无授权code");
		layer.close(ii);
	}
});
