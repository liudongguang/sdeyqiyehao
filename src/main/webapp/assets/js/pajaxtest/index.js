jQuery(document).ready(function () {
    $("#defaultDisplay").click();//进入页面展开
    var loadIndex;

    $(document).on('pjax:beforeSend', function () {
        NProgress.start();
    })
    $(document).on('pjax:send', function () {
        NProgress.set(0.3);
        loadIndex = layer.load(0, {
            shade: [0.6, '#fff']
        });
    })
    $(document).on('pjax:complete', function () {
        NProgress.set(0.6);
        layer.close(loadIndex);
    })
    $(document).on('pjax:end', function () {
        NProgress.done();
    })
    $(document).on('pjax:error', function () {
        NProgress.done();
    })
    $(document).on('pjax:timeout', function (event) {
        // Prevent default timeout redirection behavior
        event.preventDefault();
        NProgress.done();
        alert("请求超时！");
    })
    //点击出发请求
    $("p[class='zcd']").click(function () {
        var hrefVal = $(this).attr("href");
        if (hrefVal) {
            $.pjax({url: hrefVal, container: '#pajaxMainContainer'})
        }
    });
    if ($("#isRedirect").val()) {
        //刷新当前地址的请求
        $.pjax({
            url: '',
            container: '#pajaxMainContainer'
        });
    }
});