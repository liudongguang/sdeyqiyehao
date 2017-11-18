$(document).ready(function() {
    Highcharts.setOptions({ global: { useUTC: false } });
    ajaxRequest("webyzcx/menzhenChart", null, function (data) {
        $('#container').highcharts(data.menzhen);
        $('#container1').highcharts(data.yuyue);
        $('#container2').highcharts(data.jibingChart);
    });
})