$(document).ready(function() {
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
                $("#userID").text(userid);
                /////////////////////////////////
                location.href="sankaheyi/index?jobnum="+userid;
                //////////////////////////////////
                layer.close(ii);// 关闭遮罩层
            } else {
                layer.msg("无此用户！");
                layer.close(ii);// 关闭遮罩层
            }
        }, 'json');
    } else {
        layer.msg("无code！");
        layer.close(ii);// 关闭遮罩层
    }
});