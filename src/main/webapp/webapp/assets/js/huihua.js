var basePath = $("#basePah").val();
$(document).ready(
		function() {
			var basePath = $("#basePah").val();
			var shouquanRUL = location.href;
			var chat_id = "";
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
						jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
								'onMenuShareAppMessage', 'onMenuShareQQ',
								'onMenuShareWeibo', 'onMenuShareQZone',
								'hideMenuItems', 'showMenuItems',
								'hideAllNonBaseMenuItem',
								'showAllNonBaseMenuItem', 'translateVoice',
								'startRecord', 'stopRecord',
								'onVoiceRecordEnd', 'playVoice',
								'onVoicePlayEnd', 'pauseVoice', 'stopVoice',
								'uploadVoice', 'downloadVoice', 'chooseImage',
								'previewImage', 'uploadImage', 'downloadImage',
								'getNetworkType', 'openLocation',
								'getLocation', 'hideOptionMenu',
								'showOptionMenu', 'closeWindow', 'scanQRCode',
								'chooseWXPay', 'openProductSpecificView',
								'addCard', 'chooseCard', 'openCard',
								'openEnterpriseChat', 'openEnterpriseContact' ]
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
		});
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
function getDepartMent() {
	var ii = layer.load(0, {
		shade : [ 0.8, '#fff' ]
	// 0.1透明度的白色背景
	});
	$.post(basePath + "/wxassets/getAllSimpleDataDepartForzTreeNodes", {},
			function(data) {
				var data = data.data;
				// 定义参数
				var zTreeObj, setting = {
					data : {
						simpleData : {
							enable : true,
							idKey : "id",
							pIdKey : "parentid",
							rootPId : 0
						}
					},
					callback : {
						onClick : zTreeOnCheck
					}

				};
				// 初始化tree
				zTreeObj = $.fn.zTree.init($("#departmentTreeForSimpleData"),
						setting, data);
				// 展开第一个节点 start
				var nodes = zTreeObj.getNodes();
				var firstNode = nodes[0];
				zTreeObj.expandNode(firstNode, true, false, false, false);
				// 展开第一个节点 end
				layer.close(ii);
			}, 'json')
}
function zTreeOnCheck(event, treeId, treeNode) {
	var checkedID = treeNode.id;
	var checkedName = treeNode.name;
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
			if (userList.length != 0) {
				$("#subBT").removeClass("hide").addClass("show");
				$("#checkedAll").removeClass("hide").addClass("show");
				initCheckBox();
			} else {
				$("#subBT").removeClass("show").addClass("hide");
				$("#checkedAll").removeClass("show").addClass("hide");
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
	$("#checkedAll").click(function() {
		allbox.prop("checked", true);
	});
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
