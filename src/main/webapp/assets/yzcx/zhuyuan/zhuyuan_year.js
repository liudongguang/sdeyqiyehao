$(document).ready(function() {
    var myChart = echarts.init(document.getElementById('container'));
    var myChart1 = echarts.init(document.getElementById('container1'));

    ajaxRequest("webyzcxZyxx/zhuyuan_year_chart", null, function (data) {
        myChart.setOption(JSON.parse(data.echartOption));
        myChart1.setOption(JSON.parse(data.echartOption_ruyuan_tongqi));

    });
})