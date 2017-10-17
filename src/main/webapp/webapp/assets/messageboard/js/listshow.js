$(document).ready(function(e) {
	// ///内容表情替换
	var content = $("#lybctID").text();
	$("#lybctID").empty().append(replace_em(content));
	// ///
	var imgs = $("#lybimagesID").val();// 图片地址，多个图片逗号分割
	if (imgs) {
		var jq_content = $("#lybcontentID");
		var imageArr = imgs.split(",");
		for (var i = 0; i < imageArr.length; i++) {
			var imgPath = imageArr[i];
			var imagehtml = '<img src="{0}" style="width: 100%" alt="" />';
			imagehtml = imagehtml.replace('{0}', imgPath);
			jq_content.append(imagehtml);
		}
	}
	// /////////////获取评论
	getPingLun();
	// //////////////
	//
	function huidiao(data) {
		$("input[name='hfnr']").val('');
		getPingLun();// 刷新评论
	}
	initAjaxForm(huidiao);
	// /
});
// 替换表情
function replace_em(str) {
	str = str
			.replace(
					/\[em_([0-9]*)\]/g,
					'<img src="assets/messageboard/face/$1.gif" border="0" style="width:18px;height:18px;"/>');
	return str;
}
// 获取评论最新5条
function getPingLun() {
	var pii = layer.load(0, {
		shade : [ 0.8, '#fff' ]
	// 0.1透明度的白色背景
	});
	var msgid = $("#messageID").val();
	$.post(basePath + '/messageboard/getLiuYanHuiFuList', {
		messageid : msgid
	}, function(data) {
		$("#lybhuifuID").empty().append(data);
		var plcount = $("#pltotalid").val();
		$("#plsID").text(plcount);
		layer.close(pii);
	});

}
