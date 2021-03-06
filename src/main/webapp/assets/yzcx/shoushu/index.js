$(document).ready(function () {
    var myChart = echarts.init(document.getElementById('container'));
    ajaxRequest("webyzcxSsxx/indexChart", null, function (data) {
       var pieDatalist=data.pieDatalist;
       var legendList=data.legendList;
        option = {
            title : {
                text: ' ',
                subtext: ' ',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                left: 'center',
                data: legendList
            },
            series : [
                {
                    name: '手术分级',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    selectedMode: 'single',
                    data: pieDatalist,
                    label: {
                        normal: {
                            show: true,
                            formatter:'{b}: {c}'
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
    });


})