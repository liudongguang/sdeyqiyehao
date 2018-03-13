$(document).ready(function(e) {
    //获取话题列表
    $("p[class=blog_main_p]").each(function (t) {
        var content=$(this).text();
        $(this).empty().append(replace_em(content))
    })
});
