$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	complete : function(XMLHttpRequest, textStatus) {
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
		if (sessionstatus == "timeout") {
			layer.alert("非法登陆！", {
				skin : 'layui-layer-error', // 样式类名
				title : '提示',
				closeBtn : 0,
				shadeClose : true
			}, function(index) {
				layer.close(index);
				window.location.href="/error.jsp";
			});
		}
	}
});
