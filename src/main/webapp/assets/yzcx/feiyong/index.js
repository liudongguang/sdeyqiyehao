$(document).ready(function() {
    Highcharts.setOptions({ global: { useUTC: false } });
    ajaxRequest("webyzcxFeiYong/indexChart", null, function (data) {
        $('#container').highcharts(data.zhuyuan);
        $('#container1').highcharts(data.menzhen);
    });
})