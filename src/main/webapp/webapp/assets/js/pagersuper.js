$(document).ready(
		function() {
			// 分页加载，遮罩层
			$("ul[class='pagination']").find("a[href!='javascript:;']").click(
					function() {
						var ii = layer.load(0, {
							shade : [ 0.8, '#fff' ]
						// 0.1透明度的白色背景
						});
					});
			$("button[zhezhao]").click(function() {
				var ii = layer.load(0, {
					shade : [ 0.8, '#fff' ]
				// 0.1透明度的白色背景
				});
			})
		});