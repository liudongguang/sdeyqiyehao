$(document).ready(function() {
    Highcharts.setOptions({ global: { useUTC: false } });
    ajaxRequest("webyzcx/menzhenChart", null, function (data) {
        $('#container').highcharts(data);
    });
})