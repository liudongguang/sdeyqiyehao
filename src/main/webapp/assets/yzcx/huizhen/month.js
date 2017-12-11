$(document).ready(function () {
    $('#monthly').monthpicker({
        years: [2017],
        topOffset: 6,
        onMonthSelect: function (m, y) {
            var d1 = new Date()
            var d2 = new Date(y, m, 1);
            //选择的日期要小于当前日期
            if (d1 > d2) {
                var month = d2.getMonth().toString();
                month = parseInt(month) + 1;
                if (month.toString().length == 1) {
                    month = "0" + month;
                }
                var date = d2.getFullYear() + "-" + month + "-01";
                var url = "webyzcxHuizhen/monthChartPage?start=" + date;
                location.href = url;
            } else {
                alert("不能大于本月！");
            }
        }
    })
    var myChart = echarts.init(document.getElementById('container'));
    ajaxRequest("webyzcxHuizhen/monthChart", {start: $('#monthly').val() + "-01"}, function (data) {
        var legendData=data.legendData;
        var shenqing=data.shenqing;
        var jieshou=data.jieshou;
        var option = {
            "backgroundColor": "#ffffff",
            "title": {"text": " ", "subtext": "日入院人次"},
            "tooltip": {"trigger": "axis"},
            "legend": {"data": ["入院情况"]},
            "grid": {"left": 80},
            "xAxis": [{"type": "value"}],
            "yAxis": [{
                "type": "category",
                "inverse": true,
                "data": legendData
            }],
            "series": [{
                "name": "会诊申请",
                "type": "bar",
                "itemStyle": {"normal": {"color": "#7cb5ec"}},
                "data": shenqing
            },{
                "name": "会诊接受",
                "type": "bar",
                "data": jieshou
            }]
        }
        myChart.setOption(option);
    });
});