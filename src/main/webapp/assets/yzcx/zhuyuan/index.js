$(document).ready(function () {
    initTapHandler();
    //主界面和侧滑菜单界面均支持区域滚动；
    mui('#offCanvasSideScroll').scroll();
    mui('#offCanvasContentScroll').scroll();

    ajaxRequest("webyzcxZyxx/indexChart", null, function (data) {
        var rybd = data.rybd; //入院波动
        var ksry = data.ksry;//科室入院情况
        var chuangwei = data.chuangwei;//床位
    });
})