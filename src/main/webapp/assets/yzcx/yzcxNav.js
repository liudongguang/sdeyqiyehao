$(document).ready(function() {
    $("div[class=mui-scroll]").find('a').on('tap',function (e) {
        var href=$(this).attr("url");
        layer.load(0, {
            shade: [0.8, '#fff']
        });
        mui.openWindow({ url:href, id:'detail' });
        e.stopPropagation();
        e.preventDefault();
        return false;
    });
});