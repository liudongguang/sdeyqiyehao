$(document).ready(function() {

	/* --------数据表格隔行变色开始 --------*/
	$(".datatable tr:even").addClass("graybg01");
	$(".datatable tr:odd").addClass("whitebg");
	$(".datatable tr").mouseover(function() {
		$(this).addClass("graybg02")
	})
	$(".datatable tr").mouseout(function() {
		//var bgc = $(this).attr("bg");
		$(this).removeClass("graybg02");
	})

	/* --------详情页面td对齐方式 --------*/
	$(".detailTable tbody tr").each(function() {
		$(this).children("td").eq(0).css({
			"text-align": "right",
			"padding-right": "20px"
		})
		$(this).children("td").eq(1).css({
			"text-align": "left"
		})
	})

});