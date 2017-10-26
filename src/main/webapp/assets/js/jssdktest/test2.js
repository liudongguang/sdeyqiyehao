jQuery(document).ready(function () {
    var appId = $("#appId").val();
    var timestamp = $("#timestamp").val();
    var nonceStr = $("#nonceStr").val();
    var signature = $("#signature").val();
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，企业微信的cropID
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: ['checkJsApi',
            'onMenuShareAppMessage',
            'onMenuShareWechat',
            'startRecord',
            'stopRecord',
            'onVoiceRecordEnd',
            'playVoice',
            'pauseVoice',
            'stopVoice',
            'uploadVoice',
            'downloadVoice',
            'chooseImage',
            'previewImage',
            'uploadImage',
            'downloadImage',
            'getNetworkType',
            'openLocation',
            'getLocation',
            'hideOptionMenu',
            'showOptionMenu',
            'hideMenuItems',
            'showMenuItems',
            'hideAllNonBaseMenuItem',
            'showAllNonBaseMenuItem',
            'closeWindow',
            'scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function () {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，
        // 所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
        wx.checkJsApi({
            jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            success: function(res) {
                // 以键值对的形式返回，可用的api值true，不可用为false
                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
                //alert("checkJsApi-->" + JSON.stringify(res));
            }
        });
        $("#danweiID").click(function () {
            wx.openEnterpriseChat({
                userIds: 'zhangshan;lisi;wangwu',    // 必填，参与会话的成员列表。格式为userid1;userid2;...，用分号隔开，最大限制为2000个。userid单个时为单聊，多个时为群聊。
                groupName: 'openEnterpriseChat讨论组',  // 必填，会话名称。单聊时该参数传入空字符串""即可。
                success: function(res) {
                    // 回调
                },
                fail: function(res) {
                    if(res.errMsg.indexOf('function not exist') > -1){
                        alert('版本过低请升级')
                    }
                }
            });
            WeixinJSBridge.invoke("selectEnterpriseContact", {
                    "fromDepartmentId": -1,// 必填，-1表示打开的通讯录从自己所在部门开始展示, 0表示从最上层开始
                    "mode": "single",// 必填，选择模式，single表示单选，multi表示多选
                    "type": ["department", "user"],// 必填，选择限制类型，指定department、user中的一个或者多个
                    "selectedDepartmentIds": [2, 3],// 非必填，已选部门ID列表
                    "selectedUserIds": ["lisi", "lisi2"]// 非必填，已选用户ID列表
                }, function (res) {
                    if (res.err_msg == "selectEnterpriseContact:ok") {
                        var selectedDepartmentList = res.result.departmentList;// 已选的部门列表
                        for (var i = 0; i < selectedDepartmentList.length; i++) {
                            var department = selectedDepartmentList[i];
                            var departmentId = department.id;// 已选的单个部门ID
                            var departemntName = department.name;// 已选的单个部门名称
                        }
                        var selectedUserList = res.result.userList; // 已选的成员列表
                        for (var i = 0; i < selectedUserList.length; i++) {
                            var user = selectedUserList[i];
                            var userId = user.id; // 已选的单个成员ID
                            var userName = user.name;// 已选的单个成员名称
                            var userAvatar = user.avatar;// 已选的单个成员头像
                        }
                    }
                }
            );
        });
    });
    wx.error(function (res) {
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
        alert("错误信息-->" + JSON.stringify(res));
    });
})