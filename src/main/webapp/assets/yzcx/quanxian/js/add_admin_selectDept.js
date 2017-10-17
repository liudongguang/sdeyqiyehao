var basePath = $("#basePath").val();
$(document).ready(function() {
	getDepartMent();
});

// 获取员工信息
function getDepartMent(formdata) {
	var ii = layer.load(0, {
		shade : [ 0.8, '#fff' ]
	// 0.1透明度的白色背景
	});
	var requestRUL = basePath + "/wxassets/getAllDepart";
	if (formdata) {
		requestRUL = requestRUL + "?" + formdata
	}
	$.post(requestRUL, {}, function(data) {
		var data = data.data;
		// //////////////////
		var $tree = $('#departmentTree').treeview({
			data : data,
			multiSelect : false,
			onNodeSelected : function(event, node) {
				zTreeOnCheck(event, node.id, node.text);// 初始化数据，按钮
			},
			onNodeUnselected : function(event, node) {

			}
		});
		// //////////////
		layer.close(ii);
	}, 'json')
}
function zTreeOnCheck(event, id, name) {
	var checkedID = id;
	var checkedName = name;
	if (checkedID) {
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		var url = basePath
				+ '/yzcx/getDetailEmployeeByDepartMentID?departmentId='
				+ checkedID + "&ksname=" + checkedName;
		location.href = url;
	}
};