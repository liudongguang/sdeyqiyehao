$(document).ready(function() {
	var basePath = $("#basePah").val();
	$("#getAllDepartBT").click(function() {
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		$.post(basePath + "/wxassets/getAllDepart", {}, function(data) {
			var data = data.data;
			var $searchableTree = $('#treeview1').treeview({
				data : JSON.stringify(data)
			});
			console.log($searchableTree)
			layer.close(ii);
			// layer.open();
		}, 'json')
	});
});