$(document).ready(function() {

	/* --------数据表格首行设置 --------*/

		$('table.listTable tr:first-child td').css({
			"color":"#fff"
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