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
						selectMember(wx);
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

//
var evalWXjsApi = function(jsApiFun) {
	if (typeof WeixinJSBridge == "object"
			&& typeof WeixinJSBridge.invoke == "function") {
		jsApiFun();
	} else {
		document.attachEvent
				&& document.attachEvent("WeixinJSBridgeReady", jsApiFun);
		document.addEventListener
				&& document.addEventListener("WeixinJSBridgeReady", jsApiFun);
	}
}
function selectMember(wx) {
	$("#selectMember").click(function() {
		var shouquanRUL = location.href;
		var basePath = $("#basePah").val();
		var iimem = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		$.ajax({
			url : basePath + '/jssdk/getGroupTicket',// 跳转到
			data : {
				url : shouquanRUL
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				var groupId = data.groupId;
				var nonceStr = data.nonceStr;
				var signature = data.signature;
				var timestamp = data.timestamp;
				disMembers(groupId, timestamp, nonceStr, signature);
				layer.close(iimem);// 关闭遮罩层
			},
			error : function(httpxml, errinfo) {
				layer.close(iimem);// 关闭遮罩层
				alert("异常！" + errinfo);
			}
		});
	});
}

function disMembers(groupId, timestamp, nonceStr, signature) {
	evalWXjsApi(function() {
		WeixinJSBridge.invoke("openEnterpriseContact", {
			"groupId" : groupId, // 必填，管理组权限验证步骤1返回的group_id
			"timestamp" : timestamp, // 必填，管理组权限验证步骤2使用的时间戳
			"nonceStr" : nonceStr, // 必填，管理组权限验证步骤2使用的随机字符串
			"signature" : signature, // 必填，管理组权限验证步骤2生成的签名
			"params" : {
				'departmentIds' : [ 1 ], // 非必填，可选部门ID列表（如果ID为0，表示可选管理组权限下所有部门）
				'tagIds' : [ 1 ], // 非必填，可选标签ID列表（如果ID为0，表示可选所有标签）
				'userIds' : [], // 非必填，可选用户ID列表
				'mode' : 'multi', // 必填，选择模式，single表示单选，multi表示多选
				'type' : [ 'department', 'tag', 'user' ], // 必填，选择限制类型，指定department、tag、user中的一个或者多个
				'selectedDepartmentIds' : [], // 非必填，已选部门ID列表
				'selectedTagIds' : [], // 非必填，已选标签ID列表
				'selectedUserIds' : [], // 非必填，已选用户ID列表
			},
		}, function(res) {
			if (res.err_msg.indexOf('function_not_exist') > -1) {
				alert('版本过低请升级');
			} else if (res.err_msg.indexOf('openEnterpriseContact:fail') > -1) {
				return;
			}
			var result = JSON.parse(res.result); // 返回字符串，开发者需自行调用JSON.parse解析
			var selectAll = result.selectAll; // 是否全选（如果是，其余结果不再填充）
			if (!selectAll) {
				var selectedDepartmentList = result.departmentList; // 已选的部门列表
				for (var i = 0; i < selectedDepartmentList.length; i++) {
					var department = selectedDepartmentList[i];
					var departmentId = department.id; // 已选的单个部门ID
					var departemntName = department.name; // 已选的单个部门名称
				}
				var selectedTagList = result.tagList; // 已选的标签列表
				for (var i = 0; i < selectedTagList.length; i++) {
					var tag = selectedTagList[i];
					var tagId = tag.id; // 已选的单个标签ID
					var tagName = tag.name; // 已选的单个标签名称
				}
				var selectedUserList = result.userList; // 已选的成员列表
				var jq_imgDiv = $("#imgsDiv").empty();
				var huihuaIDS = "";
				for (var i = 0; i < selectedUserList.length; i++) {
					var user = selectedUserList[i];
					var userId = user.id; // 已选的单个成员ID
					var userName = user.name; // 已选的单个成员名称
					var photo = user.photo;
					var jq_image = $("<img/>").attr("src", photo);
					var jq_name = $("<span></span>").text(userName);
					jq_imgDiv.append(jq_image).append(jq_name);
					huihuaIDS += userId + ";";
				}
				openSession(huihuaIDS.substring(0, huihuaIDS.length - 1));
			}
		})
	});
}
// ////////////////////////////////////////////////////////////
function openSession(userids) {
	wx.openEnterpriseChat({
		userIds : userids, // 必填，参与会话的成员列表。格式为userid1;userid2;...，用分号隔开，最大限制为1000个。userid单个时为单聊，多个时为群聊。
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
}
