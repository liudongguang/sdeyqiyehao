$(document).ready(function() {
    ajaxRequest("webyzcx/indexChart", null, function (data) {
        $('#container').highcharts(data);
    });
})