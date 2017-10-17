var basePath = $("#basePah").val();
$(document).ready(function() {
	var shouquanRUL = location.href;
	var chat_id = "";
	initTicket(shouquanRUL, basePath);
	var jq_searchBT=$("#searchBT");
	var jq_searchInput = $("#name");
	$("#name").keyup(function() {
		var searchVal = $("#name").val();
		var jq_searchRSDIVID = $("#searchRSDIVID");
		var jq_navbar_menu = $("#navbar-menu");
		if (searchVal) {
			var jq_searchRSKS = $("#searchRSKS");
			jq_searchRSKS.empty();
			$.post(basePath + "/wxassets/getAllDepartFromInternal", {
				searchKSName : searchVal
			}, function(data) {
				var list = data.data;
				if (list.length) {
					for (var i = 0; i < list.length; i++) {
						var li = '<li class="list-group-item">{0}</li>';
						var obj = list[i];
						var id = obj.id;
						var name = obj.name;
						li = li.replace('{0}', name);
						var jq_li = $(li).attr("id", id);
						jq_li.click(function() {
							var id = $(this).attr("id");
							var name = $(this).text();
							jq_searchRSDIVID.hide();
							zTreeOnCheck(null, id, name);
							jq_navbar_menu.removeClass("in");
							jq_searchInput.val(name);
						});
						jq_searchRSKS.append(jq_li);
					}
					jq_searchRSDIVID.show();
					jq_navbar_menu.removeClass("in");
				} else {
					jq_searchRSDIVID.hide();
					jq_navbar_menu.addClass("in");
				}
			}, 'json');
		} else {
			jq_searchRSDIVID.hide();
			jq_navbar_menu.addClass("in");
		}
	});

});
function initTicket(shouquanRUL, basePath) {
	var ii = layer.load(0, {
		shade : [ 0.8, '#fff' ]
	// 0.1透明度的白色背景
	});
	$.ajax({
		url : basePath + '/jssdk/getJsapiTicket',// 跳转到
		data : {
			url : shouquanRUL
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			var appId = data.appId;
			var nonceStr = data.nonceStr;
			var signature = data.signature;
			var timestamp = data.timestamp;
			var url = data.url;
			wx.config({
				debug : false,
				appId : appId,
				timestamp : timestamp,
				nonceStr : nonceStr,
				signature : signature,
				jsApiList : [ 'openEnterpriseChat' ]
			});
			wx.checkJsApi({
				jsApiList : [ 'openEnterpriseChat' ],
				success : function(res) {
				}
			});
			wx.ready(function() {
				initPage();
				layer.close(ii);// 关闭遮罩层
			});
			wx.error(function(res) {
				layer.close(ii);// 关闭遮罩层
				alert("wx fail")

			});
		},
		error : function(httpxml, errinfo) {
			layer.close(ii);// 关闭遮罩层
			alert("异常！" + errinfo);
		}
	});
}
function initPage() {
	getDepartMent();
	// 禁止回车提交
	$("#subForm").submit(function() {
		return false;
	});
	$("#subBT").click(function() {
		var usersid = "";
		var allbox = $("#checkedInfo").find("input[type='checkbox']:checked");
		var checkedLength = allbox.length;
		if (checkedLength == 0) {
			layer.alert("至少选择一个用户");
			return false;
		} else if (checkedLength > 2000) {
			layer.alert("不能大于2000人！");
			return false;
		}
		allbox.each(function() {
			var jq_thisCheckBox = $(this);
			usersid += jq_thisCheckBox.val() + ";";
		});
		usersid = usersid.substring(0, usersid.length - 1);
		wx.openEnterpriseChat({
			userIds : usersid, // 必填，参与会话的成员列表。格式为userid1;userid2;...，用分号隔开，最大限制为1000个。userid单个时为单聊，多个时为群聊。
			groupName : '讨论组', // 必填，会话名称。单聊时该参数传入空字符串""即可。
			success : function(res) {
				// 回调
				chat_id = res.chat_id;
			},
			fail : function(res) {
				if (res.errMsg.indexOf('function not exist') > -1) {
					alert('版本过低请升级')
				}
				alert('fail:' + JSON.stringify(res));
			}
		});
	});
}
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
		$("#daohangBTID").click();
		// //////////////
		layer.close(ii);
	}, 'json')
}
function zTreeOnCheck(event, id, name) {
	if(event){
		$("#daohangBTID").click();
		$("#name").val(name);
	}
	var checkedID = id;
	var checkedName = name;
	if (checkedID) {
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		$.post(basePath + '/wxassets/getDetailEmployeeByDepartMentID', {
			departmentId : checkedID
		}, function(data) {
			var userList = data.data.userlist;
			var jq_checkedInfo = $("#checkedInfo").empty();
			var jq_ul = $("<ul class='kk list-unstyled list-inline'></ul>");
			jq_checkedInfo.append(jq_ul);
			for (var i = 0; i < userList.length; i++) {
				var user = userList[i];
				var userId = user.userid;
				var userName = user.name;
				if (userName.length > 3) {
					userName = userName.substring(0, 3);
				}
				var touxiang = user.avatar;
				// 创建li
				var jq_li = $("<li></li>");
				var jq_img = $("<img></img>").attr("src", touxiang);
				var jq_checkbox_div = $("<div class='checkbox'></div>");
				var jq_lable = $("<label></label>");
				var jq_checkbox = $("<input type='checkbox' />");
				jq_checkbox.attr({
					value : userId,
					name : "employeeID"
				});
				jq_lable.append(jq_img).append(jq_checkbox).append(userName);
				jq_checkbox_div.append(jq_lable);
				jq_li.append(jq_checkbox_div);
				jq_ul.append(jq_li);
			}
			if (userList.length != 0) {// 有用户列表时，显示按钮
				$("#subBT").removeClass("ldghide").addClass("ldgshow");
				$("#checkedAll").removeClass("ldghide").addClass("ldgshow");
				$("#fanxuanCheckedAll").removeClass("ldghide").addClass(
						"ldgshow");
				initCheckBox();
			} else {
				$("#subBT").removeClass("ldgshow").addClass("ldghide");
				$("#checkedAll").removeClass("ldgshow").addClass("ldghide");
				$("#fanxuanCheckedAll").removeClass("ldgshow").addClass(
						"ldghide");
			}
			layer.close(ii);
		}, 'json');
	} else {

	}
};
function initCheckBox() {
	// //////////////////////////jQuery
	// API明确说明，1.6+的jQuery要用prop，尤其是checkBox的checked的属性的判断
	var allbox = $("#checkedInfo").find("input[type='checkbox']");
	// 全选
	$("#checkedAll").click(function() {
		var ss = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		allbox.prop("checked", true);
		layer.close(ss);// 关闭遮罩层
	});
	// 反选
	$("#fanxuanCheckedAll").click(
			function() {
				var ss = layer.load(0, {
					shade : [ 0.8, '#fff' ]
				// 0.1透明度的白色背景
				});
				var currentCheckBoxs = $("#checkedInfo").find(
						"input[type='checkbox']");
				currentCheckBoxs.each(function() {
					currentCheckBox = $(this);
					if (currentCheckBox.prop('checked')) {// 如果是选中那么就变为不选中
						currentCheckBox.prop("checked", false);
					} else {
						currentCheckBox.prop("checked", true);
					}
				});
				layer.close(ss);// 关闭遮罩层
			});
	// 单个复选框选择时
	allbox.click(function() {
		var currentCheckBox = $(this);
		if (currentCheckBox.prop('checked')) {
			currentCheckBox.prop("checked", true);
		} else {
			currentCheckBox.prop("checked", false);
		}
	})
	// //////////////////////
}
