$(document).ready(function() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('container'));
    var myChart1 = echarts.init(document.getElementById('container1'));
    var myChart2 = echarts.init(document.getElementById('container2'));
    ajaxRequest("webyzcxZyxx/indexChart",null, function (data) {
        myChart.setOption(JSON.parse(data.echartOption));
        myChart1.setOption(JSON.parse(data.echartOption_ruyuanqianshi));
        myChart2.setOption(JSON.parse(data.echartOption_chuangweishu));
    });
})