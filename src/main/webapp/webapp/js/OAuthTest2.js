$(document).ready(
		function() {
			var codeIDVal = $("#codeID").val();
			var basePath = $("#basePath").val();
			var ii = layer.load(0, {
				shade : [ 0.8, '#fff' ]
			// 0.1透明度的白色背景
			});
			if (codeIDVal) {
				$.post(basePath + '/wxsq/getUserInfo', {
					'code' : codeIDVal
				}, function(data) {
					if (data.errorCode == 0) {
						data = data.data;
						var userid = data.userid;
						var name = data.name;
						var avatar = data.avatar;
						$("#avatarID").attr("src", avatar);
						$("#nameID").text(name);
					}
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
							wx.config({
								// debug : true, //
								// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
								appId : appId, // 必填，公众号的唯一标识
								timestamp : timestamp,// 必填，生成签名的时间戳
								nonceStr : nonceStr, // 必填，生成签名的随机串
								signature : signature,// 必填，签名，见附录1
								jsApiList : [ 'checkJsApi', 'chooseImage',
										'onMenuShareAppMessage', 'getLocation',
										'openLocation' ]
							// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
							});

							wx.ready(function() {
								wx.checkJsApi({
									jsApiList : [ 'chooseImage',
											'onMenuShareAppMessage',
											'onMenuShareTimeline' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
									success : function(res) {
										//alert(JSON.stringify(res));
									}
								});
								xiangce(wx);
								photodis(wx);
								didian(wx);
								
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
				}, 'json');
			} else {
				layer.msg("无code");
				layer.close(ii);// 关闭遮罩层
			}
		});
