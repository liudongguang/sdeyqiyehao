$(document).ready(function() {
    Highcharts.setOptions({ global: { useUTC: false } });
    ajaxRequest("webyzcx/indexChart", null, function (data) {
        $('#container').highcharts(data);
    });
})