function xiangce(wx) {
	// 打开相册
	$("#dkxcID").click(function() {
		var jq_imgsDiv = $("#imgsDiv");
		var jq_imgSrc = $("#imgSrc");
		wx.chooseImage({
			count : 2, // 默认9
			sizeType : [ 'original', 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有
			sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
			success : function(res) {
				var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
				jq_imgsDiv.empty();
				jq_imgSrc.empty();
				for (var i = 0; i < localIds.length; i++) {
					var jq_image = $("<img/>").attr("src", localIds[i]);
					jq_imgsDiv.append(jq_image);
					jq_imgSrc.append(localIds[i] + ",");
				}
				// /////////////
			}
		});
	})
}
function photodis(wx) {
	// //查看图片
	$("#yltpID")
			.click(
					function() {
						wx
								.previewImage({
									current : 'http://i-7.vcimg.com/crop/9bed322ec2fe94f486d225006a2170e1289400%28600x%29/thumb.jpg',
									urls : [
											'http://i-7.vcimg.com/crop/9bed322ec2fe94f486d225006a2170e1289400%28600x%29/thumb.jpg',
											'http://img5.imgtn.bdimg.com/it/u=453794973,1438963502&fm=21&gp=0.jpg',
											'http://att.bbs.duowan.com/forum/201510/10/221541jepjot1tdmyer582.jpg' ]
								});
					});
}

function pengyouquan(wx) {
	wx.onMenuShareTimeline({
		title : '分享给朋友圈的分享标题', // 分享标题
		link : 'http://www.baidu.com', // 分享链接
		imgUrl : 'http://weixin.chinamobiz.com/guyueqiyehao/images/aa.jpeg', // 分享图标
		success : function() {
			// 用户确认分享后执行的回调函数
			alert("成功...")
		},
		cancel : function() {
			// 用户取消分享后执行的回调函数
			alert("取消...")
		},
		fail : function(res) {
			dump(myObject);
		}
	});
}
function didian(wx) {
	$("#getPositionID").click(function() {
		var basePath = $("#basePath").val();
		var ii = layer.load(0, {
			shade : [ 0.8, '#fff' ]
		// 0.1透明度的白色背景
		});
		wx.getLocation({
			type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
			success : function(res) {
				var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
				var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
				var speed = res.speed; // 速度，以米/每秒计
				var accuracy = res.accuracy; // 位置精度
				$.post(basePath + '/baiduMap/getPositionByLngAndLat', {
					lng : longitude,
					lat : latitude
				}, function(rsData) {
					var data = rsData.data;
					var address = data.result.formatted_address;
					// $("#positionID").text(address+" 精度："+accuracy+"
					// latitude:"+latitude+" longitude:"+longitude);
					$("#positionID").text(address);
					// //
					wx.openLocation({
						latitude : latitude, // 纬度，浮点数，范围为90 ~ -90
						longitude : longitude, // 经度，浮点数，范围为180 ~ -180。
						name : address, // 位置名
						address : address, // 地址详情说明
						scale : 26, // 地图缩放级别,整形值,范围从1~28。默认为最大
						infoUrl : 'http://weixin.qq.com' // 在查看位置界面底部显示的超链接,可点击跳转
					});
					// //
					layer.close(ii);// 关闭遮罩层
				}, 'json');
			}
		});
	});
	// //////////
	$("#openPositionID").click(function() {
		alert("打开地图");
		wx.openLocation({
			latitude : 23.099994,
			longitude : 113.324520,
			name : 'TIT 创意园',
			address : '广州市海珠区新港中路 397 号',
			scale : 14,
			infoUrl : 'http://weixin.qq.com'
		});
	});
}
function dump(myObject) {
	var s = "";
	for ( var property in myObject) {
		s = s + "<br> " + property + ": " + myObject[property];
	}
	alert(s);
}