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
                var url = "webyzcxyijixx/monthChartPage?start=" + date;
                location.href = url;
            } else {
                alert("不能大于本月！");
            }
        }
    });
    var myChart = echarts.init(document.getElementById('container'));
    ajaxRequest("webyzcxyijixx/monthChartPage_chart", {start: $('#monthly').val() + "-01"}, function (data) {
        var legendData = data.legendData;
        var renci = data.renci;
        var heji = data.heji;
        var option = {
            title: {
                text: '日医技信息',
                subtext: ''
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['人次', '费用']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: legendData
            },
            series: [
                {
                    name: '人次',
                    type: 'bar',
                    data: renci
                },
                {
                    name: '费用',
                    type: 'bar',
                    data: heji
                }
            ]
        };
        myChart.setOption(option);
    });
});