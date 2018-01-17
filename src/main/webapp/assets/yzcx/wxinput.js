$(document).ready(function() {
    // ajaxRequest("jssdk/getJsapiTicket", {url:location.href}, function (data) {
    //     wx.config({
    //         beta: true,// 必须这么写，否则在微信插件有些jsapi会有问题
    //         debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    //         appId: data.appId, // 必填，企业微信的corpID
    //         timestamp:data.timestamp , // 必填，生成签名的时间戳
    //         nonceStr: data.nonceStr, // 必填，生成签名的随机串
    //         signature: data.signature,// 必填，签名，见[附录1](#11974)
    //         jsApiList: ['uploadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    //     });
    // });
    ajaxRequest("wxsq/getUserInfo", {code:$("#codeId").val()}, function (data) {
        if(data.errorCode!=500){
             var userid=data.data.userid;
             location.href="webyzcx/login?userid="+userid;
        }else{
            alert(data.errorMsg);
        }

    })
})