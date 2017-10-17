$(document).ready(function(e) {
	initWXSQ();
	ImgIputHandler.Init();
	$("#init").bind("click", function() {
		if ($(".focus").css("display") == "none") {
			$(".focus").show();
		} else {
			$(".focus").hide();
		}
	});
	$(".focus").each(function(i) {
		$(this).bind("click", function() {
			$(".pic:eq(" + i + ")").hide();
		});
	})
});
function initWXSQ() {
	var codeIDVal = $("#codeID").val();
	var basePath = $("#basePath").val();
	var ii = layer.load(0, {
		shade : [ 0.8, '#fff' ]
	// 0.1透明度的白色背景
	});
	// ///
	var shouquanRUL = location.href;
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
			var jsapi_ticket = data.jsapi_ticket;
			var nonceStr = data.nonceStr;
			var signature = data.signature;
			var timestamp = data.timestamp;
			var url = data.url;
			wx
					.config({
						// debug : true, //
						// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						appId : appId, // 必填，公众号的唯一标识
						timestamp : timestamp,// 必填，生成签名的时间戳
						nonceStr : nonceStr, // 必填，生成签名的随机串
						signature : signature,// 必填，签名，见附录1
						jsApiList : [ 'checkJsApi', 'chooseImage',
								'uploadImage', 'onMenuShareAppMessage',
								'getLocation', 'openLocation' ]
					// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
					});

			wx.ready(function() {
				wx.checkJsApi({
					jsApiList : [ 'chooseImage', 'onMenuShareAppMessage',
							'onMenuShareTimeline' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
					success : function(res) {
						// alert(JSON.stringify(res));
					}
				});
				initSubmitForm(wx);
				// ////
				// 打开相册start
				$("#dkxcID").click(function() {
					wx.chooseImage({
						count : 5, // 默认9
						sizeType : [ 'original', 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有
						sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
						success : function(res) {
							var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
							var jq_imgsDiv = $("#lyimgdiv");
							jq_imgsDiv.empty();
							syncUpload(localIds, jq_imgsDiv);
							// /////////////
						}
					});
				})
				// //打开相册end
				layer.close(ii);// 关闭遮罩层
			});
			wx.error(function(res) {
				alert("wx fail---->" + res)
				layer.close(ii);// 关闭遮罩层
			});
		},
		error : function(httpxml, errinfo) {
			layer.close(ii);// 关闭遮罩层
			alert("异常！" + errinfo);
		}
	});
	// //

}
function initSubmitForm(wx) {
	$("#submitBT").click(function() {
		var userid = $("#wxUserID").val();
		var username = $("#wxUserNameID").val();
		var touxiang = $("#wxTouxiangID").val();
		var title = $("#doc-ipt-email-1").val();
		var content = $("#contentID").val();
		if (!(userid && username && touxiang)) {
			alert("微信授权失败！");
			return false;
		}
		if (!title) {
			alert("输入标题！");
			return false;
		}
		if (!content) {
			alert("输入内容！");
			return false;
		}
		var jq_wximg = $("img[class='up_img']");
		$("#mediaimagesid").val("");
		jq_wximg.each(function() {
			var serverId = $(this).attr("serverid");
			var oldmediaid = $("#mediaimagesid").val();
			if (oldmediaid) {
				$("#mediaimagesid").val(oldmediaid + "," + serverId)
			} else {
				$("#mediaimagesid").val(serverId)
			}
		});
		// ///微信图片上传处理
		$("#subformID").submit();
		return false;
	});
}
var syncUpload = function(localIds, jq_imgsDiv) {
	var localId = localIds.pop();
	wx
			.uploadImage({
				localId : localId,
				isShowProgressTips : 0,
				success : function(res) {
					// ///显示图片
					var divimg = '<div class="am-form-group am-form-file am-fl up_all pic"><img class="up_img" src="{0}"	alt="" /> <span class="init_bg focus"><img	class="init_minus" src="assets/messageboard/images/init.png" alt="{1}" /></span></div>'
					divimg = divimg.replace('{0}', localId);
					divimg = divimg.replace('{1}', localId);
					jq_imgsDiv.append(divimg);
					// //
					// ///
					var serverId = res.serverId; // 返回图片的服务器端ID
					var jq_wximg = $("img[class='up_img'][src='" + localId
							+ "']");
					jq_wximg.attr("serverid", serverId)
					// 删除图片start
					var jq_shanchuimg = $("img[class='init_minus'][alt='"
							+ localId + "']");
					jq_shanchuimg.click(function() {
						$(this).parent().parent().remove();
					})
					// //////删除图片end
					// 其他对serverId做处理的代码
					if (localIds.length > 0) {
						syncUpload(localIds, jq_imgsDiv);
					}
				}
			});
};
var ImgIputHandler = {
	facePath : [ {
		faceName : "em_1",
		facePath : "1.gif"
	}, {
		faceName : "em_2",
		facePath : "2.gif"
	}, {
		faceName : "em_3",
		facePath : "3.gif"
	}, {
		faceName : "em_4",
		facePath : "4.gif"
	}, {
		faceName : "em_5",
		facePath : "5.gif"
	}, {
		faceName : "em_6",
		facePath : "6.gif"
	}, {
		faceName : "em_7",
		facePath : "7.gif"
	}, {
		faceName : "em_8",
		facePath : "8.gif"
	}, {
		faceName : "em_9",
		facePath : "9.gif"
	}, {
		faceName : "em_10",
		facePath : "10.gif"
	}, {
		faceName : "em_11",
		facePath : "11.gif"
	}, {
		faceName : "em_12",
		facePath : "12.gif"
	}, {
		faceName : "em_13",
		facePath : "13.gif"
	}, {
		faceName : "em_14",
		facePath : "14.gif"
	}, {
		faceName : "em_15",
		facePath : "15.gif"
	}, {
		faceName : "em_16",
		facePath : "16.gif"
	}, {
		faceName : "em_17",
		facePath : "17.gif"
	}, {
		faceName : "em_18",
		facePath : "18.gif"
	}, {
		faceName : "em_19",
		facePath : "19.gif"
	}, {
		faceName : "em_20",
		facePath : "20.gif"
	}, {
		faceName : "em_21",
		facePath : "21.gif"
	}, {
		faceName : "em_22",
		facePath : "22.gif"
	}, {
		faceName : "em_23",
		facePath : "23.gif"
	}, {
		faceName : "em_24",
		facePath : "24.gif"
	}, {
		faceName : "em_25",
		facePath : "25.gif"
	}, {
		faceName : "em_26",
		facePath : "26.gif"
	}, {
		faceName : "em_27",
		facePath : "27.gif"
	}, {
		faceName : "em_28",
		facePath : "28.gif"
	}, {
		faceName : "em_29",
		facePath : "29.gif"
	}, {
		faceName : "em_30",
		facePath : "30.gif"
	}, {
		faceName : "em_31",
		facePath : "31.gif"
	}, {
		faceName : "em_32",
		facePath : "32.gif"
	}, {
		faceName : "em_33",
		facePath : "33.gif"
	}, {
		faceName : "em_34",
		facePath : "34.gif"
	}, {
		faceName : "em_35",
		facePath : "35.gif"
	}, {
		faceName : "em_36",
		facePath : "36.gif"
	}, {
		faceName : "em_37",
		facePath : "37.gif"
	}, {
		faceName : "em_38",
		facePath : "38.gif"
	}, {
		faceName : "em_39",
		facePath : "39.gif"
	}, {
		faceName : "em_40",
		facePath : "40.gif"
	}, {
		faceName : "em_41",
		facePath : "41.gif"
	}, {
		faceName : "em_42",
		facePath : "42.gif"
	}, {
		faceName : "em_43",
		facePath : "43.gif"
	}, {
		faceName : "em_44",
		facePath : "44.gif"
	}, {
		faceName : "em_45",
		facePath : "45.gif"
	}, {
		faceName : "em_46",
		facePath : "46.gif"
	}, {
		faceName : "em_47",
		facePath : "47.gif"
	}, {
		faceName : "em_48",
		facePath : "48.gif"
	}, {
		faceName : "em_49",
		facePath : "49.gif"
	}, {
		faceName : "em_50",
		facePath : "50.gif"
	}, {
		faceName : "em_51",
		facePath : "51.gif"
	}, {
		faceName : "em_52",
		facePath : "52.gif"
	}, {
		faceName : "em_53",
		facePath : "53.gif"
	}, {
		faceName : "em_54",
		facePath : "54.gif"
	}, {
		faceName : "em_55",
		facePath : "55.gif"
	}, {
		faceName : "em_56",
		facePath : "56.gif"
	}, {
		faceName : "em_57",
		facePath : "57.gif"
	}, {
		faceName : "em_58",
		facePath : "58.gif"
	}, {
		faceName : "em_59",
		facePath : "59.gif"
	}, {
		faceName : "em_60",
		facePath : "60.gif"
	}, {
		faceName : "em_61",
		facePath : "61.gif"
	}, {
		faceName : "em_62",
		facePath : "62.gif"
	}, {
		faceName : "em_63",
		facePath : "63.gif"
	}, {
		faceName : "em_64",
		facePath : "64.gif"
	}, {
		faceName : "em_65",
		facePath : "65.gif"
	}, {
		faceName : "em_66",
		facePath : "66.gif"
	}, {
		faceName : "em_67",
		facePath : "67.gif"
	}, {
		faceName : "em_68",
		facePath : "68.gif"
	}, {
		faceName : "em_69",
		facePath : "69.gif"
	}, {
		faceName : "em_70",
		facePath : "70.gif"
	}, {
		faceName : "em_71",
		facePath : "71.gif"
	}, {
		faceName : "em_72",
		facePath : "72.gif"
	}, {
		faceName : "em_73",
		facePath : "73.gif"
	}, {
		faceName : "em_74",
		facePath : "74.gif"
	}, {
		faceName : "em_75",
		facePath : "75.gif"
	} ],
	Init : function() {
		var isShowImg = false;
		$(".Input_text").focusout(function() {
			$(this).parent().css("border-color", "#cccccc");
			$(this).parent().css("box-shadow", "none");
			$(this).parent().css("-moz-box-shadow", "none");
			$(this).parent().css("-webkit-box-shadow", "none");
		});
		$(".Input_text").focus(
				function() {
					$(this).parent()
							.css("border-color", "rgba(19,105,172,.75)");
					$(this).parent().css("box-shadow",
							"0 0 3px rgba(19,105,192,.5)");
					$(this).parent().css("-moz-box-shadow",
							"0 0 3px rgba(241,39,232,.5)");
					$(this).parent().css("-webkit-box-shadow",
							"0 0 3px rgba(19,105,252,3)");
				});
		$(".imgBtn")
				.click(
						function() {
							if (isShowImg == false) {
								isShowImg = true;
								$(this).parent().prev().animate({
									marginTop : "-125px"
								}, 300);
								if ($(".faceDiv").children().length == 0) {
									for (var i = 0; i < ImgIputHandler.facePath.length; i++) {
										$(".faceDiv")
												.append(
														"<img title=\""
																+ ImgIputHandler.facePath[i].faceName
																+ "\" src=\"assets/messageboard/face/"
																+ ImgIputHandler.facePath[i].facePath
																+ "\" />");
									}
									$(".faceDiv>img")
											.click(
													function() {

														isShowImg = false;
														$(this)
																.parent()
																.animate(
																		{
																			marginTop : "0px"
																		}, 300);
														ImgIputHandler
																.insertAtCursor(
																		$(".Input_text")[0],
																		"["
																				+ $(
																						this)
																						.attr(
																								"title")
																				+ "]");
													});
								}
							} else {
								isShowImg = false;
								$(this).parent().prev().animate({
									marginTop : "0px"
								}, 300);
							}
						});
		$(".postBtn").click(function() {
			alert($(".Input_text").val());
		});
	},
	insertAtCursor : function(myField, myValue) {
		if (document.selection) {
			myField.focus();
			sel = document.selection.createRange();
			sel.text = myValue;
			sel.select();
		} else if (myField.selectionStart || myField.selectionStart == "0") {
			var startPos = myField.selectionStart;
			var endPos = myField.selectionEnd;
			var restoreTop = myField.scrollTop;
			myField.value = myField.value.substring(0, startPos) + myValue
					+ myField.value.substring(endPos, myField.value.length);
			if (restoreTop > 0) {
				myField.scrollTop = restoreTop;
			}
			myField.focus();
			myField.selectionStart = startPos + myValue.length;
			myField.selectionEnd = startPos + myValue.length;
		} else {
			myField.value += myValue;
			myField.focus();
		}
	}
}