$(document).ready(function(e) {
	// 获取更多话题列表
	$("#moreLiuYan").click(function() {
		var jq_more = $(this);
		var pageNum = jq_more.attr("pageNum");
		var jq_moreDIV = $("#morelistID");
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		var nextPage = parseInt(pageNum) + 1;
		var formdata = $("#subForm").serialize();
		var ajaxurl = basePath + 'messageboard/liuYanListForMore';
		if (formdata) {
			ajaxurl=ajaxurl + "?" + formdata;
		}
		$("#morenumID").remove();
		$.post(ajaxurl, {
			pageNum : nextPage
		}, function(data) {
			jq_more.attr("pageNum", nextPage);// 赋值
			jq_moreDIV.append(data);
			isdisplayMore();
			layer.close(ii);
		});
	});
});
// 显示更多文字
function isdisplayMore() {
	var morepages = $("#morepages").val(); // 总页数
	var morepageNum = $("#morepageNum").val(); // 当前页数
	var jq_noMore = $("#noMore");
	var jq_yesMore = $("#moreLiuYan");
	if (morepages == morepageNum) { // 没有更多
		jq_noMore.removeClass().addClass('show');
		jq_yesMore.removeClass().addClass('hide');
	} else {
		jq_noMore.removeClass().addClass('hide');
		jq_yesMore.removeClass().addClass('show');
	}
}
