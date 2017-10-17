$(document).ready(
		function() {
			var appId = $("#appId").val();
			var nonceStr = $("#nonceStr").val();
			var signature = $("#signature").val();
			var timestamp = $("#timestamp").val();
			var ii = layer.load(0, {
				shade : [ 0.8, '#fff' ]
			// 0.1透明度的白色背景
			});
			wx.config({
				//debug : true, //
				// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : appId, // 必填，公众号的唯一标识
				timestamp : timestamp,// 必填，生成签名的时间戳
				nonceStr : nonceStr, // 必填，生成签名的随机串
				signature : signature,// 必填，签名，见附录1
				jsApiList : [ 'checkJsApi', 'chooseImage',
						"onMenuShareTimeline", 'onMenuShareAppMessage',
						'previewImage', 'startRecord', 'stopRecord',
						'playVoice', 'uploadVoice' ]
			});
			wx.ready(function() {
				xiangce(wx);
				photodis(wx);
				pengyouquan(wx);
				layer.close(ii);
			});
			wx.error(function(res) {
				alert("wx fail--->" + res)
			});
		});
