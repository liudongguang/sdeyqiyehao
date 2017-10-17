var loadIndex;
var openLayerWindowID;
$(document).on('pjax:beforeSend', function() {
	NProgress.start();
})
$(document).on('pjax:send', function() {
	NProgress.set(0.3);
	loadIndex = layer.load(0, {
		shade : [ 0.8, '#fff' ]
	});
})
$(document).on('pjax:complete', function(xhr, textStatus) {
	NProgress.set(0.6);
	layer.close(loadIndex);
})
$(document).on('pjax:end', function() {
	NProgress.done();
})
$(document).on('pjax:error', function(xhr, textStatus, errorThrown) {
	NProgress.done();
	if (textStatus.status == 404) {
		layer.alert("无此页面！");
	} else if (textStatus.status == 0) {
		layer.alert("服务器已关闭！");
	} else {
		layer.alert("服务器错误！ 错误码：" + textStatus.status);
	}
})
// post方式这里不生效
$(document).on('pjax:timeout', function(event) {
	// Prevent default timeout redirection behavior
	NProgress.done();
	layer.alert("请求超时！");
	event.preventDefault();
})
// ///////////////////////////////////////////////////
function pajaxRequest(url, continerID, pushstate, pushdata) {
	var push = true;
	if (!pushstate) {
		push = pushstate;
	}
	var data = {};
	if (pushdata) {
		data = pushdata;
	}
	// post不用设置超时时间
	$.pjax({
		url : url,
		container : continerID,
		type : "post",
		push : push,
		data : data
	})
}
// //////
function ajaxRequest(url, requestData, handlerFunction, datatype) {
	var dataType = "json";
	if (datatype) {
		dataType = datatype;
	}
	$.ajax({
		url : url,
		data : requestData,
		type : "post",
		dataType : dataType,
		success : handlerFunction,
		beforeSend : function() {
			loadIndex = layer.load(0, {
				shade : [ 0.8, '#fff' ]
			});
		},
		complete : function() {
			layer.close(loadIndex);
		},
		error : function(xhr, textStatus, errorThrown) {
			if ("timeout" == textStatus) {
				layer.alert("请求超时错误！");
			} else if ("notmodified" == textStatus) {
				layer.alert("未定义错误！");
			} else if ("parsererror" == textStatus) {
				layer.alert("解析错误！");
			} else if ("error" == textStatus) {
				layer.alert("服务器错误！");
			} else {
				layer.alert("不知道什么错反正出错了");
			}
		}
	})
}
// ////
function initPajaxRequestForClick(continerID) {
	var $click = $("[pajax-data]");
	$click.click(function() {
		var href = $(this).attr("href");
		var del = $(this).attr("del");
		if (href) {
			if (del) {
				layer.confirm('请谨慎操作！', {
					icon : 3,
					title : '删除条目'
				}, function(index) {
					// do something
					pajaxRequest(href, continerID);
					layer.close(index);
				});
			} else {
				pajaxRequest(href, continerID);
			}
		} else {
			layer.alert("缺少请求地址！");
		}
	});
}
function initButtonRequestForClick() {
	var $click = $("[button-data]");
	$click.click(function() {
		var href = $(this).attr("href");
		var del = $(this).attr("del");
		if (href) {
			if (del) {
				layer.confirm('请谨慎操作！', {
					icon : 3,
					title : '删除条目'
				}, function(index) {
					// do something
					location.href = href;
					layer.close(index);
				});
			} else {
				location.href = href;
			}
		} else {
			layer.alert("缺少请求地址！");
		}
	});
}
// //
function initPajaxFormRequestForClick(continerID) {
	var $form = $("[pajax-form]");
	// $.pjax.submit(event, '#pjax-container')
	$form.submit(function(event) {
		var action = $(this).attr("action");
		if (action) {
			$.pjax.submit(event, continerID)
		} else {
			layer.alert("表单没有提交路径！");
		}
		return false;
	})
}
// /////////
function ajaxFormInitial($form, successFun) {
	$form.on('submit', function(e) {
		e.preventDefault(); // prevent native submit
		$(this).ajaxSubmit({
			beforeSubmit : function() {
				loadIndex = layer.load(0, {
					shade : [ 0.8, '#fff' ]
				});
			},
			success : function(data, status) {
				layer.close(loadIndex);
				layer.close(openLayerWindowID);
				successFun(data, status);
			},
			error : function() {
				layer.close(loadIndex);
				layer.alert("请求出错！")
			}
		})
	});
}
//
function openframe() {
	var $click = $("[button-frame]");	
	$click.click(function() {
		var href = $(this).attr("href");
		var x = '360px';
		var y = '500px';
		if (px) {
			x = px;
		}
		if (py) {
			y = py;
		}
		layer.open({
			type : 2,
			area : [ x, y ],
			skin : 'layui-layer-rim', // 加上边框
			content : [ href ]
		});
	})

}