$(document)
		.ready(
				function() {
					var basePath = $("#basePah").val();
					var shouquanRUL = location.href;
					$
							.ajax({
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
									wx.config({
										debug : false,
										appId : appId,
										timestamp : timestamp,
										nonceStr : nonceStr,
										signature : signature,
										jsApiList : [ 'checkJsApi',
												'onMenuShareTimeline',
												'onMenuShareAppMessage',
												'onMenuShareQQ',
												'onMenuShareWeibo',
												'onMenuShareQZone',
												'hideMenuItems',
												'showMenuItems',
												'hideAllNonBaseMenuItem',
												'showAllNonBaseMenuItem',
												'translateVoice',
												'startRecord', 'stopRecord',
												'onVoiceRecordEnd',
												'playVoice', 'onVoicePlayEnd',
												'pauseVoice', 'stopVoice',
												'uploadVoice', 'downloadVoice',
												'chooseImage', 'previewImage',
												'uploadImage', 'downloadImage',
												'getNetworkType',
												'openLocation', 'getLocation',
												'hideOptionMenu',
												'showOptionMenu',
												'closeWindow', 'scanQRCode',
												'chooseWXPay',
												'openProductSpecificView',
												'addCard', 'chooseCard',
												'openCard' ]
									});
									wx
											.ready(function() {
												$("#fxpyq")
														.click(
																function() {
																	wx
																			.onMenuShareTimeline({
																				title : '互联网之子',
																				link : 'http://movie.douban.com/subject/25785114/',
																				imgUrl : 'http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg',
																				trigger : function(
																						res) {
																					// 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
																					alert('用户点击分享到朋友圈');
																				},
																				success : function(
																						res) {
																					alert('已分享');
																				},
																				cancel : function(
																						res) {
																					alert('已取消');
																				},
																				fail : function(
																						res) {
																					alert(JSON
																							.stringify(res));
																				}
																			});
																	alert('已注册获取“分享到朋友圈”状态事件');
																});
											});
									wx.error(function(res) {
										alert("wx fail")
									});

								},
								error : function(httpxml, errinfo) {
									alert("异常！" + errinfo);
								}
							});
				});
