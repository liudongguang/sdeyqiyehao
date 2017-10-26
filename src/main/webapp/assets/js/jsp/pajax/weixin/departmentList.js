jQuery(document).ready(function () {
    var $click = $("[pajax-data]");
    $click.click(function () {
        var href = $(this).attr("href");
        ajaxRequest(href, {}, function (data) {
            openLayerWindowID=layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['80%', '90%'], //宽高
                content: data
            });
        }, 'html');
    });
})