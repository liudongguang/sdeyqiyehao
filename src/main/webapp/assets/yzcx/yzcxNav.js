$(document).ready(function() {
    $("div[class=mui-scroll]").find('a').on('tap',function (e) {
        var href=$(this).attr("url");
        mui.openWindow({ url:href, id:'detail' });
        e.stopPropagation();
        e.preventDefault();
        return false;
    });
});