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
                var url = "webyzcxZyxx/zhuyuan_yue_page?start=" + date;
                location.href = url;
            } else {
                alert("不能大于本月！");
            }
        }
    })
    var myChart = echarts.init(document.getElementById('container'));
    ajaxRequest("webyzcxZyxx/zhuyuan_yue_chart", {start: $('#monthly').val() + "-01"}, function (data) {
        var option = {
            "backgroundColor": "#ffffff",
            "title": {"text": " ", "subtext": "日入院人次"},
            "tooltip": {"trigger": "axis"},
            "legend": {"data": [["入院情况"]]},
            "grid": {"left": 80},
            "xAxis": [{"type": "value"}],
            "yAxis": [{
                "type": "category",
                "inverse": true,
                "data": ["2017-12-01", "2017-12-02", "2017-12-03", "2017-12-04", "2017-12-05", "2017-12-06"]
            }],
            "series": [{
                "name": "入院情况",
                "type": "bar",
                "itemStyle": {"normal": {"color": "#7cb5ec"}},
                "data": [167.0, 103.0, 131.0, 293.0, 233.0, 209.0]
            }]
        }
        myChart.setOption(JSON.parse(data.echartOption));
    });
});