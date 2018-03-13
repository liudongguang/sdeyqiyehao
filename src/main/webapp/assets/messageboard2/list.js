$(document).ready(function (e) {
    //获取话题列表
    $("p[class=blog_main_p]").each(function (t) {
        var content = $(this).text();
        $(this).empty().append(replace_em(content))
    })
});

function load() {
    var pageNum = 0;
    if ($("#pageNumID").val()) {
        pageNum = parseInt($("#pageNumID").val());
        var pages = parseInt($("#pagesID").val());
    }
    if (pageNum == pages) {
        layer.alert("没有更多");
        return;
    }
    ajaxRequest('messageboard/liuYanListForMore', {pageNum: pageNum+1}, function (data) {
        $("#pageNumID").remove();
        $("#pagesID").remove();
        $("#datacontentID").append(replace_em(data))
    }, 'html');

}
