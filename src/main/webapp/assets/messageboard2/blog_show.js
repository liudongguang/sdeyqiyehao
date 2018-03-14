$(document).ready(function (e) {
    //获取话题列表
    $("p[class=blog_main_p]").each(function (t) {
        var content = $(this).text();
        $(this).empty().append(replace_em(content))
    })
    ajaxFormInitial($("#submitForm"), function (data) {
        location.href = location.href;
    });
    $("#subBTID").click(function () {
        var lyuserid = $("#wxUserID").val();
        var wxUserName = $("#wxUserNameID").val();
        var wxTouxiang = $("#wxTouxiangID").val();
        var hfnr = $("#hfnrID").val();
        if (!lyuserid) {
            layer.alert("未登录用户！");
            return false;
        }
        if (!hfnr) {
            layer.alert("未填写评论！");
            return false;
        }
        $("#submitForm").submit();
    });
});