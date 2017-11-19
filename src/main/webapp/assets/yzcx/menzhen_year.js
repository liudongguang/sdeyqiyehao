$(document).ready(function() {
    Highcharts.setOptions({ global: { useUTC: false } });
    ajaxRequest("webyzcx/menzhen_year_chart", null, function (data) {
        $('#container').highcharts(data.menzhe_year);
        $('#container2').highcharts(data.menzheTongqi_year);
    });
})